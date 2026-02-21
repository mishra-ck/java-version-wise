package collection.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<Employee> set = new HashSet<>();

        set.add(new Employee("Ram",22,"IT",50000.0,"DEV"));
        set.add(new Employee("Ram",22,"IT",50000.0,"DEV"));
        set.add(new Employee("Ram",23,"IT",50000.0,"DEV"));

//        set.stream().forEach(System.out::println);

        HashMap<Employee,Integer> map = new HashMap<>() ;
        map.put(new Employee("Ram",22,"IT",50000.0,"DEV"),101);
        map.put(new Employee("Ram",22,"IT",50000.0,"DEV"),102);
        map.put(new Employee("Ram",24,"IT",50000.0,"DEV"),103);

        map.entrySet().forEach(System.out::println);

    }
}
class Employee{
    private String name ;
    private int age ;
    private String dept ;
    private double salary ;
    private String role;

    public Employee(String name, int age, String dept, double salary, String role) {
        this.name = name;
        this.age = age;
        this.dept = dept;
        this.salary = salary;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", dept='").append(dept).append('\'');
        sb.append(", salary=").append(salary);
        sb.append(", role='").append(role).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
    return age == employee.age && Double.compare(salary, employee.salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(dept, employee.dept) && Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, dept, salary, role);
    }
}