package collection.list;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ArrayPractice {

    public static void main(String[] args) {
        divisionWithPrecision(220.0F,12.0F);

    }
    static ArrayList<Float> divisionWithPrecision(float a, float b) {
        MathContext context = new MathContext(4,RoundingMode.HALF_EVEN);

        ArrayList<Float> list = new ArrayList<>();
        if (b>0) {
            Float res = a / b;
            System.out.println("res "+ res);
            list.add(res);
            BigDecimal bd = new  BigDecimal(String.valueOf(BigDecimal.valueOf(res)));
            bd = bd.round(context);
            System.out.println("res2 "+ bd);
            return  list ;
        }
        else{
            return  null ;
        }
    }
}
