package basics.generics;

import java.util.Arrays;

public class MyBounded {
    public static void main(String[] args) {
        Person[] people = {new MyBounded.SalesPerson("Make", 1111), new Person("Alice")};
        showNames(people);
    }

    // Bounded 型
    // T は Person から派生したクラスしか指定できないように強制する.
    private static <T extends Person> void showNames(T[] a) {
        Arrays.stream(a).forEach(person -> System.out.println(person.getName()));
    }

    /**
     * static にするか、
     * MyBounded myClass = new MyBounded();
     * SalesPerson person = myClass.new SalesPerson("Make", 1111)
     * とする.
     */
    public static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class SalesPerson extends Person {
        int employeeId;

        public SalesPerson(String name, int employeeId) {
            super(name);
            this.employeeId = employeeId;
        }

        public int getEmployeeId() {
            return this.employeeId;
        }
    }
}
