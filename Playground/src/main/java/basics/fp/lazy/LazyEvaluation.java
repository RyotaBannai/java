package basics.fp.lazy;

import java.util.function.Supplier;

public class LazyEvaluation {
    public static class Evaluation {
        public boolean evaluate(final int value) throws InterruptedException {
            System.out.println("evaluating... " + value);
            // simulateTimeConsumingOp(1000);
            return value > 100;
        }

        private void simulateTimeConsumingOp(Integer waitingTime) throws InterruptedException {
            Thread.sleep(waitingTime);
        }

        public void eagerEvaluator(
                final boolean input1, final boolean input2) {
            System.out.println("eagerEvaluator called...");
            System.out.println("accept?: " + (input1 && input2));
        }

        public void lazyEvaluator(
                final UseInstance<Boolean, InterruptedException> input1,
                final UseInstance<Boolean, InterruptedException> input2) throws InterruptedException {
            System.out.println("lazyEvaluator called...");
            System.out.println("accept?: " + (input1.get() && input2.get()));
        }

        @FunctionalInterface
        public interface UseInstance<R, X extends Throwable> {
            R get() throws X;
        }

        public static void main(String[] args) {
            Evaluation evaluation = new Evaluation();
            try {
                evaluation.eagerEvaluator(
                        evaluation.evaluate(100), evaluation.evaluate(100));

                evaluation.lazyEvaluator(
                        () -> evaluation.evaluate(100),
                        () -> evaluation.evaluate(100)); // 二つ目の引数は一つ目の引数が false の場合呼ばれない!
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
    }
}