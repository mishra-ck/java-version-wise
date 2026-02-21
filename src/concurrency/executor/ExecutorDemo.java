package concurrency.executor;

import java.util.concurrent.Executor;

public class ExecutorDemo {
    public static void main(String[] args) {
        Executor executor = new Invoker();
        executor.execute(
                ()->{
                    for (int i =0 ; i < 10 ; i++ ){
                        System.out.println("Hello");
                    }
                }
        );
    }
}
