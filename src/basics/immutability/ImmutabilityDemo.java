package basics.immutability;

import java.util.ArrayList;
import java.util.List;

public class ImmutabilityDemo {
    // general assumption is to use final keyword to make Immutable object which is wrong
    public static void main(String[] args) {

        // 1. Using final we can only make sure reference of an object is un-changed
        final String str = "Chanchal";
//    str = str + "Kumar Mishra" ;  This is not allowed

        // 2. Final only stops us from changing reference of an object but it cannot stop us from changing internal state of an object from public API
        final List<String> stringList = new ArrayList<>();
        System.out.println(stringList.size());  // 0
        //now try to add a new element in array
        stringList.add("Chanchal Mishra");
        System.out.println(stringList.size());  // 1

    }

}
