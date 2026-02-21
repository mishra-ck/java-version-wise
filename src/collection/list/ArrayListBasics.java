//package collection.list;
//
//import basic.concepts.dsa.Employee;
//
//import java.time.LocalDate;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * concurrency.practice.Demo of array list methods and usages
// */
//public class ArrayListBasics {
//    public static void main(String[] args) {
//
//        List<String> list = new ArrayList<>();
//        list.add("Ram");
//        list.add("Shyam");
//        list.add("Sita");
//        list.add("Ram");
//        System.out.println(list);
//        System.out.println(list.get(0));
//        System.out.println(list.remove("Ram")); /// true
//        System.out.println(list);
//        //returns old value
//        String str = list.set(2,"Geeta");  // set() returns the old value while update the element
//        System.out.println(str); // Sita
//
//        System.out.println(list.size());
//
//        list.add("Ravan");
//        System.out.println(list);
//        list.remove("Ravan");
//        list.remove(1);
//
//        System.out.println(list);
//
//       Object[] arr =  list.toArray(); // convert into an object array
//        list.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return 0;
//            }
//        });
//
//        Iterator<String> it = list.iterator();
//        for(String st : list){
//            while(it.hasNext()){
//                String s1 = it.next() ;
//                System.out.println("String of a String is : "+ s1);
//            }
//        }
//
//        List<String> syncList = Collections.synchronizedList(list);  // will return sync list
//
////        list.stream().filter(s2 -> s2.equals("Shyam")).forEach(System.out::println);
//
//        List<Integer> intLIst = new ArrayList<>();
//        List<String> arrayList = Arrays.asList("Avish","Chanchal","Rajeev","Billu");
//
//        int[] arr2 = {10,100,30,40,50,11,15};
//        String[] strArr = {"Avish","Chanchal","Rajeev","Billu"} ;
//        Arrays.sort(arr2);
//        Arrays.sort(strArr);
////        Arrays.stream(arr2).forEach(System.out::println);
////        Arrays.stream(strArr).forEach(System.out::println);
//
//
//        List<Employee> emplist = new ArrayList<>() ;
//        emplist.add(new Employee(1,"Avish","CS", 50000, 26));
//        emplist.add(new Employee(5,"Ram","IT", 40000, 26));
//        emplist.add(new Employee(3,"Shyam","ME", 60000, 25));
//        emplist.add(new Employee(4,"Rajeev","IT", 70000, 29));
//        emplist.add(new Employee(6,"Babu","CY", 80000, 28));
//
//         emplist.stream()
//                .sorted(Comparator.comparing(Employee::getAge))
//                .forEach(System.out::println);
//
//    }
//
//}
