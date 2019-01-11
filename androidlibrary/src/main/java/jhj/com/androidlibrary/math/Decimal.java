package jhj.com.androidlibrary.math;

import java.util.Locale;

/**
 * 小数点处理
 * <p>
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
        return String.format(Locale.getDefault(), "%.2f", num);
    }
}
