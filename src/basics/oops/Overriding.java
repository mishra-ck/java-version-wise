package basics.oops;

public class Overriding {
    public static void main(String[] args) {
        P p = new C();
        System.out.println("child name " + p.getChildName());
        System.out.println(" " + p.getParentName());
        E e = new F();
        System.out.println("var of parent " + e.x );
        System.out.println("static case :" + E.m5());

    }
}

abstract class P{
    String getParentName(){
        System.out.println("Inside parent name()");
        return new String("Papa");
    }
    String[] getChildName(){
        System.out.println("Inside Parent");
        String[] children = {"Monu","Sonu","Golu","Chiku"};
        return children ;
    }

    Integer m1(){
        return Integer.getInteger("10") ;
    }
    abstract void discipline();

    protected void method1(){
        System.out.println("protected method in parent");
    }

}
class C extends P{

    @Override
    void discipline() {
        System.out.println("I am in discipline..!!");
    }
}


abstract class E {
    int x = 1_10_100 ;
    abstract void m1();  // synchronized

    void m2(){

    }
    // Private methods cannot be overridden
    private void m3(){

    }
    void m4(){

    }
    static int m5(){
        return  0 ;
    }

}

 final class F extends E{


    @Override
    void m1(){

    }
//    @Override
//    abstract void m2();

//     @Override
//     public void m3(){
//
//     }

     // public > protected > default > private
     @Override
     protected void m4(){

     }

     // Method hiding, this is not overriding
    static int m5(){
    return -1 ;
    }
}