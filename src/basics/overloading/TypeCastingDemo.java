package basics.overloading;

import java.io.IOException;
import java.sql.SQLException;

public class TypeCastingDemo {
    public static void main(String[] args) {
        Integer x = Integer.valueOf(10);
        Number num = (Number) x ;
        Object obj = (Object) num ;
        String str = "chanchal";
        System.out.println(num == x);  // true
        System.out.println(obj == num); // true
        System.out.println(x == obj);  // true

     
    }

    static void m1(){
        try{
            System.out.println("inside try ");
            System.out.println(10/0);
            return ;
        }catch(ArithmeticException e){
            System.out.println(e.getStackTrace());

        }
    }
}



