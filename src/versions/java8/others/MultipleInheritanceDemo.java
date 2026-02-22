package versions.java8.others;

public class MultipleInheritanceDemo {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.move();
    }
}
interface Flyable {
    default void move() {
        System.out.println("Flying");
    }
}
interface Swimmable {
    default void move() {
        System.out.println("Swimming");
    }
}
class Duck implements Flyable, Swimmable {
    @Override
    public void move() {
        Flyable.super.move();  // Choose one
        System.out.println("Can fly and Swim..!");
    }
}
