package concurrency.practice;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
    ProducerConsumer pc = new ProducerConsumer();
        // create 2 threads producer and consumer
        Thread producer = new Thread(
                () -> {
                    try {
                        pc.produce();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
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
// This class have a producer and consumer
class ProducerConsumer{
    private Queue<Integer> queue = new LinkedList<>();
    private  final int capacity = 5 ;
    public void produce() throws InterruptedException {
        int size = 0 ;
        while(true){
            synchronized (this){
                while(queue.size() == capacity){
                    wait();
                }
                queue.add(size++);
                System.out.println("produced item : "+ size);
                notify();
            }
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException{
        while(true){
            synchronized (this){
                while(queue.isEmpty()){
                    wait();
                }
                int data = queue.remove();
                System.out.println("consumed data : "+data);
                notify();
            }
            Thread.sleep(1000);
        }
    }
}
