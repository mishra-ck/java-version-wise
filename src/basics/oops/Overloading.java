package basics.oops;

public class Overloading {
    // Polymorphism
    // On the basis of execution - 2 types(1. Compile time - Overloading, 2. Runtime or Overriding )
    public static void main(String[] args) {
        Army army = new Army();
//        System.out.println("State Raj  : "+  army.getBattelionName("Raj"));

//        System.out.println("Null case : "+ army.getBattelionName(null));   // Ambigious method call CE methods are at same level
//        System.out.println("Null case : "+ army.getBattelionName(null));   // Will not raise CE if parent child relation is there

//        System.out.println(army.m1('a','b')); // case of automatic promotion
        System.out.println("War Cry : "+ army.getBattelionName(new StringBuffer("Jai Mata Di")));

    }
}

class ArmedForces{
    public String getCDSChiefName(){
        return "Bipin Rawat" ;
    }

}
class Army extends ArmedForces{

    //Overloading example- 2 methods with same name and different parameter/arguments
    public String getBattelionName(String state){
        System.out.println("getBattelionName(String state)");
        switch (state){
            case "Raj":
                return "Rajput Rifel";
            case "UK":
                return "Gurkha" ;
        }
        return null ;
    }
    public String getBattelionName(StringBuffer warCry){
        System.out.println("getBattelionName(Object warCry)");
        if (warCry.compareTo(new StringBuffer("Jai Mata Di")) == 0) {
            return "Rajput Rifel";
        } else if (warCry.compareTo(new StringBuffer("Jai Maa Kaali"))==0) {
            return "Gurkha";
        }
        return null ;
    }

    int m1(int x,float f){
        System.out.println("Inside int()");
        return 0 ;
    }
    int m1(float f,int x){
        System.out.println("inside float()");
        return 1 ;
    }

}
class Navy extends  ArmedForces{

}
class Airforce extends  ArmedForces{

}