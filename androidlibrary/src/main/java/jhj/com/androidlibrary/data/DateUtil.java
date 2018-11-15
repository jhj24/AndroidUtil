package jhj.com.androidlibrary.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期类处理工具
 * <p/>
 * Date:Tue Jul 05 11:00:33 GMT+08:00 2016
 * Calendar:
 * Long: 1501580257000
 * <p/>
 * Created by jhj on 17-7-27.
 */

public class DateUtil {

    /**
     * String -> long
     *
     * @param str     日期（eg、2016/7/5；2016/07/05 10:20等）
     * @param pattern 日期格式(eg、yyyy/MM/dd；yyyy/MM/dd HH:mm等)
     * @return long
     * @throws ParseException exception
     */
    public static long getStringToMillis(String str, String pattern) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        c.setTime(sdf.parse(str));
        return c.getTimeInMillis();
    }

    /**
     * Date -> long
     *
     * @param date Date
     * @return long
     */
    public static long getDateToMillis(Date date) {
        return date.getTime();
    }


    /**
     * Calendar -> long
     *
     * @param calendar calendar
     * @return long
     */
    public static long getCalanderToMillis(Calendar calendar) {
        return calendar.getTime().getTime();
    }

    /**
     * long -> String
     *
     * @param millis  long
     * @param pattern eg、"yyyy-MM-dd HH:mm"
     * @return 日期
     */
    public static String milliscondToString(long millis, String pattern) {
        Date d = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(d);
    }

    /**
     * Date -> String
     *
     * @param date    Date
     * @param pattern eg、"yyyy-MM-dd"..
     * @return String String
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(date);
    }


    /**
     * Calendar -> String
     *
     * @param calendar calendar
     * @param pattern  eg、"yyyy-MM-dd"..
     * @return String
     */
    public static String getCalendarToString(Calendar calendar, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(calendar.getTime());
    }

    /**
     * Calendar -> Date
     *
     * @param calendar calendar
     * @return Date
     */
    public static Date getCalendarToData(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * long -> Date
     *
     * @param millis long
     * @return Date
     */
    public static Date getLongToDate(long millis) {
        return new Date(millis);
    }

    /**
     * String -> Date
     *
     * @param string  eg、2017-7-25
     * @param pattern eg、yyyy-MM-dd
     * @return Date
     * @throws ParseException
     */
    public static Date getStringToDate(String string, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.parse(string);
    }

    /**
     * date -> Calendar
     *
     * @param date date
     * @return Calendar
     */
    public static Calendar getDateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * long -> Calendar
     *
     * @param millis millis
     * @return calendar
     */
    public static Calendar getLongToCalendar(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar;
    }


    /**
     * 获取当天起始时间的毫秒值
     *
     * @return long
     */
    public static long getMillisOfToday() {
        Calendar calendar = Calendar.getInstance();
        return chooseDate(calendar);
    }

    /**
     * 获取当天所在的一周的周一起始时间的毫秒值
     *
     * @return long
     */
    public static long getMillisOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return chooseDate(calendar);
    }

    /**
     * 获取当天所在的月份1号起始时间的毫秒值
     *
     * @return long
     */
    public static long getMillisOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return chooseDate(calendar);
    }

    /**
     * 设置时间为当天时间的0点0时0分0秒0毫秒
     *
     * @return long 毫秒值
     */
    private static long chooseDate(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTimeInMillis();
    }

    /**
     * 判断两个日期是不是同一天
     *
     * @param d1
     * @param d2
     * @return boolean
     */
    public static Boolean isSameDay(Date d1, Date d2) {

        if (null != d1 && null != d2) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(d1);
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.setTime(d2);
            return cal.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH)
                    && cal.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
                    && cal.get(Calendar.DAY_OF_MONTH) == selectedDate.get(Calendar.DAY_OF_MONTH);
        } else {
            return false;
        }
    }

    /**
     * 返回指定友好格式日期
     *
     * @return String
     */
    public static String getFriendlyDate(long millis) {
        long oneDayMillis = 24 * 60 * 60 * 1000;

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

        long todayMillis = getMillisOfToday();
        long yesterday = todayMillis - oneDayMillis;
        long beforeYesterday = todayMillis - oneDayMillis * 2;

        Calendar thisYear = Calendar.getInstance();
        thisYear.setTimeInMillis(todayMillis);
        thisYear.set(Calendar.DAY_OF_YEAR, 1);
        long thisYearMillis = thisYear.getTimeInMillis();


        if (millis >= todayMillis) { //当天 -> HH:mm
            return sdf.format(new Date(millis));
        } else if (millis < todayMillis && millis >= yesterday) { //昨天 -> 昨天 HH:mm
            return String.format("昨天 %s", sdf.format(new Date(millis)));
        } else if (millis < yesterday && millis >= beforeYesterday) { //前天 -> 前天 HH:mm
            return String.format("前天 %s", sdf.format(new Date(millis)));
        } else if (millis < beforeYesterday && millis >= thisYearMillis) { //今年 -> MM:dd HH:mm
            return sdf1.format(new Date(millis));
        } else { //往年 -> yyyy-MM-dd
            return sdf2.format(new Date(millis));
        }

    }

}
