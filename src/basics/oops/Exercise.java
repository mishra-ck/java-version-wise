package basics.oops;

public class Exercise {

    public static void main(String[] args) {
        Demo demo = new Demo();

        Engine eng = new Engine();
        eng.setBrand("tata");
        eng.setCc(1200);

        Car car = new Car();
        car.setCarbrand("Maruti");
        car.setEng(eng);

        Child ch = new Child();
        ch.setStr("Avish");
        ch.setX(26);
        System.out.println(""+ ch.getChildName());

    }

    // OOPs
    // 4: Data hiding , Abstraction , Encapsulation, Inheritance , Polymorphism
    // Data hiding : using private
    // Abstraction  : Data hiding(var- private ) + limited exposure of implementation (Methods - public)
    // Encapsulation : co-related data members and behaviours grouping , ex: class
    // Inheritance : Extension of Parent class by Child class(extends, implements)


}
class Demo{
    private int x ;
    private int y ;
    private String str ;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x >= 0){

        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    // Abstraction = Data hiding + implemn expose
}

// Encapsulation ex
class Car{
   private  String carbrand ;
    //
   private  Engine eng ;



    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public Engine getEng() {
        return eng;
    }

    public void setEng(Engine eng) {
        this.eng = eng;
    }
}
class Engine{
   private String brand ;
   private float cc ;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getCc() {
        return cc;
    }

    public void setCc(float cc) {
        this.cc = cc;
    }
}


// Inheritance ex : Class

class Parent{
    private int x ;
   private  String str ;

   public Parent(){

   }
    public Parent(int x, String str) {
        this.x = x;
        this.str = str;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    // fields
    // methods
}
class Father{

}
class Child extends Parent{
    // by default parents stuff are present
    public String getChildName(){
        return super.getStr();
    }

}
class Child2 extends Parent{

}
class GrandSonChild extends Child{

}

// Inheritance ex : interface

