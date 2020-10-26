package basics.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Collection {
    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        friends.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println(name);
            }
        });
    }
}
