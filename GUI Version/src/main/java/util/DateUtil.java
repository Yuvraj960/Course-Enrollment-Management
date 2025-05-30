package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }
}