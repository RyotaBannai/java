package basics.fp.designWithFp;

import java.math.BigDecimal;
import java.util.function.Function;

public class CalculateNAV {
    private final Function<String, BigDecimal> priceFinder;

    public CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
        priceFinder = aPriceFinder;
    }

    public BigDecimal computeStockWorth(
            final String ticker, final int shares) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }

    public static void main(String[] args) {
        final CalculateNAV calculateNav = new CalculateNAV(AlphaVantage::getPrice);
        System.out.println(
                String.format("100 shares of Google worth: $%.2f",
                        calculateNav.computeStockWorth("GOOG", 100)));
    }
}
