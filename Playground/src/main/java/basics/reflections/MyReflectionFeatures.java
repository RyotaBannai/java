package basics.reflections;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyReflectionFeatures {
    public static void main(String[] args) {
        try {
            useForName();
        } catch (ClassNotFoundException error) {
            error.getStackTrace();
        }
    }

    private static void useForName() throws ClassNotFoundException {
        Class<?> myClass = Class.forName("basics.reflections.Employee"); // auto completion works.
        System.out.println("Classname: " + myClass.getName()); // -> Classname: basics.reflections.Employee
//        myClass.newInstance() // deprecated
    }

    private static void makeInstanceWithoutNew() throws ClassNotFoundException {
        Class<?> myClass = Class.forName("basics.reflections.Employee");
        try {
            Constructor myClassConstructor = myClass.getDeclaredConstructor(String.class, String.class); // コンストラクタオブジェクトを作成
            Employee employee = (Employee) myClassConstructor.newInstance("Mark", "Zuckerburg");
            System.out.println(employee.getLastName());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            error.getStackTrace();
        }
    }
}
