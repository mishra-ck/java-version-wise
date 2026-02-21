package basics.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PolymorSerial {
    public static void main(String[] args) throws IOException {
        // It is classic case of polymorphism in serialization
        FileOutputStream fileOutputStream = new FileOutputStream("poly.ser");
        ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
        os.writeObject(new Child());  // it will not throw any exception even though is not Serialized
    }
}
class Parent{
    public Parent(){
        System.out.println("Inside parent class constructor");
    }
}
class Child extends Parent implements Serializable {
    public Child(){
        System.out.println("Inside child class constructor");
    }

}

