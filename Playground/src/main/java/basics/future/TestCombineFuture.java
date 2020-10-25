package basics.future;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCombineFuture {

    public static void main(String[] args) {
        try {
            System.out.println(composedFuture.get());
            System.out.println(combinedFuture.get());
        } catch (InterruptedException | ExecutionException e) {
        }
    }

    static CompletableFuture<String> returnHello = CompletableFuture.supplyAsync(() -> "Hello");
    static CompletableFuture<String> composedFuture
            = returnHello
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World")); // combine じゃなくて compose

    static CompletableFuture<String> combinedFuture
            = returnHello
            .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

    // もし前の future の戻りを次の future にあげる必要がなく、最終的な戻りもいらない場合 → 最後にまとめて欲しいだけなら、thenAcceptBoth が良い
    CompletableFuture acceptBothFutures
            = returnHello
            .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> System.out.println(s1 + s2));
}
