package basics.serialization;

import java.io.*;

public class SerializationConcepts {

    /**
     * Step A : Writing a Serialized Object into a file
     *
     *  1. Make a FileOutputStream :- FileOutputStream knows how to connect and create a file
     *  FileOutputStream fileStream = new FileOutputStream("MyFile.ser");   If "MyFile.ser" does not exist, it will be created automatically.
     *
     *  2.Make an ObjectOutputStream :- It lets us write Objects, but it can't directly connect to file. It needs to be fed a "helper".
     *  This is called "chaining" one stream to another
     *  ObjectOutputStream os = new ObjectOutputStream(fileStream) ;
     *
     *  3. Write the object :-
     *  os.writeObject(obj1);
     *  os.writeObject(obj2);
     *  os.writeObject(obj3) ;
     *  Serializes the objects referenced by obj1,obj2,obj3 and writes them in order to file "MyFile.ser"
     *
     *  4. Close the ObjectOutputStream :-
     *  os.close();   closing the stream at the top automatically closes the ones underneath,
     *  So FileOutputStream and file will close automatically.
     *
     *  NOTE :-
     *  When an Object is serialized, all the objects it refers to from instance variables are also serialized.
     *  All the objects those objects refers to are serialized and so on. and it happens automatically.
     */


    public static void main(String[] args) throws IOException {
        SerialProcess process = new SerialProcess("Chanchal",67,28,"CSE","Delhi");
        FileOutputStream fileStream = new FileOutputStream("SerialProcess.ser");
        ObjectOutputStream os = new ObjectOutputStream(fileStream);
        os.writeObject(process);
        // will give runtime exception : NotSerializableException because it is not implementing Serializable interface
    }

    record SerialProcess(String name , int rollno , int age , String deptName , String address
    ) implements Serializable {

    }
}
