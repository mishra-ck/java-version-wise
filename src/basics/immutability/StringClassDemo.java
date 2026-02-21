package basics.immutability;

public class StringClassDemo {
    public static void main(String[] args) {

        // String has a special memory alloaction area named : SCP - String Constant Pool
        String  name = new String("Avish");  // Im-muatable object
        name.concat(" Choudhary");
//        System.out.println("My name is : "+ name);  // My name is : Avish

        StringBuffer sb = new StringBuffer("Avish");  //Mutable object
        sb.append(" Choudhary");
//        System.out.println("Sb name is : "+sb);  // Sb name is : Avish Choudhary

        String s1 = "Avish" ;  // only creates object in SCP area
        String s2 = "Avish" ; // same content as s1
        String s3 = new String("Avish"); // creates 2 objects one in SCP other in heap
        System.out.println(s1.equals(s2));  // true
        System.out.println(s1==s2); // true
        System.out.println(s3 == s2);  // false
        System.out.println(s3.equals(s1)); // equals() method checks contain of String

        StringBuffer sb1 = new StringBuffer("Avish");
        StringBuffer sb2 = new StringBuffer("Avish");
        System.out.println(sb1.equals(sb2)); // false
        System.out.println(sb1==sb2); // false

        String s4 = new String(sb1);
        System.out.println("content of s4 "+ s4);

        char[] ch = { 'A','B','C','D','E'}  ;
        char ch2 = 'a' ;
        char ch3 = ++ch2 ;
        System.out.println("ch3 content is :" + ch3);

        String s = new String(ch);
        System.out.println("Char arr to String "+ s) ;

        byte[] b = {97,98,99,100};
        byte b2 = (byte) 255;
        byte b3 = b2;
        System.out.println("content of b3 is " + b3);
        String s5 = new String(b);
        System.out.println("byte array is now " + s5);

        String s6 = "chanchal";
        System.out.println("char at 5 index : "+s6.charAt(5));

        String s7 = "";
        System.out.println(s7.isEmpty());  //true
        System.out.println(s6.isEmpty());  // false

        for(int i = 0 ; i< s6.length();i++){
            System.out.print(s6.charAt(i));
        }

        String s8 = "abcdesfab";
        System.out.println(s8.replace('a', 'b'));  //bbcdesfbb
        System.out.println(s8);

        String s9 = " Avish Kumar ";
        System.out.println(s9.length());
        s9 = s9.trim() ;
        System.out.println(s9.length());

        char[] array = s9.toCharArray();
    }
}
