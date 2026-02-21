package concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        SemaphorTask task = new SemaphorTask();
        task.execute();
    }
}
class SemaphorTask {
    static Semaphore semaphore = new Semaphore(10);
    public void execute(){
        System.out.println("Number of available permits : " + semaphore.availablePermits());
        System.out.println("Thread waiting to acquire : "+ semaphore.getQueueLength());
        System.out.println("Is defined semaphore is fair : " + semaphore.isFair());

        if(semaphore.tryAcquire()){
            try{
                // perform an operation
                System.out.println("acquired permission of semaphore");
            }finally {
                System.out.println("releasing permission of semaphore");
                semaphore.release();
            }
        }

    }
}