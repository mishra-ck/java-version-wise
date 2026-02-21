package basics.oops;

public class ProtectClass {

    int x ;
    int y ;
    public ProtectClass() {

    }
    public ProtectClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProtectClass{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
    protected void method1(){
        System.out.println("Inside protected methods of ProtectClass");
    }
    void method2(){
        System.out.println("Inside default methods of ProtectClass");
    }

    public static void main(String[] args) {
        Teacher t = new Student(10,20);  // no inheritance in constructor
    }
}

class Teacher{
    int x ;
    int y ;
    double d ;
    float f ;
    String str ;

    // Example of constructor chaining
    public Teacher(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Teacher(int x , int y , double d){
        this(x,y);
        this.d = d ;
    }
    public Teacher( int x , int y , double d, float f){
        this(x,y,d);
        this.f = f ;
    }

}
class Student extends Teacher{
    int x ;
    double d ;
    public Student(int x, int y) {
        super(x, y);
    }
    // Recursive invocation of constructor is not allowed
//    Student(int x){
//        this(x);
//
//    }
//    Student(double d){
//        this(d);
//    }
}