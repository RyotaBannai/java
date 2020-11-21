package archive.basics;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyVariadicArguments {
    public static void main(String[] args) {
        Test(IntStream.rangeClosed(1, 10).toArray());
    }

    private static void Test(int... args) {
        Arrays.stream(args).forEach(System.out::println);
    }
}
