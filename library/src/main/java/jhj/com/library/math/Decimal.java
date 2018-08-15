package jhj.com.library.math;

import java.util.Locale;

/**
 * Created by jhj on 18-8-15.
 */

public class Decimal {

    /**
     * 去除小数后面多余的0
     *
     * @param num double
     * @return String
     */
    public static String decimalDeal(double num) {
        String string = String.valueOf(num);
        if (string.contains(".")) {
            string = string.replaceAll("0+?$", "");
            string = string.replaceAll("[.]$", "");
        }
        return string;
    }

    /**
     * 保留两位有效小数
     *
     * @param num double
     * @return String
     */
    public static String a(double num) {
        return String.format(Locale.CHINA, "%.2f", num);
    }
}
