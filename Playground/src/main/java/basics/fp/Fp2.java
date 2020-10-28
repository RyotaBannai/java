package basics.fp;

import basics.fp.JavaBean.Person;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Fp2 {
    private static void printchars(int aChar) {
        System.out.println((char) (aChar));
    }

    public static void main(String[] args) {
        final String str = "w00t";
        str.chars().forEach(ch -> System.out.println(ch)); // chars() は　Integer stream を返すので 119 79 79 116 となる

        str.chars().forEach(Fp2::printchars); // 自分で作成した関数をメソッド参照で使う！


        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35)
        );

        List<Person> ascendingAge =
                people.stream()
                        .sorted((person1, person2) -> person1.ageDifference(person2)) // sorted は引数に　Comparator を取る
                        .collect(toList());
        ascendingAge.stream()
                .forEach(System.out::println);
    }

}
