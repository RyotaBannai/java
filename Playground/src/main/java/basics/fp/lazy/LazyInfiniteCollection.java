package basics.fp.lazy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LazyInfiniteCollection {
    public static void main(String[] args) {
//        System.out.println(primes(1)); // java.lang.StackOverflowError
        System.out.println(primesNew(1, 10));
    }

    private static boolean isPrime(final int number) {
        // 2 とその平方根の間の数で割り切れない場合は、その数は素数である
        // rangeClosed() != range(). rangeClosed は 閉区間（closed interval）
        // 範囲内の全ての値に対してラムダ式が false を返す場合 noneMatch() メソッドは boolean = true を返す -> つまり除数が存在しない
        return number > 1 &&
                IntStream.rangeClosed(2, (int) Math.sqrt(number))
                        .noneMatch(divisor -> number % divisor == 0);
    }

    private static List<Integer> concat(final int prime, final List<Integer> primes) {
        primes.add(prime);
        return primes;
    }

    // primes は再起関数
    private static List<Integer> primes(final int number) {
        if (isPrime(number)) {
            return concat(number, primes(number + 1));
        } else {
            return primes(number + 1);
        }
    }

    private static List<Integer> primesNew(final int fromNumber, final int count) {
        return Stream.iterate(primeAfter(fromNumber - 1), LazyInfiniteCollection::primeAfter) // UnaryOperator を使う
                .limit(count)
                .collect(toList());
    }

    private static int primeAfter(final int number) {
        System.out.println(String.format("Current number is: %d", number));
        if (isPrime(number + 1)) {
            return number + 1;
        } else {
            return primeAfter(number + 1);
        }
    }
    /**
     * UnaryOperator:
     *   takes one argument, and returns a result of the same type of its arguments.
     * @FunctionalInterface
     * public interface UnaryOperator<T> extends Function<T, T>{
     * }
     *
     * Function:
     *   takes any arguments, and returns a result of any type.
     * @FunctionInterface
     * public interface Function<R, T> {
     *     R apply(T t);
     * }
     *
     * reference: https://mkyong.com/java8/java-8-unaryoperator-examples/#:~:text=In%20Java%208%2C%20UnaryOperator%20is,interface%20and%20it%20extends%20Function%20.&text=The%20Function%20takes%20one%20argument,a%20result%20of%20any%20type.
     * */
}
