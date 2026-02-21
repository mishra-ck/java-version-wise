package collection.list;

import java.util.*;

public class CursorDemo {
    public static void main(String[] args) {

        // Enumeration - legacy objects

        Vector<Integer> v = new Vector<>();
        for(int i = 0 ;i<10  ;i++){
            v.addElement(i);
        }
//        System.out.println("List is "+ v);

        Enumeration<Integer> e = v.elements();

        while(e.hasMoreElements()){
            Integer ie = e.nextElement();
            if(ie % 2 == 0){
//                System.out.println("Even number");
            }else{
                v.removeElement(ie) ;
            }
        }
//        System.out.println("Final vector "+ v);


        //  Iterator - universal iterator
        List<String> list = new ArrayList<>();
        list.add("Rajeev");
        list.add("Laajwanti");
        list.add("Rajjo");
        list.add("Tharkki");
        list.add("Heavy Rider");
        list.add("Engineer");

        Iterator<String> it = list.iterator();

        while (it.hasNext()){
            String str = it.next();
            if(str.charAt(0) == 'R'){
//                System.out.println("Name is : "+ str);
            }else{
                // do nothing
            }
        }
//        System.out.println(list);


        // ListIterator -- specific iterator for list

        LinkedList<String> l =  new LinkedList() ;
        l.add("Chanchal") ;
        l.add("Mishra")  ;
        l.add("Bansi") ;
        l.add("Kuamr") ;
        System.out.println(l);  // [Chanchal, Mishra, Bansi]

        ListIterator<String> ltr =  l.listIterator() ;
        // Here the object created is implementation class object , not the interface object
        // For example below ListItr is anonymous inner class
        System.out.println(ltr.getClass().getName()); // java.util.LinkedList$ListItr

        while(ltr.hasNext())
        {
//            l.add("Avish");  change cannot be done on collection needs to do in cursor while iterating

            String s = ltr.next() ;
            if(s.equals("Bansi"))
            {
                ltr.remove();
            }
            else if(s.equals("Chanchal"))
            {
                ltr.add("Kr");
            }

            else if(s.equals("Kuamr"))
            {
                ltr.set("Siddharth");
            }

        }
        System.out.println(l);


    }
}
