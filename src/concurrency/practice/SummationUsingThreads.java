package concurrency.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SummationUsingThreads {
    /**
     * Problem : sum from 1 to 100
     *     divide it into 10 subtasks
     *     int i = 1;
     *     task1 : 1 to 10
     *     task2 : 11 to 20
     *             ..
     *     task10 : 91 to 100
     * */
    public static void main(String[] args) {
        int startRange = 1 ;
        int endRange = 100 ;
        int sum = 0 ;

        ExecutorService executors = Executors.newFixedThreadPool(10);
            executors.execute( new Task(1,10));
            
        }
    }

class Task implements Runnable {
    int start ;
    int end ;
    public void run(){
        int result = 0 ;
        for(int i=start ;i<=end;i++ ){
            result += i ;
        }
    }
    Task(int start , int end){
        this.start= start ;
        this.end= end ;
    }
}