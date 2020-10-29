import basics.fp.designWithFp.CalculateNAV;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculateNAVTest {
    @Test
    public void computeStockWorth() {
        final CalculateNAV calculateNAV =
                new CalculateNAV(ticker -> new BigDecimal("6.01"));
        BigDecimal expected = new BigDecimal("6010.00");
        assertEquals(expected, calculateNAV.computeStockWorth("GOOG", 1000));
    }
}
