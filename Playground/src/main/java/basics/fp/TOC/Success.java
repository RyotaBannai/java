package basics.fp.TOC;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * Example - Factorial
 * http://www.fredosaurus.com/notes-java/data/numbers/60factorial.html
 */
public class Success {
    public static void main(String[] args) {
        System.out.println(
                String.format("the result is %d", factorial(BigInteger.valueOf(21))));
    }

    // 0 を渡すとエラーになる・invoke を呼ぶ必要がある -> 詳細は隠蔽
    public static BigInteger factorial(final BigInteger number) {
        return factorialTailRec(BigInteger.ONE, number).invoke();
    }

    private static TailCall<BigInteger> factorialTailRec(
            final BigInteger factorial,
            final BigInteger number) {
        if (number.equals(BigInteger.ONE)) {
            return TailCalls.done(factorial);
        } else {
            return TailCalls.call(
                    () -> factorialTailRec(BigIntegerMethods.multiply(factorial, number), BigIntegerMethods.decrement(number)));
        }
    }

    @FunctionalInterface
    public interface TailCall<T> {
        TailCall<T> apply();

        default boolean isComplete() {
            return false;
        }

        default T result() {
            throw new Error("not implemented");
        }

        // 最初に一度だけ呼ぶ
        default T invoke() {
            return Stream.iterate(this, TailCall::apply)
//                    .peek(current -> System.out.println(current.isComplete()))
                    .filter(TailCall::isComplete)
                    .findFirst()
                    .get()
                    .result();
        }
    }

    public static class TailCalls {
        public static <T> TailCall<T> call(final TailCall<T> nextCall) {
            return nextCall;
        }

        public static <T> TailCall<T> done(final T value) {
            return new TailCall<T>() {
                @Override
                public boolean isComplete() {
                    return true;
                }

                @Override
                public T result() {
                    return value;
                }

                @Override
                public TailCall<T> apply() {
                    throw new Error("not implemented;");
                }
            };
        }
    }

    public static class BigIntegerMethods {
        public static BigInteger decrement(final BigInteger number) {
            return number.subtract(BigInteger.ONE);
        }

        public static BigInteger multiply(
                final BigInteger first, final BigInteger second) {
            return first.multiply(second);
        }
    }
}
