package basics.fp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Collection {
    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        final List<String> comrades = Arrays.asList("Kate", "Ken", "Paula", "Zack");
        // 匿名 Consumer インスタンス を lambda に変更する
        // 型推論も取り除かれる (final String name)
//        friends.forEach(name -> System.out.println(name));
        friends.forEach(System.out::println);
        // use stream
        friends.stream()
                .map(String::toUpperCase)
                .forEach(name -> System.out.print(name + ' '));

        final Predicate<String> startwithNLambda = name -> name.startsWith("N"); // Predicate は条件に合致するかどうか bool を返す
        final List<String> startsWithN = friends.stream()
                .filter(startwithNLambda)
                .collect(Collectors.toList()); // convert to List
        System.out.println(
                comrades.stream()
                        .filter(startwithNLambda)
                        .count());

        // Function を使って, static の使用を押させる
        final Function<String, Predicate<String>> startsWithLetter =
                letter -> name -> name.startsWith(letter);

        friends.stream()
                .filter(startsWithLetter.apply("N"))
                .forEach(System.out::println);

        // stream の型を変える
        System.out.println("the length of all friends name is: " +
                friends.stream()
                        .mapToInt(name -> name.length())
                        .sum());

        // get the longest name and keep first one if more than two
        final Optional<String> aLongName = friends.stream()
                .reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2); // name1 に前の処理の結果が入る
        aLongName.ifPresent(name -> System.out.println(String.format("The longest name is %s", name)));

        // join method が良い
        System.out.println(friends.stream()
                .map(String::toUpperCase)
                .collect(joining(", ")));
    }
}
