package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo8 {

    //Print subarray with maximum sum in the given array
    //i/p:  {-3,-4,5,-1,2,-4,6,-1}  i , j   (i , j )
    public static void main(String[] args) {
        int[] arr = {-3,-4,5,-1,2,-4,6,-1};  // 5
        findMaxSubArray(arr);



    String  input="ThisIsCamelCase";
    char[] ch = input.toCharArray() ;


//O/p: this_is_camel_case


    }

    public static void findMaxSubArray(int[] arr){

        int maxSum = Integer.MIN_VALUE ;
        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int i = 0 ; i < arr.length ; i++){

            int currentSum = arr[i] ;
            List<Integer> subArray = new ArrayList<>() ;
            subArray.add(arr[i]);
            for(int j = i+1 ;j < arr.length ; j++ ){

                currentSum +=  arr[j] ;

                if(currentSum > maxSum){  //
                    maxSum = currentSum ;
                    subArray.add(arr[j]);
                    continue;
                }else{
                    map.put(maxSum,subArray) ;
                    break ;
                }
            }
        }
        map.entrySet().forEach(System.out::println);

    }


}
