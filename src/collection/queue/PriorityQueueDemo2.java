package collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
// PriorityQueue using Customized Sorting via Comparator
public class PriorityQueueDemo2 {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>(15, new MyComparator());
        queue.offer("A");
        queue.offer("L");
        queue.offer("B");
        queue.offer("Z");
        System.out.println(queue);  // [Z, L, B, A]
    }
}
class MyComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        String s1 = (String)o1;
        String s2 = (String)o2;
        return s2.compareTo(s1);
    }
}
