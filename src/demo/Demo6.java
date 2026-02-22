package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo6 {
    public static void main(String[] args) {
//
//        Comparator<Integer> comp = ()->{
//            public int compare(Integer a,Integer b){
//                if(a > b){
//                    return 1 ;
//                }else if(a < b){
//                    return -1 ;
//                }else{
//                    return  0 ;
//                }
//            }
//        } ;
//        // 10,15,8,49,25,98,98,32,15 sort in descending order
       List<Integer> list =  Stream.of(10,15,8,49,25,98,98,32,15)
                .sorted(Collections.reverseOrder()).collect(Collectors.toList());

        int[] arr = {5,1 ,5 ,2 ,9 ,7 ,3 ,4} ; int sum = 8 ;
        List<List<Integer>> result  = findSubArray(arr,sum) ;
        result.forEach(System.out::println);

    }

    // 5 1 5 2 9 7 3 4  - 8 {1,5,2} {5,3}
    public static List<List<Integer>> findSubArray(int[] arr, int sum){
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0 ; i < arr.length-1 ; i++){  // 5 .,1 -> 6
            int result = 0 ;
            List<Integer> tempList = null ;
             for(int j = i+1 ; j < arr.length ; j++  ){  // 1 , 5
                if(arr[i] + arr[j] == sum){
                    tempList = new ArrayList<>();
                    tempList.add(arr[i]);
                    tempList.add(arr[i]);
                    list.add(tempList);
                }
            }
        }
        return list ;
    }

}
