package basics.fp.memo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/*
 *
 * java.util.Function
 * https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 * */
public class Success {
    public static void main(String[] args) {
        final List<Integer> priceValues = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
        final RodCutterBasic rodCutter = new RodCutterBasic(priceValues);
        System.out.println(rodCutter.maxProfit(3));
        System.out.println(rodCutter.maxProfit(22));
    }

    private static class RodCutterBasic {
        final List<Integer> prices;

        public RodCutterBasic(final List<Integer> pricesForLength) {
            prices = pricesForLength;
        }

        public int maxProfit(final int rodLength) {
            BiFunction<Function<Integer, Integer>, Integer, Integer> compute =
                    (func, length) -> {
                        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
                        for (int i = 1; i < length; i++) {
                            int priceWhenCut = func.apply(i) + func.apply(length - i);
                            if (profit < priceWhenCut) profit = priceWhenCut;
                        }
                        return profit;
                    };
            return Memoizer.callMemoized(compute, rodLength);
        }
    }

    public static class Memoizer {
        public static <T, R> R callMemoized(final BiFunction<Function<T, R>, T, R> function, final T input) {
            Function<T, R> memoized = new Function<>() {
                private final Map<T, R> store = new ConcurrentHashMap<>();
                public R apply(final T input) {
                    return store.computeIfAbsent(input, key -> function.apply(this, key));
                }
            };
            return memoized.apply(input);
        }
    }
}
