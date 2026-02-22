package demo;

public class Demo12 {
    public static void main(String[] args) {
        int[] arr = {-1,3,5,10,2,30} ;
//        System.out.println(secondLargest(arr));

        minAndMaxOfArray(arr);

    }

    // int[] arr = {2,3,5,6,8,32,0}
    //second largest element
    public static int secondLargest(int[] arr){

        int secondLargest = Integer.MIN_VALUE ;
        int largest = Integer.MIN_VALUE ;

        for(int i = 0 ; i < arr.length ; i++){
            int temp = largest ;
            if(arr[i] > largest ){
                largest = arr[i] ;
            }
            if (arr[i] > secondLargest && arr[i]<largest) {
                secondLargest = arr[i] ;
            }
        }
        return secondLargest ;
    }


    // min and mix of an array
    public  static void minAndMaxOfArray(int[] arr){
        if(arr == null){
            throw new IllegalArgumentException("Empty array");
        }
        int min = Integer.MAX_VALUE ; // max integer value
        int max = Integer.MIN_VALUE ;  // min value

        for(int i=0 ; i < arr.length ;i++){  //

            if(arr[i] < min ){
                min = arr[i] ;
            }
            if(arr[i] >max){
                max = arr[i] ;
            }
        }
        System.out.println("Min value is : " +min + " Max value is : " + max);
    }

}
