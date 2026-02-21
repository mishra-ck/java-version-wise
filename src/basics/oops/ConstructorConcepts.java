package basics.oops;

public class ConstructorConcepts {


    public static void main(String[] args) {
//        DemoConstruct demo = new DemoConstruct();
        DemoConstruct demo2 = new DemoConstruct(5,10,"Avish");

//        ConstructorConcepts c = new ConstructorConcepts();
    }

    public ConstructorConcepts(){
        System.out.println("Parent constructor");
    }
}

class DemoConstruct extends ConstructorConcepts{
    int x ;
    int y ;
    String str ;
    // 2 types : default(non-parameterized) and parameterized
    // role of constructor : initialize the newly constructed object
    // if you want to create object inside the class only  go for private constructor

    public DemoConstruct(){
        super();
        System.out.println("Child constructor");
    }
    public DemoConstruct(int x, int y, String str) {
        this.x = x;
        this.y = y;
        this.str = str;
        System.out.println("Child constructor");
    }

}