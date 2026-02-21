package basics.immutability;

import basics.oops.ProtectClass;

public class Demo extends ProtectClass {
    public Demo(int x, int y) {
        super(x, y);
    }

    public Demo() {

    }

    public static void main(String[] args) {

        Demo p = new Demo();
        p.method1(); // use of protected outside package
//        p.method2();  default method cannot be called outside the package

    }
}
