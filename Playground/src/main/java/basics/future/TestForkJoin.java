package basics.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestForkJoin {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(3);
        forkJoinPool.execute(calculator);
        try {
            System.out.println(String.valueOf(calculator.get()));
        } catch (InterruptedException | ExecutionException e) {
        }
    }

    public static class FactorialSquareCalculator extends RecursiveTask<Integer> {
        private Integer n;

        public FactorialSquareCalculator(Integer n) {
            this.n = n;
        }

        @Override
        protected Integer compute()  {
            System.out.println(n * n);
            if (n <= 1) return n;

            FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);
            calculator.fork(); // ask ForkJoinPool to initiate the execution of this subtask.

            return n * n + calculator.join(); // join method will the return of sub thread's calculation
            // so the order of calculation is like, n * n + (n - 1) * (n - 1) + (n - 2) * (n - 2) +...
        }
    }
}
