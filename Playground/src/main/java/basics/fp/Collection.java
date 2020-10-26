package basics.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Collection {
    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        // 匿名 Consumer インスタンス を lambda に変更する
        // 型推論も取り除かれる (final String name)
//        friends.forEach(name -> System.out.println(name));
        friends.forEach(System.out::println);
    }
}
