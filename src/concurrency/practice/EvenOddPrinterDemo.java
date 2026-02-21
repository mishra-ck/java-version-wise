package concurrency.practice;

public class EvenOddPrinterDemo {
    public static void main(String[] args) {
        EvenOddPrinter ev = new EvenOddPrinter();
        Thread even = new Thread(ev::printEven,"Even Thread");
        Thread odd = new Thread(ev::printOdd,"Odd Thread");

        even.start();
        odd.start();
    }
}
class EvenOddPrinter{
    private static int counter = 1;

    public void printEven(){
        while(true){
            synchronized (this){
                while(counter <= 10){
                    if(counter%2 == 0){
                        System.out.println("printing even :" + counter);
                        counter++ ;
                        this.notify();
                    }else{
                        try {
                            this.wait();
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
    public void printOdd(){
        while(true){
            synchronized (this){
                while(counter <= 10){
                    if(counter %2 != 0){
                        System.out.println("printing odd : "+ counter);
                        counter++ ;
                        this.notify();
                    }else{
                        try {
                            this.wait();
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

}
