package concurrency.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class TaskRunner {
    private static ExecutorService executorService = null ; // Creating Executor Service reference
    public static void main(String[] args) {
        runTask();
    }
    public static void runTask(){
        executorService = Executors.newSingleThreadExecutor();  // initialize executor service
        EventLoggingTask eventLoggingTask = new EventLoggingTask(); // initialize task
        Future future = executorService.submit(eventLoggingTask); // submit task in thread pool
        executorService.shutdown();  // shutdown thread pool after completing task
    }

}


