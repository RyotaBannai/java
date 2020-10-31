import basics.fp.designWithFp.forTestPurpose.RodCutter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class ExceptionTest {
    private RodCutter rodCutter = new RodCutter();
    private List<Integer> prices = new ArrayList<Integer>();

    // x: too long
    @Test
    public void VerboseExceptionTest() throws RodCutter.RodCutterException {
        rodCutter.setPrices(prices);
        try {
            rodCutter.maxProfit(prices.size());
            fail("Expected exception for zero length");
        } catch (RodCutter.RodCutterException e) {
            assertTrue("expected", true);
        }
    }

    // x: どちらの関数が error なのかわからない
    @Test(expected = RodCutter.RodCutterException.class)
    public void TerseExceptionTest() throws RodCutter.RodCutterException {
        rodCutter.setPrices(prices);
        rodCutter.maxProfit(prices.size());
    }

    @Test
    public void ConciseExceptionTest() throws RodCutter.RodCutterException {
        rodCutter.setPrices(prices);
        TestHelper.assertThrows(
                RodCutter.RodCutterException.class, () -> rodCutter.maxProfit(prices.size()));
    }
}
