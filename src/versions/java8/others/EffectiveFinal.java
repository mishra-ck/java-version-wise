package versions.java8.others;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// Effective Final variable concept
public class EffectiveFinal {
    public static void main(String[] args) {
        /**
         * Before Java 8, If you wanted to use a local variable inside an anonymous class or lambda,
         * It has to be explicitly declared as final.
         */
        final String name = "Shyam";
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("final variable : "+name);
            }
        };
        /**
         * Java 8 introduced "effective final", we no longer need to declare variable as final
         * until variable is never re-assigned after initial assignment.
         */
        String greeting = "Hello..How are you ..? ";   // never reassigned → effectively final
        int count = 5 ;                     // never reassigned → effectively final

        Runnable r2 = () ->{
            System.out.println("Greeting : "+greeting + " , count : "+ count);
        };
        r2.run(); // Greeting : Hello..How are you ..?  , count : 5

        // greeting = "Namaste" ; this will raise CE saying variable in lambda should be final/effective final

        // Why can't we use Non-Final variable ?
        /**
        int value = 10;
        Runnable r3 = () -> System.out.println(value);  // captures value
        value = 20 ;   // re-assigned after lambda was defined
        r.run() ;   // which 'value' should be picked ? 10 or 20 ?
        */

        // When you genuinely need to "modify" a value inside a lambda,
        // the standard pattern is to wrap it in an array or an AtomicInteger:

        // You want a counter inside a lambda — this won't compile:
        List<Integer> list = Arrays.asList(3);

        // Workaround 1: single-element array
        int[] numArr = {0};
        list.forEach(item -> numArr[0]++);  // works, but not thread-safe

        // Workaround 2: AtomicInteger (thread-safe)
        AtomicInteger atomicVal = new AtomicInteger(0);
        list.forEach(item -> atomicVal.incrementAndGet());  // correct and thread-safe
        System.out.println(atomicVal.get());

    }
}
