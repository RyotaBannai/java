package basics.fp.memo;

import java.util.Arrays;
import java.util.List;

public class Fail {
    public static void main(String[] args) {
        final List<Integer> priceValues = Arrays.asList(2, 5, 2, 2, 2, 2, 1, 8, 9, 15);
        final RodCutterBasic rodCutter = new RodCutterBasic(priceValues);
        System.out.println(rodCutter.maxProfit(3, ""));
//        System.out.println(rodCutter.maxProfit(22));
    }

    private static class RodCutterBasic {
        final List<Integer> prices;

        public RodCutterBasic(final List<Integer> pricesForLength) {
            prices = pricesForLength;
        }

        public int maxProfit(final int length, String indexSign) {
            System.out.println(String.format("%s current length is: %d", indexSign, length));
            String currentIndexSign = indexSign + ">";
            int profit = (length <= prices.size()) ? prices.get(length - 1) : 0; // もし分割しなくても値段がつくのであればデフォルト値を使う

            for (int i = 1; i < length; i++) {
                int priceWhenCut = maxProfit(i, currentIndexSign) + maxProfit(length - i, currentIndexSign + "*");
                System.out.println(String.format("%s the price is %d", currentIndexSign, priceWhenCut));
                if (profit < priceWhenCut) profit = priceWhenCut;
            }
            return profit;
        }
    }
}
