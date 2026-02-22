
## **Reduce Operations - Data Aggregation**

**Purpose**: Combine stream elements into a single result.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Basic reduction
Optional<Integer> sum = numbers.stream()
    .reduce(Integer::sum);
// Result: Optional

// Reduction with identity value
Integer sumWithIdentity = numbers.stream()
    .reduce(0, Integer::sum);
// Result: 15 (never Optional)

// Complex business reduction
double totalSalary = employees.stream()
    .map(Employee::getSalary)
    .reduce(0.0, Double::sum);

// String concatenation with reduce
String allNames = employees.stream()
    .map(Employee::getName)
    .reduce("Employees: ", (acc, name) -> acc + name + " ");

// Custom accumulator and combiner for parallel processing
String departmentSummary = employees.parallelStream()
    .reduce("",
        (partial, emp) -> partial + emp.getName() + "(" + emp.getDepartment() + ") ",
        String::concat  // Combiner for parallel streams
    );
```

**Specialized Reductions**:

```java
// Built-in terminal operations are optimized reductions
long count = employees.stream().count();
OptionalDouble average = employees.stream()
        .mapToDouble(Employee::getSalary)
        .average();
Optional<Employee> maxSalary = employees.stream()
        .max(Comparator.comparing(Employee::getSalary));
boolean anyHighEarners = employees.stream()
        .anyMatch(emp -> emp.getSalary() > 100000);
```
