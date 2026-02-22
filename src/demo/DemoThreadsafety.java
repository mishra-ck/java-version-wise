package demo;

import java.util.Arrays;
import java.util.List;

public  class DemoThreadsafety {

    private final List<String> addresses = Arrays.asList("New Delhi", "Mumbai");
    
    public static void main(String[] args) {
        DemoThreadsafety demo = new DemoThreadsafety();
        demo.print();
    }

    public List<String> print(){
        addresses.add("Kolkata");
        addresses.add("Banguluru");
        System.out.println(addresses);
        return addresses ;
    }

}


interface A{

}
class B implements A{
    public static void main(String[] args) {

    }
}