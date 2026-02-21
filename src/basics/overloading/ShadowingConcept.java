package basics.overloading;

public class ShadowingConcept {
    public static void main(String[] args) {
        A a = new B();
        a.m1();
    }
}
abstract class A {
     static void m1(){
          System.out.println("Inside class A");
      }
}
class B extends A{
     static void m1(){
        System.out.println("Inside class B");
    }
}