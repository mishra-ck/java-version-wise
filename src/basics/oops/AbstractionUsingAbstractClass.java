package basics.oops;

import java.util.Date;

    public class AbstractionUsingAbstractClass {
    public static void main(String[] args) {

        // We cannot create abstract class object but can use it via Anonymous class
//        PayrollService service = new PayrollService(10,20) {
//            @Override
//            public double grossPayroll(String companyName) {
//                return 0;
//            }
//
//            @Override
//            public double totalTax() {
//                return 0;
//            }
//        };
        // Two ways to create objects in case of abstract class child
        TCS tcs = new TCS(10,20);
        PayrollService parent = new TCS(10,20);
        System.out.println("Total Tax "+ tcs.totalTax());
        tcs.yearOfRegistration("YO Registration "+"tcs");

        System.out.println("Founder name "+  PayrollService.founderName());

    }
}
abstract class PayrollService{
    int x  ;
    int y  ;
    static final String name = "XYZ";
    public PayrollService(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public abstract double grossPayroll(String companyName);
    public abstract double totalTax();
    public Date yearOfRegistration(String companyName){
        return new Date();
    }
    public static String founderName(){
        return new String("Avish");
    }

}
class TCS extends PayrollService{

    public TCS(int x, int y) {
        super(x, y);
    }

    @Override
    public double grossPayroll(String companyName) {
        return 0;
    }

    @Override
    public double totalTax() {
        return this.x;
    }
}