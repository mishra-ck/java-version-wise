//package collection.list;
//
//import basic.concepts.dsa.Employee;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class LinkedlistDemo {
//    public static void main(String[] args)
//    {
//
//        LinkedList l  = new LinkedList() ;
//        l.add("chanchal") ;
//        l.add( 25) ;
//        l.add(null) ;
//        l.add("Bansi") ;
//
//        // get() and set()
//        System.out.println(l.get(3) );  // Bansi
//        l.set(0, "Software") ;
//        System.out.println(l); // [Software, 25, null, Bansi]
//
//        l.add(0 , "Mishra") ;
//        System.out.println(l); // [Mishra, Software, 25, null, Bansi]
//
//        // addFirst()  , addLast()
//        l.addFirst("Employed");
//        l.addLast("TCS");
//        System.out.println(l);  // [Employed, Mishra, Software, 25, null, Bansi, TCS]
//
//        // peek() , peekFirst() , peekLast()
//        System.out.println(l.peek());    // returns first element
//        System.out.println(l.peekFirst()); // returns first element
//        System.out.println(l.peekLast());  // returns last element
//
//        // poll, pollFirst() , pollLast()
//        System.out.println(l.poll());  // removes first element and returns it
//        System.out.println(l.pollFirst());  // removes first element and returns it
//        System.out.println(l.pollLast());   // removes last element and returns it
//
//        System.out.println(l);
//
//        l.removeLast();
//        l.addFirst("CC");
//        System.out.println(l); // [CC, Mishra, Software, 25, null]
//
//        List<Employee> ll = new LinkedList<>();
//        ll.add(new Employee(1,"Avish"));
//        ll.add(new Employee(2,"Avish2"));
//        ll.add(new Employee(3,"Avish3"));
//        ll.add(new Employee(4,"Avish4"));
//        ll.add(new Employee(5,"Avish5"));
//        System.out.println(ll);
//        ll.set(0,new Employee(7,"thala")) ;
//        System.out.println(ll);
//        ll.set(1,new Employee(8,"thala2")) ;
//        System.out.println(ll);
//
//
//    }
//
//}
