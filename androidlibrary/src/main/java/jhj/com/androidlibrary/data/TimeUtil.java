package jhj.com.androidlibrary.data;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 时间处理工具类
 * Created by jhj on 17-7-31.
 */

public class TimeUtil {


    /**
     * 毫秒转换为指定格式的分秒
     *
     * @param time   milliseconds
     * @param format 00:00,00'00"等样式
     * @return 指定样式的分秒
     */
    public static String formotTime(long time, String format) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time - minutes * 60 * 1000);
        return String.format(Locale.CHINA, format, minutes, seconds);
    }


    /**
     * 毫秒值转换为00'00"样式，当分钟为0时，转换为00"样式
     *
     * @param time millisenconds
     * @return 00'00"
     */
    public static String formatTime(long time) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time - minutes * 60 * 1000);
        if (minutes == 0) {
            return String.format(Locale.CHINA, "%2d\"", seconds);
        } else {
            return String.format(Locale.CHINA, "%02d\'%02d\"", minutes, seconds);
        }
    }


}
