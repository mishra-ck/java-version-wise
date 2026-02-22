package demo;

import java.util.*;
import java.util.stream.Collectors;

public class Demo7 {
    public static void main(String[] args) {
        int[] a={1,2,3}; int[] c= {6,4,5};
        int[] b={1,2,3,4,5} ;
        System.out.println(checkArrayPresent(b,c));

        List<Integer> list = Arrays.asList( 1,2,4,6,3,7,8,0) ;
        List<Integer> sortedList =
                list.stream().sorted().collect(Collectors.toList());
    }

    public  static boolean checkArrayPresent(int[] searchArr , int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();  // search
        for (int i = 0; i < searchArr.length; i++) {
            map.put(searchArr[i], i);
        }
        map.entrySet().forEach(System.out::println);
        boolean finalResult = true;
        for (int j = 0; j < arr.length; j++) {
            if (!map.containsKey(arr[j])) {
                return false;
            }
        }
        return finalResult;
    }

    }
