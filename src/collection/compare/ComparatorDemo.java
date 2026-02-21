package collection.compare;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorDemo {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>(new CustomCompare());
        set.add(10);
        set.add(5);
        set.add(15);
        set.add(30);
        set.add(25);
        set.add(0);
        System.out.println(set);
    }
}

class CustomCompare implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
