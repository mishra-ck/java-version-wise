package collection.queue;

import java.util.PriorityQueue;
// PriorityQueue using Default Natural Sorting
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        System.out.println(queue.peek());  // null
//        System.out.println(queue.element());  // NoSuchElementException
        for (int i =0 ; i<10 ; i++){
            queue.offer(i);
        }
        System.out.println(queue);  // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(queue.poll());  // 0
        System.out.println(queue);  // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(queue.remove()); // 1
        System.out.println(queue);  // [2, 3, 4, 5, 6, 7, 8, 9]
    }
}
