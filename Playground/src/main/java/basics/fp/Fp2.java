package basics.fp;

import basics.fp.JavaBean.Person;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Fp2 {

    // Convenience Methods.
    private static void printchars(int aChar) {
        System.out.println((char) (aChar));
    }

    private static void printPeplpe(final String message, List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

    public static void main(String[] args) {
        final String str = "w00t";
        str.chars().forEach(System.out::println); // chars() は　Integer stream を返すので 119 79 79 116 となる

        str.chars().forEach(Fp2::printchars); // 自分で作成した関数をメソッド参照で使う！


        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35)
        );

        List<Person> ascendingAge =
                people.stream()
                        //.sorted((person1, person2) -> person1.ageDifference(person2)) // sorted は引数に　Comparator を取る
                        .sorted(Person::ageDifference) // !! 最初のインスタンスをメソッドのターゲット、二つ目をメソッドの引数として使う！これを java のコンパイラがよろしくやってくれる。
                        .collect(toList());

        printPeplpe("Sorted in ascending order by age:", ascendingAge);


        final Function<Person, String> byName = person -> person.getName();

        // 名前でソートも
        printPeplpe("Sorted in ascending order by name:",
                people.stream()
//                        .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
//                        .sorted(comparing(Person::getName)) // simplified!!
                        .sorted(comparing(byName)) // こっちでも良い
                        .collect(Collectors.toList())
        );

        // 集約関数の真骨頂
        people.stream()
                .min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println("Youngest: " + youngest));

    }

}
