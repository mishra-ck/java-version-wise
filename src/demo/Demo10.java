package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Demo10 {

    public static void main(String[] args) {
//        String str = "" ;
//        System.out.println(isStringPalindrome(str));

//        int[] arr = {1,2,3,4,5,6,7,8,9} ;
//        System.out.println(binarySearch(arr,9));

        String note = "bccd";
        String magazine = "abcdef" ;
//        System.out.println(checkCutOutInfo(magazine,note));
    }

    public static boolean isStringPalindrome(String str){
        if(str== null || str.equals("")){
            throw new IllegalArgumentException("Input string is invalid,please try again");
        }
        boolean result = false ;
        int len = str.length();
        for(int i = 0,j = len-1 ; i < j ; i++,j--){
            if(str.charAt(i)==str.charAt(j)){
                result = true ;
                continue;
            }else{
                result = false ;
                break;
            }
        }
        return result ;
    }


    public static int binarySearch(int[] arr , int x){
        if(arr == null){
            throw new IllegalArgumentException("Input array is empty");
        }
        int left = 0 ;
        int right = arr.length ;
        while(left < right){
            int mid = left + (right-left)/2 ;
            if(arr[mid] < x){
                left = mid + 1 ;
            }else if(arr[mid] > x){
                    right = mid -1 ;
            }
            if(arr[mid] == x){
                return mid+1 ; // returning index of element present in array start from 1
            }
        }
        return -1  ;
    }


    // note = “ceba”; magazine = “abcde”
    public static boolean checkCutOutInfo(String magazine, String note){
        if(Objects.isNull(magazine) || Objects.isNull(note)){
            throw new IllegalArgumentException("Please enter valid input") ;
        }
        boolean result = false ;
        Map<Character, Integer> magazineMap = new HashMap<>();
        for(int i =0 ;i<magazine.length();i++){  // O(n)
            magazineMap.put(magazine.charAt(i),i);
        }
        for(int j = 0 ; j < note.length();j++){
            Character ch = note.charAt(j) ;
            if(magazineMap.containsKey(ch)){
                result = true ;
                magazineMap.remove(ch);
            }else{
                return false ;
            }
        }
        return result ;
    }

    public static int rCharToInt(char c) {
        String str = String.valueOf(c);
        switch (str) {
            case "I": return 1;
            case "II": return 2;
            case "III":return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII":return 8;
            case "IX": return 9;
            case "X" : return 10 ;
            case "L": return 50;
            case "C": return 100;
            case "D": return 500;
            case "M": return 1000;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }


}
