package concurrency.misc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

// Controlling execution of thread
public class ControlSubThread implements Runnable {
    private Thread worker ;
    private int interval = 100 ;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean stopped = new AtomicBoolean(true);

    public ControlSubThread(int sleepInterval){
        interval = sleepInterval ;
    }
    public void start(){    // start a new worker thread
        worker = new Thread();
        worker.start();
    }
    public void stop(){  // stop the running worker thread
        running.set(false);
    }
    public void interrupt(){   // interrupt the running worker thread
        running.set(false);
        worker.interrupt();
    }
    public boolean isRunning(){  // check if the worker is running
        return running.get();
    }
    public boolean isStopped(){  // cha=eck if the worker has stopped
        return  stopped.get();
    }
    @Override
    public void run() {
        running.set(true);
        stopped.set(false);
        while (running.get()){
            try {
                worker.start();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                worker.interrupt();
               Thread.currentThread().interrupt();
                System.out.println("This thread is interrupted..Failed to complete operation");
            }finally {
                System.out.println(" Worker thread has completed its task now it is about to stop ");
                worker.stop();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<?> future = service.submit(new ControlSubThread(500));
        future.cancel(false);
        if(future.isDone() && !future.isCancelled()){
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
