package basics.exceptions;

import java.sql.SQLException;

// there are 2 throws keyword and try-catch block
// Custom exception manually - throw

public class ExceptionDemo {
    public static void main(String[] args) throws Exception {
        Demo1 demo = new Demo1();
    demo.m2();
//        int x = 0 ;
//        float f =10/x ;  // default exception handler will come into picture
//        while(true){
//            demo.m1();
//        }

        /// how to throw your custom exception
        throw new RidingfailureException("Bike Puncture, tyre flat, cannot move  ") ;


    }
}
class Demo1{
    void m1() throws SQLException,Exception {
        System.out.println("Inside m1()");
        m2() ;
    }
    void m2() {
        try {
            int x = 0 ;
            float f =10/x ;
        }catch (NullPointerException ae){
            System.out.println("Exception message is : " + ae.getMessage());
        }catch (ArithmeticException ae){
            System.out.println("Exception message is : " + ae.getMessage());
        }catch (Exception e){
            System.out.println("Exception message is : " + e.getMessage());
        }

        System.out.println("Inside m2()");
    }
}
class Demo2{
    void m1(){
        try{
            ///
            try{

            }catch (ArithmeticException ae){
                System.out.println(ae.getStackTrace());
            }
            //
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            try{
                // if some exception needs something
            }catch(Exception we){

            }
        }
    }

    void m2(){
        // certain operation is done here


    }
}
//custom exception class
class RidingfailureException extends Exception{
    public RidingfailureException(String message) {
        super(message);
    }
}