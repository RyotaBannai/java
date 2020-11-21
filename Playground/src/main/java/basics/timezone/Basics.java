package basics.timezone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Stream;

public class Basics {
    public static void main(String[] args) {
        getTimeZoneIds();
    }

    private static void getCurrentDatetime() {
        // タイムゾーン ID とそれぞれの意味のタイムゾーンリスト
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo"); // TZ database names
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        sdf.setTimeZone(tz); // Tokyo 時刻をセット
        System.out.println(sdf.format(new Date()));
    }

    private static void getTimeZoneIds() {
        Stream.of(TimeZone.getAvailableIDs()).forEach(System.out::println);
    }
}
