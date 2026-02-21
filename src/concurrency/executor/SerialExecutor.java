package concurrency.executor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.Executor;

// When we want to impose some sort of order in next task's execution
public class SerialExecutor implements Executor {
    final Queue<Runnable> taskList = new ArrayDeque<>();
    final Executor executor ;
    private Runnable active ;
    public SerialExecutor(Executor executor) {
        this.executor = executor;
    }
    @Override
    public void execute(Runnable r){
        // Add a task and run
        taskList.add(
                ()->{
                    try{
                        r.run();
                    }finally {
                        scheduleNext();
                    }
                }
        );
        // if runnable task is empty/null
        if(active == null){
            scheduleNext();
        }
    }
    protected synchronized void scheduleNext(){
        if((active = taskList.poll()) != null){
            executor.execute(active);
        }
    }
}
