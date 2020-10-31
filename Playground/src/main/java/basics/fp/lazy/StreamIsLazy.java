package basics.fp.lazy;

import java.util.Arrays;
import java.util.List;

public class StreamIsLazy {
    private static List<String> names = Arrays.asList(
            "Brad", "Kate", "Kim",
            "Jack", "Joe", "Mike",
            "Susan", "George", "Robert",
            "Julia", "Parker", "Benson");


    private static int length(final String name) {
        System.out.println(String.format("getting length for %s, %d", name, name.length()));
        return name.length();
    }

    private static String toUpper(final String name) {
        System.out.println(String.format("name is %s", name));
        return name.toUpperCase();
    }

    public static void main(String[] args) {
        final String firstNameWith3Letters =
                names.stream()
                        .filter(name -> length(name) == 3)
                        .map(StreamIsLazy::toUpper)
                        .findFirst()
                        .orElse("no one");
        // name is Kim しか stdout に出ない...!!!
        System.out.println(firstNameWith3Letters);
    }
}
