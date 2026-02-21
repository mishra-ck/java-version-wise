package versions.java8.lambda;
/**
 * Fundamentals and Syntax of Lambda Expressions
 */
@FunctionalInterface
interface Greeting{
    void sayHello();
}
@FunctionalInterface
interface Printer{
    void print(String str);
}
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}
public class LambdaExpDemo2 {
    public static void main(String[] args) {

        // No Parameter
        // () -> System.out.println("Hello World..!");
        Greeting greeting = () -> System.out.println("Hello World..!!");
        greeting.sayHello();

        // One parameter
        // str -> System.out.println(str);
        Printer printer = str -> System.out.println(str);
        printer.print("Hello world..!");

        // Multiple parameter
        MathOperation addition = (a,b) -> a+b ;
        System.out.println("Addition is : "+addition.operate(10,20));

        // Multiple statements
        /**
         * (a,b) -> {
         *     int result = a+b ;
         *     return result ; }
         */
        MathOperation subtract = (x,y) -> {
          int result = x-y ;
          return result ;
        };
        System.out.println("Difference is : "+ subtract.operate(20,10));
    }
}
