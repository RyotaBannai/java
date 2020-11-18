package basics.reflections;

import java.util.Arrays;
import java.util.stream.Stream;

public class Employee {
    String firstName;
    String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void getLastName(String last_name) {
        this.lastName = last_name;
    }

    public static void main(String[] args) {
        Employee employee = new Employee("Mark", "Zuckerburg");
        Class<? extends Employee> myClass = employee.getClass(); //
        System.out.println("Class name is: " + myClass.getName() + "\n Methods: ");
        Arrays.stream(myClass.getMethods())
                .forEach(method -> {
                    System.out.print(" " + method.getName() + "(");
                    Stream.of(method.getParameterTypes())
                            .forEach(param -> System.out.print(param.getName() + ","));
                    System.out.println(")");
                });
    }
}
