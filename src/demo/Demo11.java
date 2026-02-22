package demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Demo11 implements Cloneable{
    public static void main(String[] args) throws CloneNotSupportedException {
        Demo11 demo = Demo11.getInstance() ;
        Demo11 demo2 = (Demo11) demo.clone();
//        System.out.println(demo2.equals(demo));

        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("Ram",5000));
        empList.add(new Employee("Shyam",6000));
        empList.add(new Employee("Raju",8000));
        empList.add(new Employee("Seeta",8000));

       List<Employee> result = empList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .distinct()
                .skip(1).limit(1).collect(Collectors.toList());
       result.forEach(System.out::println);



    }
    private  static  Demo11 demo ;
    private Demo11(){
//        System.out.println("inside constructor");
    }
    public static Demo11 getInstance(){
        if(Objects.isNull(demo)){
            synchronized(Demo11.class){
                return new Demo11();
            }
        }else{
            return demo ;
        }
    }



}


class Employee{

    String name  ;
    double salary  ;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        sb.append("name='").append(name).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0 ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }
}

