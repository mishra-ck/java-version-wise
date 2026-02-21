package concurrency.lifecycle;

public class NeweState implements Runnable{

    public static void main(String[] args) {
        Runnable runnable = new NeweState();
        Thread thread = new Thread(runnable);
        System.out.println(thread.getState()); // NEW
    }
    @Override
    public void run(){

    }
}
