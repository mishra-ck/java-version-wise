package collection.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {

        Set<String> hash = new HashSet<>();
        hash.add("Avish");
        hash.add("TCS");
        hash.add("avish");
        hash.add("TCS");  // duplicates are not inserted
        hash.add(null);  // one null is possible
        hash.add("aa");
        System.out.println(hash); // [null, aa, Avish, TCS, avish]

        hash.remove("aa");// true
        System.out.println(hash.isEmpty()); ; // false
        System.out.println(hash.size());;  // 4


    }
}
