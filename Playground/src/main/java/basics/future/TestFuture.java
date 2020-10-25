package basics.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {
    public static void main(String[] args) {
        Future<Integer> future = calculate(10);
        /**
         * ・Suppose we've triggered a task but, for some reason, we don't care about the result anymore.
         * We can use Future.cancel(boolean) to tell the executor to stop the operation and interrupt its underlying thread:
         * boolean canceled = future.cancel(true);
         *
         * ・Executors.newCachedThreadPool()：that reuses previously used Threads when they are available
         * */
        while (!future.isDone()) {
            System.out.println("Calculating...");
            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
            }
        }
        try {
            System.out.println("The result is: " + String.valueOf(future.get()));
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
        }
    }

    static ExecutorService executor = Executors.newSingleThreadExecutor(); //  capable of handling a single thread at a time

    public static Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
