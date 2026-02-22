## **Filter Operations - Selective Processing**

**Purpose**: Select elements based on boolean predicates.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Basic filtering
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
// Result: [2, 4, 6, 8, 10]

// Multiple filter conditions
List<Integer> filtered = numbers.stream()
    .filter(n -> n > 3)              // Greater than 3
    .filter(n -> n % 2 == 0)         // Even numbers
    .collect(Collectors.toList());
// Result: [4, 6, 8, 10]

// Complex business filtering
List<Employee> seniorEngineers = employees.stream()
    .filter(emp -> "Engineering".equals(emp.getDepartment()))
    .filter(emp -> emp.getAge() > 30)
    .filter(emp -> emp.getSalary() > 80000)
    .collect(Collectors.toList());
```

**Performance Characteristics**:

- **Time Complexity**: O(n) - each element evaluated once
- **Space Complexity**: O(1) for filtering predicate
- **Parallel Benefits**: Excellent - independent element evaluation
