package basics.immutability;

// no one can extend - final class
// fields immutable
// constructors
// only getter methods
// methods should make sure to maintain immutability

public final class CustomImmutableClass {
    private int i ;
    public CustomImmutableClass(int i) {
        this.i = i;
    }
    public int getI() {
        return i;
    }

    public CustomImmutableClass modify(int i)
    {
        if(this.i == i)
        {
            return this;
        }
        else
        {
            return new CustomImmutableClass(i) ;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomImmutableClass{");
        sb.append("i=").append(i);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        CustomImmutableClass demo = new CustomImmutableClass(10);
        System.out.println("modified : "+ demo.modify(20));
        System.out.println(demo.toString());

        String str = " " ;  // contains one blank space
        str.trim();  // now that space will be removed form string, but it is not assigned to object
        System.out.println(str.equals("")+ "..."+ str.isEmpty());
        // false...false will be the output


        /** StringBuffer sb  = new StringBuffer(String str);
        Creates an equivalent StringBuffer constructor with initial capacity as : Capacity = str.length() + 16 ;
        Capacity = str.length() + 16 ;
        For ex: if str.length is 5 then initial capcity of this StringBuffer object will be 21.**/

        StringBuffer sb3 = new StringBuffer("Avish ") ; // 27 blocks , 11 blocks mein Hello World, 16
        System.out.println(sb3.capacity());
        sb3.append(" Creates an equivalent StringBuffer constructor with");
        System.out.println(sb3.capacity());  // 27
        System.out.println("sb3 value" + sb3 );


        /** public void ensureCapacity(int capacity);
        It will increase capacity  of string buffer object **/
        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2.capacity());// 16
        sb2.ensureCapacity(1000);
        System.out.println(sb2.capacity());// 1000


        StringBuffer sb5 = new StringBuffer(500);
        System.out.println(sb5.capacity());   // 500
        sb5.append("ABCD");
        sb5.trimToSize();
        System.out.println(sb5.capacity());  // 4

        //NOTE: Every method present in StringBuffer is synchronized

        
    }
}
