package basics.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * ref https://www.baeldung.com/java-completablefuture
 */
public class TestMultipleFutures {
    public static void main(String[] args) {
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        try {
            System.out.println(combinedFuture.get()); // doesn't return any value...

//            String combined = Stream.of(future1, future2, future3)
//                    .map(CompletableFuture::join)
//                    .collect(Collectors.joining(",")); // error
//            System.out.println(combined);

            System.out.println(errorHandlingOfFuture.get());
            // exception を発生されせるため、事前にセット
            exceptionInFuture.completeExceptionally(new RuntimeException("calculation failed!!!"));
            exceptionInFuture.get();

            parallelFutures.get();

        } catch (InterruptedException | ExecutionException | RuntimeException e) {
            System.out.println(e); // says calculation failed!!!
        }
    }

    // error handling

    static String name = null;
    static CompletableFuture<String> errorHandlingOfFuture = CompletableFuture.supplyAsync(() -> {
        if (name == null) {
            throw new RuntimeException("Computation error!");
        }
        return "Hello, " + name;
    }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
    // もし exception を出したいなら -> completeExceptionally()
    static CompletableFuture<String> exceptionInFuture =
            CompletableFuture.supplyAsync(() -> "Hello, " + name);

    // Async methods wraps ForkJoinTask instance..
    static CompletableFuture<String> parallelFutures =
            CompletableFuture
                    .supplyAsync(() -> "Hello")
                    .thenApplyAsync(s -> s + " World");
}