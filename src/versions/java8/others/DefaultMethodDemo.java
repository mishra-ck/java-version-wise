package versions.java8.others;

public class DefaultMethodDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.service();
    }
}
interface Vehicle {
    void start();  // Abstract method
    // Default method with implementation
    default void service() {
        System.out.println("Performing standard service.");
    }
}
class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started.");
    }
    // Inherits service() automatically also we can override it if wanted
}