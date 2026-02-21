package basics.oops;

public class AbstractionUsingInterface {
    // Abstract : Partial/ not fully implemented

    // Class and interface
    // abstract keyword
    public static void main(String[] args) {
        MechanicalDepartment dept = new MechanicalDepartment() ;
        System.out.println(dept.numOfDepartments() + "");
        System.out.println(dept.college);
        System.out.println(dept.noOfTeacher());
        System.out.println(IMSCollege.collegeName);
        System.out.println(IMSCollege.noOfTeacher());
    }
}

// interface - methods (abstract , default, static )
// No objects for interface

interface University{
    default int numOfDepartments(){
        return 50;
    }
}
interface IMSCollege {
    String collegeName = "IMS Engg COllege"  ; // by default it is static and final
    int numberOfStudents() ;  // implicit public abstract
    default int numOfDepartments(){
        return 20;
    }
    static int noOfTeacher(){
        return 100 ;
    }
}
class MechanicalDepartment implements IMSCollege, University {
    String college = IMSCollege.collegeName + " Ghaziabad" ;
    @Override
    public int numberOfStudents() {
        return 50 ;
    }
    @Override
    public int numOfDepartments() {
        return IMSCollege.super.numOfDepartments();
    }
    public int noOfTeacher(){
        return IMSCollege.noOfTeacher();
    }

}



