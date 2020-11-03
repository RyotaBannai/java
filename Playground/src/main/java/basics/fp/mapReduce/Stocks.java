package basics.fp.mapReduce;

import basics.fp.designWithFp.AlphaVantage;
import basics.fp.mapReduce.stock_prices.StockPrices;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Stocks {
    public static void main(String[] args) {
//        getOverHundred();
        findHighPriced(Tickers.symbols.parallelStream());
    }

    private static void findHighPriced(final Stream<String> symbols) {
        final StockPrices.StockInfo highPriced =
                symbols.map(StockPrices::getStockInfo)
                        .filter(isPriceLessThan(500))
                        .reduce(Stocks::pickHigh)
                        .orElse(null);
        System.out.println("High priced under $500 is " + highPriced);
    }

    private static Predicate<StockPrices.StockInfo> isPriceLessThan(final int price) {
        return stockInfo -> stockInfo.price.compareTo(BigDecimal.valueOf(price)) < 0; // -1 0 1
    }

    private static StockPrices.StockInfo pickHigh(final StockPrices.StockInfo stock1, final StockPrices.StockInfo stock2) {
        return stock1.price.compareTo(stock2.price) > 0 ? stock1 : stock2;
    }

    private static void getOverHundred() {
        final BigDecimal HUNDRED = new BigDecimal("100");
        System.out.println("Stocks priced over $100 are " +
                Tickers.symbols
                        .stream()
                        .filter(symbol -> StockPrices.getPrice(symbol).compareTo(HUNDRED) > 0)
                        .sorted()
                        .collect(joining(", "))
        );
    }

    private static class Tickers {
        public static final List<String> symbols = Arrays.asList(
                "AMD", "HPQ", "IMB", "TXN", "VMW", "XRX", "AAPL", "ADBE",
                "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU",
                "MSFT", "ORCL", "TIBX", "VRSN", "YHOO", "NVDA");
    }
}
