## **Advanced Collectors and Grouping**

### **Statistical Collectors**

```java
// Comprehensive statistics
DoubleSummaryStatistics salaryStats = employees.stream()
    .collect(Collectors.summarizingDouble(Employee::getSalary));

System.out.println("Count: " + salaryStats.getCount());
System.out.println("Average: " + salaryStats.getAverage());
System.out.println("Min: " + salaryStats.getMin());
System.out.println("Max: " + salaryStats.getMax());
System.out.println("Sum: " + salaryStats.getSum());
```


### **Grouping and Partitioning**

```java
// Basic grouping
Map<String, List<Employee>> byDepartment = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));

// Multi-level grouping
Map<String, Map<Boolean, List<Employee>>> complexGrouping = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.partitioningBy(emp -> emp.getSalary() > 70000)
    ));

// Grouping with downstream collectors
Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));

// Custom collectors
Map<String, String> deptSummary = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.mapping(
            emp -> emp.getName() + "($" + emp.getSalary() + ")",
            Collectors.joining(", ")
        )
    ));
```

## **Lazy Evaluation and Performance Optimization**

### **Deferred Execution**

Intermediate operations are **lazy** - they don't execute until a terminal operation is called:

```java
Stream<String> lazyStream = employees.stream()
    .filter(emp -> {
        System.out.println("Filtering: " + emp.getName());  // Won't print yet
        return emp.getSalary() > 70000;
    })
    .map(emp -> {
        System.out.println("Mapping: " + emp.getName());    // Won't print yet
        return emp.getName().toUpperCase();
    });

System.out.println("Stream created, but no processing yet!");

// Now trigger execution
List<String> result = lazyStream.collect(Collectors.toList());
// Only now will the filter and map operations execute
```


### **Short-Circuiting Operations**

Some operations can terminate early:

```java
// findFirst() - stops after finding first match
Optional<Employee> firstHighEarner = employees.stream()
    .filter(emp -> emp.getSalary() > 100000)
    .findFirst();  // Stops processing once found

// anyMatch() - stops when condition is met
boolean hasHighEarners = employees.stream()
    .anyMatch(emp -> emp.getSalary() > 100000);

// limit() - processes only specified number of elements
List<String> first3Names = employees.stream()
    .map(Employee::getName)
    .limit(3)
    .collect(Collectors.toList());
```
