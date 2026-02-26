##**Basic and Medium Level**:-

```java
/**
* Sum of squares: Calculate the sum of squares of the first 10,000,000 integers
* using a parallel stream.
  */
  long sum = LongStream.rangeClosed(1, 10_000_000)
  .parallel()
  .map(n -> n * n).sum();


/**
* Filtering and Collecting : Given a list of 1 million Strings, filter
* those that start with "C" and convert them to uppercase in parallel.
  */
  List<String> result = words.parallelStream()
  .filter(s -> s.startsWith("C"))
  .map(String::toUpperCase)
  .collect(Collectors.toList());

/**
* The "Stateful" trap :
* Identify why this code is dangerous in parallel and fix it:
* List<Integer> list = new ArrayList<>(); stream.parallel().forEach(list::add)
* Issue: ArrayList is not thread-safe. forEach in parallel will cause race conditions.
* Fix: Use collect() which is designed to be thread-safe for parallel operations.
  */
  List<Integer> list = stream.parallel().collect(Collectors.toList());

/**
* Prime Discovery: Count primes between 1 and 500,000.
  */
  long count = LongStream.rangeClosed(2, 500_000)
  .parallel()
  .filter(n -> LongStream.rangeClosed(2, (long)Math.sqrt(n)).noneMatch(i -> n % i == 0))
  .count();


/**
* Custom Parallelism : By default, parallel streams use one core.
* How do you run a parallel stream using exactly 4 threads?
  */
  ForkJoinPool customPool = new ForkJoinPool(4);
  long result = customPool.submit(() ->
  list.parallelStream().reduce(0, Integer::sum)
  ).get();


/**
* Searching in Large Dataset: Find any string that matches a complex regex in a massive list.
* findAny is much faster in parallel than findFirst.
  */
  Optional<String> match = largeList.parallelStream()
  .filter(s -> s.matches("^[0-9]-.*"))
  .findAny();


/**
* Grouping Data : Given a list of Employee objects, group them by Department using a parallel stream.
* groupingByConcurrent is optimized for parallel streams.
  */
  Map<Department, List<Employee>> byDept = employees.parallelStream()
  .collect(Collectors.groupingByConcurrent(Employee::getDepartment));


/**
* Finding "Top K" elements: Find the top 10 most expensive products in a list of 2 million.
  */
  public List<Product> getTop10(List<Product> products) {
  return products.parallelStream()
  .sorted(Comparator.comparing(Product::getPrice).reversed())
  .limit(10)
  .collect(Collectors.toList());
  }
```

```java

##Real World Applications:-

/**
* Batch Image Processing : Process a list of 50,000 local file paths to extract EXIF data
* and filter only images taken in "New Delhi".
  */
  public List<Path> getNewDelhiImages(List<Path> imagePaths) {
  return imagePaths.parallelStream()
  .filter(path -> {
  String location = ImageUtils.getExifLocation(path); // Blocking I/O check
  return "New Delhi".equalsIgnoreCase(location);
  })
  .collect(Collectors.toList());
  }


/**
* Custom Parallelism for Blocking Task: You need to ping 500 servers.
* Using the commonPool might block other system tasks. Run this in a custom thread pool.
  */
  public void pingServers(List<String> urls) throws Exception {
  ForkJoinPool customPool = new ForkJoinPool(20); // 20 threads for I/O
  customPool.submit(() ->
  urls.parallelStream().forEach(url -> NetworkUtils.ping(url))
  ).get();
  }


/**
* Aggregating Sales Data: Group 5,000,000 Transaction objects by Region and calculate the average sale price.
  */
  public Map<String, Double> averageByRegion(List<Transaction> txns) {
  return txns.parallelStream()
  .collect(Collectors.groupingByConcurrent(
  Transaction::getRegion,
  Collectors.averagingDouble(Transaction::getAmount)
  ));
  }


/**
* Large Data Duplication: You have a stream of user activity logs.
* Keep only the first entry for each userId to see when they first joined the platform today.
  */
  public Collection<Log> firstOccurrences(List<Log> logs) {
  return logs.parallelStream()
  .collect(Collectors.toMap(
  Log::getUserId,
  l -> l,
  (existing, replacement) -> existing, // Keep the first (if ordered)
  ConcurrentHashMap::new
  )).values();
  }


/**
* Concurrent Log Analysis: Parse a 2GB log file (represented as a `List<String>`)
* to find unique IP addresses that attempted a SQL injection.
  */
  public Set<String> findMaliciousIPs(List<String> logLines) {
  Pattern pattern = Pattern.compile(".*SELECT.*FROM.*WHERE.*");
  return logLines.parallelStream()
  .filter(pattern.asPredicate())
  .map(line -> line.split(" ")[0]) // Assuming IP is first column
  .collect(Collectors.toConcurrentSet());
  }

```java
/**
* Batch Image Processing : Process a list of 50,000 local file paths to extract EXIF data 
* and filter only images taken in "New Delhi".
*/
public List<Path> getNewDelhiImages(List<Path> imagePaths) {
    return imagePaths.parallelStream()
            .filter(path -> {
                String location = ImageUtils.getExifLocation(path); // Blocking I/O check
                return "New Delhi".equalsIgnoreCase(location);
            })
            .collect(Collectors.toList());
}


/**
* Custom Parallelism for Blocking Task: You need to ping 500 servers. 
* Using the commonPool might block other system tasks. Run this in a custom thread pool.
*/
public void pingServers(List<String> urls) throws Exception {
    ForkJoinPool customPool = new ForkJoinPool(20); // 20 threads for I/O
    customPool.submit(() -> 
        urls.parallelStream().forEach(url -> NetworkUtils.ping(url))
    ).get();
}


/**
* Aggregating Sales Data: Group 5,000,000 Transaction objects by Region and calculate the average sale price.
*/
public Map<String, Double> averageByRegion(List<Transaction> txns) {
    return txns.parallelStream()
            .collect(Collectors.groupingByConcurrent(
                Transaction::getRegion,
                Collectors.averagingDouble(Transaction::getAmount)
            ));
}


/**
* Large Data Duplication: You have a stream of user activity logs. 
* Keep only the first entry for each userId to see when they first joined the platform today.
*/
public Collection<Log> firstOccurrences(List<Log> logs) {
    return logs.parallelStream()
            .collect(Collectors.toMap(
                Log::getUserId,
                l -> l,
                (existing, replacement) -> existing, // Keep the first (if ordered)
                ConcurrentHashMap::new
            )).values();
}


/**
* Concurrent Log Analysis: Parse a 2GB log file (represented as a `List<String>`) 
* to find unique IP addresses that attempted a SQL injection.
*/
public Set<String> findMaliciousIPs(List<String> logLines) {
    Pattern pattern = Pattern.compile(".*SELECT.*FROM.*WHERE.*");
    return logLines.parallelStream()
            .filter(pattern.asPredicate())
            .map(line -> line.split(" ")[0]) // Assuming IP is first column
            .collect(Collectors.toConcurrentSet()); 
}
```

        
        
        
## Golden Rules & Anti-Patterns

### ✅ DO

```java
// 1. Use thread-safe collectors
.collect(Collectors.groupingByConcurrent(...))
.collect(Collectors.toConcurrentMap(...))

// 2. Use reduce for aggregation (no shared state)
.reduce(0, Integer::sum)

// 3. Use Atomic vaiables for counters
AtomicLong counter = new AtomicLong();
.forEach(x -> counter.incrementAndGet())

// 4. Use ConcurrentHashMap for shared maps
Map<K, V> map = new ConcurrentHashMap<>();
.forEach(x -> map.computeIfAbsent(x.key(), k -> new ConcurrentLinkedQueue<>()).add(x))

// 5. Use ThreadLocalRandom (not Math.random() — it has global lock!)
ThreadLocalRandom.current().nextDouble()

// 6. Use custom ForkJoinPool in server environments
new ForkJoinPool(n).submit(() -> list.parallelStream()...).get()
```

### ❌ DON'T

```java
// 1. Don't mutate shared collections
List<T> result = new ArrayList<>();
list.parallelStream().forEach(result::add);  // Race condition!

// 2. Don't use wrong reduce identity
.reduce(10, Integer::sum)  // 10 applied per thread!

// 3. Don't do nested parallel streams
outer.parallelStream().map(x ->
    inner.parallelStream()...  // Pool starvation!
)

// 4. Don't assume ordering
.parallelStream().forEach(System.out::println)  // Random order!
// Use forEachOrdered() if order matters

// 5. Don't use for I/O-bound work
.parallelStream().map(url -> httpGet(url))  // Threads blocked, no gain!
// Use CompletableFuture with a dedicated thread pool instead

// 6. Don't ignore thread safety of intermediate ops
.parallelStream().distinct()  // OK - Stream handles internally
.parallelStream().sorted()    // OK but forces sequential merge — slow!
```
