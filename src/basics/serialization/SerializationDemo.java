package basics.serialization;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {

        Employee employee = new Employee("Chanchal","IT",5000);
        serializeObject(employee,"chanchal.ser");
        Employee deserializedObj = (Employee) deserializedObject("chanchal.ser");
        System.out.println("deserialized object is : "+ deserializedObj);
    }
    public static void serializeObject(Object obj, String fileName){
        try(FileOutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(outputStream)){
            objectOut.writeObject(obj);
            System.out.println("Object is now serialized");
        }catch (IOException e){
            System.out.println("Exception occurred : "+ e);
        }
    }

    public static Object  deserializedObject(String fileName){
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileInputStream)) {
            Object objectReceived = objectIn.readObject();
            System.out.println("Object is now deserialized");
            return  objectReceived ;
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Exception occurred : "+ e);
            return null ;
        }
    }
}

class Employee implements  Serializable{
    private String employeeName ;
    private String deptId ;
    private double salary ;

    public Employee(String employeeName, String deptId, double salary) {
        this.employeeName = employeeName;
        this.deptId = deptId;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("employeeName='").append(employeeName).append('\'');
        sb.append(", deptId='").append(deptId).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }
}