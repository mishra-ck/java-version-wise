package versions.java8.others;

public class StaticMethodDemo {
    public static void main(String[] args) {
        System.out.println(MathUtils.add(10,20));
        System.out.println(MathUtils.isEven(23));
    }
}
interface MathUtils {
    // Static method - utility
    static int add(int a, int b) {
        return a + b;
    }
    static boolean isEven(int num) {
        return num % 2 == 0;
    }
}