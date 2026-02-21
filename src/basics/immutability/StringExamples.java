package basics.immutability;

public class StringExamples {
    public static void main(String[] args) {
        // Scenario 1
        String s1 = new String("chanchal");
        String s9 = new String("chanchal");
        String s2 = s1.toUpperCase() ;  // changing to upper case
        // Therefore a  new Object will be created

        String s6 = s1.toLowerCase();  // changing to lower case
        // No change is done in existing object , therefore no new object will be created

        System.out.println(s1 == s2);   // false
        System.out.println(s1 == s6);  // true
        System.out.println(s9 == s1);  // false

        System.out.println("\n Scenario 2 \n ");
        // scenario 2
        String s3 = "delhi";  // created object in SCP area
        String s4 = s3.toString();  // no change in existing object so s4 will point to s3 object
        String s5 = s3 .toUpperCase();  // change in object , new Object will be created in Heap area
        String s7 = s3.toLowerCase(); // no change, point to s3 object

        System.out.println(s3 == s4);   // true
        System.out.println(s3 == s5);   // false
        System.out.println(s3 == s7);   // true

    }
}
