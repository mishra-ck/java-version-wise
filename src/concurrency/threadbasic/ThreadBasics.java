package concurrency.threadbasic;

/** Implementation of thread using Thread Class and Runnable interface
    NOTE : While using Runnable interface we have to pass the Runnable Object into a Thread object.
 */
public class ThreadBasics {
    public static void main(String[] args) {
        MyThreadUsingThread t1 = new MyThreadUsingThread();
        t1.start();
        System.out.println("Current Thread is : "+Thread.currentThread().getName());
        MyThreadUsingRunnable r1 = new MyThreadUsingRunnable();
        Thread t2 = new Thread(r1) ;
        t2.start();
        Thread t3 = new Thread(() -> System.out.println("Anonymous class thread : " + Thread.currentThread().getName()));t3.start();
        Runnable r2 = () -> System.out.println("Anonymous Runnable Class : "+ Thread.currentThread().getName());
        Thread t4 = new Thread(r2);
        t4.start();

        // Thread priority
        System.out.println(Thread.currentThread().getPriority());
    }
}
class MyThreadUsingThread extends Thread {
    @Override
    public void run(){
        System.out.println("Current Thread is : " + Thread.currentThread().getName() +" and priority is : " + Thread.currentThread().getPriority());
    }
}
class MyThreadUsingRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println( "Current Thread is : " + Thread.currentThread().getName() + " and priority is "+ Thread.currentThread().getPriority());
        // we can also change the priority of thread
        Thread.currentThread().setPriority(10);
        System.out.println("changed thread priority is : " + Thread.currentThread().getPriority());
    }
}

