package demo;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class Demo4 {
    public static void main(String[] args) {
        int[] numArr = {2,0,2,1,1,0};
        int[] result = sortArray(numArr);
//        System.out.println(Arrays.toString(result));

        String str =  "RaCeCar" ;
//        System.out.println(isPalindrome(str));

        char obj = 'a'+1 ;
        System.out.println(obj);

    }
    public static  int[] sortArray(int[] unsorted){

        int len = unsorted.length ;  // 6
        if(len == 0){
            return null ;
        }
        int[] result = new int[len];  // 6

        int last = len-1 ;  // 5
        int first = 0 ;

        for(int i= 0 ; i< len ; i++ ){

            if(unsorted[i] == 2){
                result[last-1] =  2 ;  // 3
                last = last -1 ;
            }
            if(unsorted[i] ==0){ // 2
                result[first] = 0 ;
                first = first +1 ;
            }
        }

        for(int x = first ; x <=last ; x++){
            result[x] = 1 ;
        }

        return result ;
    }

    // Palindrome : madam
    public static boolean isPalindrome(String str){
        boolean result = false ;
        if(Objects.isNull(str)){
            return false ;
        }
        int len = str.length() ;
        for(int i = 0, l = len-1 ; i< len  ; i++,l--){
            if(str.charAt(i) == str.charAt(l)){
                result = true  ;
              continue;
            }
        }
    return result ;
    }

//    private int start ;
//    Supplier<Integer> incrementer(int start) {
//        return () -> start++;
//    }

}
interface D{
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}