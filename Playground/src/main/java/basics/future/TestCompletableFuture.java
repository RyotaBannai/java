package basics.future;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCompletableFuture {
    public static void main(String[] args) {
        try {
            Future<String> future = calculateAsync();
            while (!future.isDone()) {
                Thread.sleep(200);
                System.out.println("not done yet");
            }
            String result = calculateAsync().get();
            System.out.println(Objects.equals("hello", result)); // true
            // ...
            System.out.println(Objects.equals("hello", future.get()));
            // ...
            System.out.println(Objects.equals("hello world", chainAsync.get()));
            // ...
            printAsyncly.get();
        } catch (InterruptedException | ExecutionException e) {
        }
    }

    public static Future<String> calculateAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completeableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(1000);
            completeableFuture.complete("hello"); // javascript でいう resolve, reject のような感じ
            // completableFuture.cancel(true); // CancellationException
            return null;
        });
        return completeableFuture;
    }

    // calculateAsync のようにいちいち Thread を作って並列処理を行うの面倒なので...
    static CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
    // javascript のように then を使いたい...
    static CompletableFuture<String> chainAsync = future.thenApply(s -> s + " world");
    // もし戻り値が不要ならば thenRun を使う
    static CompletableFuture<Void> printAsyncly = future.thenRun(() -> System.out.println("Computation finished."));
}
