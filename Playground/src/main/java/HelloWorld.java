import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");

        String[] result = StringUtils.split("hoge,piyo,foo,bar,", ",");
        System.out.println(Arrays.toString(result));
    }
}
