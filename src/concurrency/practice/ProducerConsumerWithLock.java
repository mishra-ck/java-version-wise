package concurrency.practice;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    public static void main(String[] args) {
        ConsumerProducer pc = new ConsumerProducer();

            Thread producer = new Thread(
                    ()-> {
                        try {
                            pc.consume();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            ) ;
            Thread consumer = new Thread(
                    ()->{
                        try {
                            pc.consume();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            producer.start();
            consumer.start();


    }

}
class ConsumerProducer{
    private LinkedList buffer = new LinkedList();
    int bufferSize = 10 ;
    private final Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void produce() throws InterruptedException{
        int size = 0 ;
        while (true) {
            lock.lock();
            try {
                while (buffer.size() == bufferSize) {
                    notFull.await();
                }
                buffer.add(size++);
                System.out.println("Inside producer");
                notFull.signal();
            } finally {
                lock.unlock();
            }
            Thread.sleep(1000);
        }
    }
    public void consume() throws InterruptedException{
        while (true){
            lock.lock();
            try {
                while (buffer.isEmpty()){
                    notEmpty.await();
                }
                buffer.removeFirst();
                System.out.println("Inside consumer ");
                notEmpty.signal();
            }finally {
                lock.unlock();
            }
            Thread.sleep(1000);
        }
    }

}
