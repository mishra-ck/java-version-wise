package concurrency.threadbasic;

// concurrency.practice.Demo of how to prevent a Thread from Execution
// 3 methods : yield() , join() , sleep()
// In all these method calls thread will never release lock
public class ThreadPreventionDelay {
    public static void main(String[] args) {
        System.out.println(10*20);
    }
}
