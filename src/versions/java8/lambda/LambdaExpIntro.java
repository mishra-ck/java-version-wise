package versions.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

/**
 * Comparison between Traditional Methods and Lambda Expressions
 */
public class LambdaExpIntro {
    public static void main(String[] args) {
        // Traditional Method
        List<String> names = Arrays.asList("Chanchal","Ram","Shyam","Amit","Menu");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for(String name : names){
            System.out.println(name);
        }

        // Lambda Expression
        List<String> lambdaName = Arrays.asList("Chanchal","Ram","Shyam","Amit","Menu");
        lambdaName.sort((a,b) -> a.compareTo(b));
        lambdaName.forEach(System.out::println);

    }
}
