package versions.java8.collector;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorBasicsLevel {
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

        /** ---------  toList(), toSet(), toUnmodifiableList() ----------- */

        // Basic collection
        List<String> names = emp.stream()
                .map(Employee::name)
                .collect(Collectors.toList());

        // Distinct cities as Set (no duplicates, no order)
        Set<String> cities = emp.stream()
                .map(Employee::city)
                .collect(Collectors.toSet());

        // Immutable result
        List<String> immutableNames = emp.stream()
                .map(Employee::name)
                .collect(Collectors.toUnmodifiableList());
        // immutableNames.add("X") → UnsupportedOperationException

        // filter, map and then collect
        List<String> engineers = emp.stream()
                .filter(e -> e.department().equals("Engineering"))
                .filter(Employee::active)
                .map(Employee::name)
                .collect(Collectors.toList());


        /** -----------------  toMap() Method ------------------- */

        // Basic : name -> salary map
        Map<String, Double> salaryMap = emp.stream()
                .collect(Collectors.toMap(
                        Employee::name,   // key extract
                        Employee::salary  // value extract
                ));
        // toMap() throws IllegalStateException on Duplicate keys, this will crash if 2 employees have same name

        // SOLUTION : Provide merge function(3rd argument in toMap())
        Map<String, Double> salaryMap2 = emp.stream()
                .collect(Collectors.toMap(
                        Employee::name,   // key extract
                        Employee::salary,  // value extract
                        (first, duplicate) -> first   // keep the first
                ));

        // With MERGE keep the higher salary employee name on conflict
        Map<String, Double> maxSalaryMap = emp.stream()
                .collect(Collectors.toMap(
                        Employee::name,    // key extract
                        Employee::salary,  // value extract
                        Double::max       // keep higher value
                ));

        // Preserve INSERTION Order : LinkedHashMap implementation
        Map<String,Double> orderedMap = emp.stream()
                .sorted(Comparator.comparing(Employee::name))
                .collect(Collectors.toMap(
                        Employee::name,
                        Employee::salary,
                        (first, duplicate) -> first,   // keep the first
                        LinkedHashMap::new
                ));

        // Complex map : name -> Employee Object(Identity map)
        Map<String, Employee> empById = emp.stream()
                .collect(Collectors.toMap(
                   Employee::name,
                   Function.identity()     // Value is Employee itself
                ));

        // Transform Key and Value in Map
        Map<String, String> nameCityMap = emp.stream()
                .collect(Collectors.toMap(
                        e -> e.name().toLowerCase(),  // key: lowercase name
                        e -> e.city() + "( "+e.department() + " )"  // value : formatted
                ));

        /**  -----------  counting() Method ---------------  */

        // Total count (same as stream.count())
        long count = emp.stream().collect(Collectors.counting());

        // Count employee in each department
        Map<String, Long> headCount = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.counting()   // useful as downstream collector
                ));
        // {Engineering=5, HR=2, Marketing=3}

    }

}
