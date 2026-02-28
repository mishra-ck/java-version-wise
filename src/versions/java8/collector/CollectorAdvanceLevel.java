package versions.java8.collector;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorAdvanceLevel {
    record Employee( String name,  String department,  double salary, int age, String city,  boolean active
    ) {}
    record ProjectAssignment(String employee, List<String> projects) {}
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
    static List<ProjectAssignment> assignments = List.of(
            new ProjectAssignment("Ram",   List.of("SBI", "HDFC")),
            new ProjectAssignment("Shyam", List.of("Apollo", "PNB")),
            new ProjectAssignment("Jay", List.of("TCS","IBM","Tesla")),
            new ProjectAssignment("Amit",   List.of("SBI", "TCS", "PNB"))
    );
    public static void main(String[] args) {
        List<Employee> emp = employees();

        /** -------- Downstream Collectors  ------------- ***/
        /** mapping(): TRANSFORM element THEN collect, mostly used with groupingBy()  */

        // Collect department name as Set
        Set<String> deptName = emp.stream()
                .collect(Collectors.mapping(
                        Employee::department,Collectors.toSet()
                ));
        // [Engineering, HR, Marketing]

        // group department present in different cities
        Map<String,Set<String>> citiesPerDept = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::city,Collectors.toSet())
                ));
        // {Engineering=[Delhi, Mumbai], HR=[Banglore, Mumbai], Marketing=[Chennai, Kolkata]}

        /**  ---------  flatMapping() Java 9+ -------------- */
         // All unique projects per employee group : Without flatMapping() -> nested list
         Map<String,List<List<String>>> nested = assignments.stream()
                 .collect(Collectors.groupingBy(
                         a -> a.employee(),
                         Collectors.mapping(ProjectAssignment::projects,Collectors.toList())
                 ));
         // {Shyam=[[Apollo, PNB]], Jay=[[TCS, IBM, Tesla]], Amit=[[SBI, TCS, PNB]], Ram=[[SBI, HDFC]]}

         // With FLatMapping()
         Map<String,Set<String>> uniqueProject = assignments.stream()
                 .collect(Collectors.groupingBy(
                         a -> a.employee,
                         Collectors.flatMapping(
                                 a -> a.projects().stream(),Collectors.toSet()
                         )
                 ));
         // {Shyam=[PNB, Apollo], Jay=[TCS, IBM, Tesla], Amit=[TCS, PNB, SBI], Ram=[HDFC, SBI]}

         /** --------------  filtering() Java 9+ ------------- */

        // filtering: filter WITHIN a downstream context
        // Without filtering — Employees earning > 80K department wise
        Map<String,List<Employee>> highEarnersByDept = emp.stream()
                .filter(e -> e.salary() > 80000)
                .collect(Collectors.groupingBy(Employee::department));

        // With Filtering
        Map<String,List<Employee>> highEarnersByDept2 = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.filtering(
                                e ->e.salary() >80000,
                                Collectors.toList())
                ));

        // Higher earner count by department > 80K
        Map<String,Long> highEarnerCountByDept = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.filtering(
                                e -> e.salary() > 80000,
                                Collectors.counting()
                        )
                ));
        // {Engineering=5, HR=0, Marketing=0}

        /**  --------------  collectingAndThen()  ------------------  ***/
        // collectingAndThen: apply a FINISHER function to a collector's result
        // Collector<T, A, RR> collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher)

        // Wrap Result into a unmodifiable list
        List<String> immutable = emp.stream()
                .map(Employee::name)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList  // finisher function
                ));

        // Get count as int (counting() returns Long, convert to int)
        int count = emp.stream().filter(Employee::active)
                .collect(Collectors.collectingAndThen(
                        Collectors.counting(),
                        Long::intValue   // finisher function
                ));
        // O/P : 8

        // Convert List to Array
        String[] nameArray = emp.stream()
                .map(Employee::name)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.toArray(String[]::new)   // finisher function
                ));

        // Group by dept, then sort each group by salary, Each department's list is sorted by salary descending
        Map<String,List<Employee>> sortedGroup = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Employee::salary).reversed())
                                        .collect(Collectors.toList())
                        )
                ));


        /** ----------- String & Joining Collectors  ----------------- */

        List<String> words = List.of("Java", "Stream", "Collectors", "API");

        // Simple Join
        String joined = words.stream().collect(Collectors.joining());
        // JavaStreamCollectorsAPI

        // With Delimiter
        String joinWithDelimiter = words.stream()
                .collect(Collectors.joining(", "));
        // Java, Stream, Collectors, API

        // With delimiter, prefix, suffix
        String formatted = words.stream()
                .collect(Collectors.joining(", ","[","]"));
        // [Java, Stream, Collectors, API]

        // SQL IN clause generation
        List<Integer> ids = List.of(1, 2, 3, 4, 5);
        String inClause = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ","WHERE id in (",")"));
        // WHERE id in (1, 2, 3, 4, 5)

        // Department roster report
        String report = emp.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name,Collectors.joining(", "))
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e ->e.getKey()+ ":"+ e.getValue())
                .collect(Collectors.joining("\n"));
        System.out.println(report);

        // Engineering:Ram, Shyam, Sonu, Sundar, Amit
        // HR:Golu, Geeta
        // Marketing:Satya, Mohit, Jay

















    }
}
