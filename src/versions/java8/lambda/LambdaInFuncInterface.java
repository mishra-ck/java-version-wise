package versions.java8.lambda;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Using Lambda Expression with Functional Interfaces
 */
public class LambdaInFuncInterface {
    public static void main(String[] args) {
        // Predicate
        List<String> names = Arrays.asList("Chanchal","Ram","Shyam","Amit","Raju");
        Predicate<String> startsWithR = name -> name.startsWith("R");
        names.stream().filter(startsWithR)
                .forEach(System.out::println);

        // Function
        List<String> nameLen = Arrays.asList("Chanchal","Ram","Shyam","Amit","Raju");
        Function<String, Integer> nameLength = name -> name.length();
        var lenResult = nameLen.stream().map(nameLength)
                .collect(Collectors.toList());
//        lenResult.forEach(System.out::println);

        // Consumer
        List<String> printNames = Arrays.asList("Chanchal","Ram","Shyam","Amit","Raju");
        Consumer<String> nameList = name -> System.out.println("Name : " + name + ", lenght : "+ name.length());
        printNames.stream().forEach(nameList);

        // Supplier
        Supplier<LocalDateTime> dateSupplier = () -> LocalDateTime.now();
        System.out.println(dateSupplier);

    }
}
