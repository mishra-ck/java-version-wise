package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo9 {
    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1); //
        map.put(2,1);
        map.put(3,1);
        map.put(4,2); //
        map.put(5,2) ;

        map.entrySet().stream()
                .distinct().collect(Collectors.groupingBy(Function.identity())) ;





    }

}
