package basics.fp.designWithFp.forTestPurpose;

import java.util.List;

public class RodCutter {
    public void setPrices(final List<Integer> prices) throws RodCutterException {
        throw new RodCutterException("RodCutterException: setPrices()");
    }

    public int maxProfit(final int length) throws RodCutterException {
        if (length == 0) throw new RodCutterException("RodCutterException: maxProfit()");
        return 0;
    }

    public class RodCutterException extends Exception {
        // warningを回避するための宣言
        private static final long serialVersionUID = 1L;

        // コンストラクタ
        RodCutterException(String msg) {
            super(msg);
        }
    }
}
