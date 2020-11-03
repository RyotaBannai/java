package basics.fp.mapReduce.stock_prices;


import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Csv {
    public static void main(String[] args) {
        System.out.println(get_price("AAPL"));
    }

    public static BigDecimal get_price(String symbol) {
        BigDecimal price = ((Map<String, String[]>) get_csv()).entrySet().stream()
                .filter(elem -> elem.getValue()[0].equals(symbol))
                .map(elem -> BigDecimal.valueOf(Double.valueOf(elem.getValue()[1])))
                .findFirst()
                .orElse(BigDecimal.ZERO);
        return price;
    }

    public static Map get_csv() {
        Map<String, String[]> store =
                Arrays.stream(csv().split("\n"))
                        .map(item -> item.split(","))
                        .collect(HashMap::new, (map, elem) -> map.put(elem[0], Arrays.stream(elem).skip(1).toArray(String[]::new)), HashMap::putAll);

        // test
//        store.entrySet().stream()
//                .peek(elem -> System.out.println(elem.getKey()))
//                .map(elem -> elem.getValue())
//                .forEach(value -> Arrays.stream(value).limit(2).forEach(System.out::println));

        return store;
    }

    // https://www.advfn.com/nasdaq/nasdaq.asp
    private static String csv() {
        // Company name, Symbol, Current Price, Change, Change in percent, Volumes\
        return "Apple,AAPL,108.77,-0.09,-0.08%,122,869,808\n" +
                "Insignia,ISIG,1.02,+0.18,+22.27%,55,641,382\n" +
                "Digital Ally,DGLY,3.04,+0.75,+32.75%,49,271,906\n" +
                "Intel,INTC,44.46,+0.18,+0.41%,33,860,788\n" +
                "Plug Power,PLUG,15.45,+1.45,+10.36%,32,024,295\n" +
                "Microsoft,MSFT,202.33,-0.14,-0.07%,30,843,728\n" +
                "Comcast,CMCSA,41.41,-0.83,-1.96%,24,480,430\n" +
                "Cisco Systems,CSCO,35.90,0.00,0.00%,23,483,249\n" +
                "Sirius Xm Radio Inc.,SIRI,5.77,+0.04,+0.70%,21,972,513\n" +
                "Gulfport Energy,GPOR,0.25,-0.01,-4.30%,13,308,337\n" +
                "Marvell,MRVL,36.90,-0.61,-1.63%,11,677,536\n" +
                "Ebay Inc.,EBAY,47.87,+0.24,+0.50%,10,765,800\n" +
                "Huntington Bancshares,HBAN,10.81,+0.37,+3.54%,10,387,826\n" +
                "Fuelcell Energy,FCEL,2.15,+0.15,+7.25%,10,169,848\n" +
                "Gilead Sciences,GILD,58.53,+0.38,+0.65%,9,352,738\n" +
                "American Capital Agency,AGNC,13.98,+0.01,+0.07%,9,001,128\n" +
                "Starbucks,SBUX,85.97,-0.99,-1.14%,8,904,851\n" +
                "Acorda Therapeutics,ACOR,1.08,+0.21,+23.54%,8,218,239\n" +
                "Peoples Bank,PBCT,11.31,+0.64,+6.00%,7,623,516\n" +
                "Nvidia,NVDA,503.23,+1.87,+0.37%,7,488,713\n" +
                "Applied Materials,AMAT,60.34,+1.11,+1.87%,7,475,407\n" +
                "Lm Ericsson Telephone Company Ads,ERIC,11.25,+0.04,+0.36%,7,395,195\n" +
                "Activision Blizzard,ATVI,76.42,+0.69,+0.91%,7,306,413\n" +
                "Amazon.com,AMZN,3,004.48,-31.67,-1.04%,7,264,273\n" +
                "Mylan Inc.,MYL,15.14,+0.60,+4.13%,7,260,684\n" +
                "Jetblue Airways,JBLU,12.01,+0.04,+0.29%,7,033,192\n" +
                "Microvision,MVIS,1.62,-0.11,-6.36%,6,928,980\n" +
                "Bed Bath & Beyond,BBBY,20.20,+0.40,+2.02%,6,060,870\n" +
                "Qualcomm Incorporated,QCOM,QCOM +0.61123.97,+0.61,+0.49%,5,860,276\n" +
                "Nuance Communications,NUAN,31.53,-0.38,-1.19%,5,271,332\n" +
                "Endo Pharmaceuticals Holdings Inc,ENDP,4.56,-0.01,-0.22%,5,232,055\n" +
                "Discovery Communications,DISCA,21.12,+0.88,+4.35%,5,148,504\n" +
                "Limelight Networks,LLNW,3.63,+0.10,+2.69%,5,031,529\n" +
                "Flextronics,FLEX,13.75,-0.40,-2.83%,5,018,360\n" +
                "Xilinx,XLNX,118.48,-0.21,-0.18%,4,992,890\n" +
                "Ftb,FITB,24.11,+0.89,+3.81%,4,727,494\n" +
                "Mdrna,MRNA,67.03,-0.44,-0.65%,4,650,268";
    }
}
