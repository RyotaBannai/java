package effective_java.other;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CovariantReturnTypes {
    public interface ReturnNumber {
        Number value();
    }

    public class ReturnInterger implements ReturnNumber {
        public Integer value() {  // Covariant return type
            return Integer.valueOf(0);
        }
    }

    public static void main(String[] args) {
        Method[] methods = ReturnInterger.class.getMethods(); // リフレクションを使う
        Arrays.stream(methods).forEach(method -> {
            System.out.println(method);
            System.out.println("\tisSynthetic:" + method.isSynthetic());
            System.out.println("\tisBridge:" + method.isBridge());
            /*
            * public java.lang.Number effective_java.other.CovariantReturnTypes$ReturnInterger.value()
            *     isSynthetic:true
            *     isBridge:true
            * */
        });
    }
}
