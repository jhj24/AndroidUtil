package jhj.com.androidlibrary.date;

import java.text.SimpleDateFormat;
import java.util.Date;
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


    public static String formatTime(Date date) {

        String dateString = null;
        Date now = new Date();

        try {
            long endTime = now.getTime();
            long currentTime = date.getTime();
            long seconds = (endTime - currentTime) / 1000;
            if (seconds < 10) {
                dateString = "刚刚";
            } else if (seconds < 60) {
                dateString = seconds + "秒前";
            } else if (seconds < 60 * 60) {
                dateString = seconds / 60 + "分钟前";
            } else if (seconds < 60 * 60 * 24) {
                dateString = seconds / 60 / 24 + "小时前";
            } else if (seconds < seconds * 60 * 60 * 24 * 30) {
                dateString = seconds / 60 / 60 / 24 + "天前";
            } else if (date.getYear() == now.getYear()) {
                dateString = new SimpleDateFormat("MM-dd HH:mm").format(date);
            } else {
                dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;


    }


}
