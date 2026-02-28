## What Collector is and  How It Really Works ?
```
A Collector is specified by four functions that work together to accumulate entries into a mutable result container, and optionally perform a final transform on the result. 
    They are:
    1. Creation of a new result container (supplier())
    2. In-corporating a new data element into a result container (accumulator())
    3. Combining two result containers into one (combiner())
    4. Performing an optional final transform on the container (finisher())

    It answers FOUR questions:
      1. What container do we START with?    → Supplier<A>
      2. How do we ADD one element?          → BiConsumer<A, T>
      3. How do we MERGE two containers?     → BinaryOperator<A>   (parallel only)
      4. How do we FINISH the result?        → Function<A, R>

    Where:
      T = input element type  (e.g., String, Employee, Integer)
      A = accumulation type   (e.g., ArrayList, StringBuilder, HashMap)
      R = result type         (e.g., List, String, Map)
```

## ## Sequential Stream `collect()` Flow Step By Step :-
```java 
List<String> names = List.of("Alice", "Bob", "Charlie", "Diana");
List<String> result = names.stream()
    .filter(n -> n.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

```
Source: List["Alice", "Bob", "Charlie", "Diana"]
Pipeline: filter(len>3) -> map(toUpperCase) -> collect(tolist) 

STEP 1: Terminal operation collect() called, Stream pipeline is LAZY nothing has executed yet.
        collect() is the TRIGGER that starts evaludation.
        AbstractPipeline.collect(Collector collector) is invoked.
        Java internally calls : return evaluate(ReduceOps.makeRef(collector));
        
STEP 2: Pipeline evaluation starts, ReduceOps.makeRef(collector) creates a ReduceOp terminal operation.
        Inside  makeRef, it extracts the Collector's four components
        
        Supplier<List<String>>   supplier    = collector.supplier();
        // = () -> new ArrayList<>()    [from toList() implementation]
        
        BiConsumer<List<String>, String> accumulator = collector.accumulator();
        // = (list, element) -> list.add(element)
        
        BinaryOperator<List<String>> combiner = collector.combiner();
        // = (left, right) -> { left.addAll(right); return left; }
        
        Function<List<String>, List<String>> finisher = collector.finisher();
        // = Function.identity()  [because IDENTITY_FINISH characteristic is set] 
              
STEP 3: Container creation - supplier.get(), calls new ArrayList<>()
        // A = new ArrayList<String>()
        Memory : ArrayList object created in Heap(Eden Space)
        
STEP 4: Pipeline traversal - element by element
        The pipeline traverse each element through ALL stages before accumulating
        This is known as PUSH model - source pushes the element downstream
        
        Element 1: "Alice"
        Source        -> "Alice"
        filter stage -> "Alice".length() = 5>3 = TRUE -> PASS
        map stage    -> "Alice".toUpperCase() = "ALICE"
        collect/accumulate → A.add("ALICE")
        A = ["ALICE"]
        
        Element 2: "Bob"
        Source        -> "Bob"
        filter stage  -> "Bob".length() = 3>3 = FALSE -> SKIP 
        map and collect never called for Bob
        
        Element 3: "Charlie"
        Source        -> "Charlie"
        filter stage ->  7>3 = TRUE -> PASS
        map stage    ->  "CHARLIE"
        collect/accumulate → A.add("CHARLIE")
        A = ["ALICE","CHARLIE"]
        
        Element 3: "Diana"
        Source        -> "Diana"
        filter stage ->  5>3 = TRUE -> PASS
        map stage    ->  "DIANA"
        collect/accumulate → A.add("DIANA")
        A = ["ALICE","CHARLIE","DIANA"]
        
STEP 5: Finisher applied(or skipped), it checks if collector have IDENTITY_FINISH characteristic ?
        
        If IDENTITY_FINISH : result = (R) A // just cast not method call 
        // Ex : result = (List<String>)Arraylist -> skips finisher() call
        
        If NOT IDENTITY_FINISH (ex: toUnmodifiableList())
        result = finisher.apply(A)
        // Ex:  Collections.unmodifiableList(arrayList)
        
STEP 6: Return result
        return result;
        // result = ["ALICE", "CHARLIE", "DIANA"]
        
TOTAL OBJECTS CREATED:
  1 ArrayList (the accumulator A)
  3 String objects from toUpperCase()
  1 filter lambda instance (shared, not per element)
  1 map lambda instance (shared)
  Pipeline nodes: ~5 objects
        
```
## Collector Interface : Internal implementation 

```java
// How Collectors.toList() is actually implemented:
public static <T> Collector<T, ?, List<T>> toList() {
    return new CollectorImpl<>(
        ArrayList::new,                        // supplier
        List::add,                             // accumulator
        (left, right) -> {                     // combiner
            left.addAll(right);
            return left;
        },
        Collections.unmodifiableList(),        // finisher
        CH_NOID                                // no characteristics (ORDERED result)
    );
}

// toUnmodifiableList() (Java 10+):
public static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
    return new CollectorImpl<>(
        ArrayList::new,
        List::add,
        (left, right) -> { left.addAll(right); return left; },
        list -> Collections.unmodifiableList(list),   // finisher transforms!
        CH_NOID
    );
}
// NOTE: finisher is NOT identity → IDENTITY_FINISH characteristic NOT set
// → finisher() method IS called (unlike toList())

// toSet():
public static <T> Collector<T, ?, Set<T>> toSet() {
    return new CollectorImpl<>(
        HashSet::new,
        Set::add,
        (left, right) -> {
            if (left.size() < right.size()) {
                right.addAll(left); return right;   // merge smaller into larger!
            } else {
                left.addAll(right); return left;
            }
        },
        Collections.unmodifiableSet(),
        CH_UNORDERED_NOID                         // UNORDERED: Set has no order
    );
}
```

## Parallel Stream `collect()` flow step by step
```java
SOURCE: List ["Alice","Bob","Charlie","Diana"]
        List<String> result = names.parallelStream()
        .filter(n -> n.length() > 3)
        .map(String::toUpperCase)
        .collect(Collectors.toList());
```
```
    Key difference is COMBINER() is now called.
    
    STEP 1: Spliterator splits source into chunks
        ArrayListSpliterator[0,4) splits:
            Left:  [0,2)  → ["Alice", "Bob"]
            Right: [2,4)  → ["Charlie", "Diana"]
            
        Further splits if more threads available:
            Left-Left:  ["Alice"]
            Left-Right: ["Bob"]
            Right-Left: ["Charlie"]
            Right-Right:["Diana"]
    
    STEP 2: ForkJoinPool assigns each chunk to a thread
        Thread-1: processes ["Alice"]
        Thread-2: processes ["Bob"]
        Thread-3: processes ["Charlie"]
        Thread-4: processes ["Diana"]

    STEP 3: Each thread creates its OWN container — supplier.get()
        Thread-1: A1 = new ArrayList<>()   ← separate instance!
        Thread-2: A2 = new ArrayList<>()   ← separate instance!
        Thread-3: A3 = new ArrayList<>()   ← separate instance!
        Thread-4: A4 = new ArrayList<>()   ← separate instance!

    STEP 4: Each thread independently accumulates
        Thread-1: "Alice" → filter(true) → "ALICE" → A1.add("ALICE")
        A1 = ["ALICE"]
        
        Thread-2: "Bob" → filter(false) → SKIP
        A2 = []
        
        Thread-3: "Charlie" → filter(true) → "CHARLIE" → A3.add("CHARLIE")
        A3 = ["CHARLIE"]
        
        Thread-4: "Diana" → filter(true) → "DIANA" → A4.add("DIANA")
        A4 = ["DIANA"]

    STEP 5: Combine phase — combiner() called (TREE REDUCTION)
        NOT sequential merging! Tree-shaped combining:
        
        Level 2 (leaf): A1=["ALICE"], A2=[], A3=["CHARLIE"], A4=["DIANA"]
        
        Level 1:        combiner(A1, A2):
                        A1.addAll(A2) → A1 = ["ALICE"]
                        returns A1          
                        combiner(A3, A4):
                        A3.addAll(A4) → A3 = ["CHARLIE", "DIANA"]
                        returns A3

        Level 0 (root): combiner(A1, A3):
                    A1.addAll(A3) → A1 = ["ALICE", "CHARLIE", "DIANA"]
                    returns A1
        
        ORDERED result maintained because ORDERED characteristic present in source!
        ForkJoinPool ensures left-then-right combine order when ORDERED.
        
        Final A = ["ALICE", "CHARLIE", "DIANA"]

    STEP 6: Finisher applied → result returned
        Same as sequential — IDENTITY_FINISH skips finisher call.
        return ["ALICE", "CHARLIE", "DIANA"]

```























