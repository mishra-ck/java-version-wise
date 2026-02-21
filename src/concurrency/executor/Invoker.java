package concurrency.executor;

import java.util.concurrent.Executor;
// Run a submitted task immediately after submitting
public class Invoker implements Executor {
    @Override
    public void execute(Runnable command) {
        System.out.println("Inside Invoker class to run execute()..");
        command.run();
    }
}

// Create a separate thread for each submitted task
class ThreadForEachTask implements  Executor{
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}



