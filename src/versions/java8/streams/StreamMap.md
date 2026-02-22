## **Map Operations - Data Transformation**

**Purpose**: Transform each element to a different value or type.

```java
// Simple transformations
List<String> upperCaseNames = employees.stream()
    .map(Employee::getName)
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Type transformation
List<Integer> nameLengths = employees.stream()
    .map(Employee::getName)
    .map(String::length)
    .collect(Collectors.toList());

// Complex business transformation
List<EmployeeSummary> summaries = employees.stream()
    .map(emp -> new EmployeeSummary(
        emp.getName(),
        emp.getDepartment(),
        calculateTotalCompensation(emp),
        determineLevel(emp.getAge(), emp.getSalary())
    ))
    .collect(Collectors.toList());

// Specialized numeric mappings
IntSummaryStatistics salaryStats = employees.stream()
    .mapToInt(emp -> (int) emp.getSalary())  // Avoid boxing
    .summaryStatistics();

System.out.println("Average salary: " + salaryStats.getAverage());
System.out.println("Max salary: " + salaryStats.getMax());
```

**Advanced Mapping Patterns**:

- **flatMap()**: Flatten nested structures
- **mapToInt/Double/Long()**: Avoid boxing for primitives
- **Method References**: More concise than lambdas

