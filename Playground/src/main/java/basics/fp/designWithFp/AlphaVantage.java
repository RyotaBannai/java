package basics.fp.designWithFp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

public class AlphaVantage {
    // https://www.alphavantage.co/support/#api-key
    public static BigDecimal getPRice(final String ticker) {
        try {
            final URL url = new URL(
                    String.format("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=5min&apikey=%s&datatype=csv", ticker, "8HXZJO3QC6ZHZQ3L"));
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            // 一行ずつ読み込む代わりに stream の lines を使用
            final String data = reader.lines().skip(1).findFirst().get(); // skip: skips column titles.
//            reader.lines().forEach(System.out::println);
            final String[] dataItems = data.split(",");
            if (dataItems.length < 2)
                return new BigDecimal(0);
            else {
                System.out.println(ticker + ": " + dataItems[dataItems.length - 2]);
                return new BigDecimal(dataItems[dataItems.length - 2]); // time, high, enter, exit, low, volume
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}