package concurrency.callable;

import java.util.concurrent.*;

public class FactorialTask implements Callable<Integer> {
    int num ;
    public FactorialTask(int num) {
        this.num = num;
    }
    @Override
    public Integer call() throws Exception {
        int fact = 1 ;
        if(num < 0) {
            throw new InvalidParamterExceptionn("Number cannot be less than Zero");
        }
        for(int i = num ; i>1 ;i--){
            fact = fact * i ;
        }
        return fact ;
    }

    private class InvalidParamterExceptionn extends Exception{
        public  InvalidParamterExceptionn(String message){
            super(message);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FactorialTask task = new FactorialTask(5);
        Future future = executorService.submit(task);
        System.out.println(future.get());
        executorService.shutdown();
    }
}
