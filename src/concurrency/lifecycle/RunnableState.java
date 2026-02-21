package concurrency.lifecycle;

public class RunnableState implements  Runnable{
    public static void main(String[] args) {
        Runnable runnable = new NeweState();
        Thread t = new Thread(runnable);
        t.start();
        System.out.println(t.getState()); // RUNNABLE
    }
    @Override
    public void run() {

    }
}
