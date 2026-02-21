package concurrency.future;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        SquareCalculator calculator = new SquareCalculator(service);
        Future<Integer> future =  calculator.calculateSquare(15);

        future.cancel(false);  // attempts to cancel the execution

        try {
            future.get(5, TimeUnit.SECONDS);  // waits for the given time frame in this case 5 seconds
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
        } catch (ExecutionException e) {
                throw new RuntimeException(e);
        } catch (TimeoutException e) {
                throw new RuntimeException(e);
        }

        if(future.isDone() && !future.isCancelled()){  // returns true if the task is done and not cancelled
            try {
                Integer num = future.get();
                System.out.println(" Square root is : "+ num );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class SquareCalculator{
    private final ExecutorService executorService ;
    SquareCalculator(ExecutorService executorService){
        this.executorService = executorService ;
    }
    public Future<Integer> calculateSquare(int num){
        return executorService.submit(
                () ->{
                    Thread.sleep(1000);
                    return num * num ;
                }
        );
    }
}
