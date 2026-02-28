package versions.java8.collector;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorMediumLevel {

    record Employee( String name,  String department,  double salary, int age, String city,  boolean active
    ) {}
    static List<Employee> employees() {
        return List.of(
                new Employee("Ram",   "Engineering", 95000, 32, "Delhi",  true),
                new Employee("Shyam",     "Engineering", 88000, 28, "Mumbai",  true),
                new Employee("Satya", "Marketing",   72000, 35, "Chennai",   true),
                new Employee("Mohit",   "Marketing",   68000, 29, "Kolkata",   false),
                new Employee("Sonu",     "Engineering", 105000,40, "Delhi",   true),
                new Employee("Golu",   "HR",          62000, 45, "Banglore",  true),
                new Employee("Geeta",   "HR",          58000, 33, "Mumbai",   false),
                new Employee("Sundar",   "Engineering", 91000, 27, "Delhi",  true),
                new Employee("Jay",    "Marketing",   76000, 31, "Chennai",   true),
                new Employee("Amit",    "Engineering", 99000, 36, "Mumbai",   true)
        );
    }
    public static void main(String[] args) {

        List<Employee> emp = employees();

        /** --------------- groupingBy() : The most powerful Collector -------------*/

        // Basic : grouping employee by department
        Map<String, List<Employee>> employeeByDept = emp.stream()
                .collect(Collectors.groupingBy(Employee::department));

        // Two-level grouping: department -> city -> employee
        Map<String, Map<String, List<Employee>>> deptByCity = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.groupingBy(Employee::city)  // nested grouping
                ));

        // Controlled Map : TreeMap for sorted keys
        Map<String, List<Employee>> sortedByDept = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        TreeMap::new,      // supplier for outer map
                        Collectors.toList()  // downstream
                ));

        /**  ------------ grouping with downstream Collectors  ----------------- */

        // Count per department
        Map<String, Long> deptCount = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.counting()
                ));

        // Average salary per department
        Map<String, Double> avgSalPerDept = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                ));

        // Sum of salaries per department
        Map<String,Double> salarySUmByDept = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingDouble(Employee::salary)
                ));

        // Collect employee names per department
        Map<String, List<String>> nameListByDept = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name,Collectors.toList())
                ));

        // Highest paid in each department
        Map<String, Optional<Employee>> highestPaid = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.maxBy(Comparator.comparing(Employee::salary))
                ));

        // Get Highest paid in each department : collectingAndThen
        Map<String,String> highestPaidName = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Employee::name)),
                                opt -> opt.map(Employee::name).orElse("NA")
                        )
                ));
        // {Engineering=Sundar, HR=Golu, Marketing=Satya}

        // Statistics per department (count + sum + min + max + avg in call)
        Map<String,DoubleSummaryStatistics> deptStats = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summarizingDouble(Employee::salary)
                ));


        /** --------------- partitioningBy()  method ---------------------  */

        // Simple partition: active vs inactive
        Map<Boolean, List<Employee>> activePartition = emp.stream()
                .collect(Collectors.partitioningBy(Employee::active));

        // Partition with downstream: count in each partition
        Map<Boolean,Long> activeCount = emp.stream()
                .collect(Collectors.partitioningBy(
                        Employee::active,
                        Collectors.counting()
                ));
        // O/P: {false=2, true=8}

        // partition High earners (> 80K), collect names
        Map<Boolean,List<String>> earnersName = emp.stream()
                .collect(Collectors.partitioningBy(
                        e -> e.salary() > 80000,
                        Collectors.mapping(Employee::name, Collectors.toList())
                ));
        // O/P: {false=[Satya, Mohit, Golu, Geeta, Jay], true=[Ram, Shyam, Sonu, Sundar, Amit]}

        // Group active vs inactive by department (nested)
        Map<String, Map<Boolean, List<Employee>>> deptByActive = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.partitioningBy(Employee::active)  // inner partition
                ));

        /**
         * KEY DIFFERENCE: partitioningBy vs groupingBy
         * partitioningBy ALWAYS returns Map<Boolean, ...> with EXACTLY 2 entries (true/false)
         * groupingBy returns Map<K, ...> with one entry per distinct key
         * Even if no elements match the false partition, false key STILL exists:
         */

    }
}
