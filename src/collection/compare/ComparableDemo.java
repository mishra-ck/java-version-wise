package collection.compare;

import java.util.TreeSet;

public class ComparableDemo {
    public static void main(String[] args) {
        System.out.println("A".compareTo("A"));  // 0
        System.out.println("A".compareTo("B"));  // -ve
        System.out.println("B".compareTo("A"));  // +ve

        TreeSet<String> t = new TreeSet() ;
        t.add("K");   //  no comparison for 1 element
        System.out.println(t.add("Z"));
        System.out.println( t.add("A"));
        System.out.println(t.add("A"));; // will not be added
        System.out.println(t); ; // [A, K,Z]


    }
}
