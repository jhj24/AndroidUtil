package jhj.com.androidlibrary.math;

/**
 * 合法性验证
 * Created by jhj on 17-7-27.
 */

public class LegalityUtil {


    /**
     * 正则：手机号（精确）
     */
    private static final String PHONE = "^1[34578]\\d{9}$";
    /**
     * 区号：以0开头，3位或4位
     * 座机号：7位或8位
     * 分机号：1到4位
     * 正则：电话号码
     */
    private static final String TELEPHONE = "(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?";
    /**
     * 正则：身份证号码15位
     */
    private static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * 正则：身份证号码18位
     */
    private static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    /**
     * 正则：邮箱
     */
    private static final String REGEX_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    /**
     * 正则：URL
     */
    private static final String REGEX_URL = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";
    /**
     * 正则：汉字
     */
    private static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";

    private static boolean isMatch(String str, String regex) {
        if (str == null || "".equals(str)) {
            return false;
        } else {
            String s = str.replace(" ", "");
            return s.matches(regex);
        }
    }

    /**
     * 验证手机号是否合法
     *
     * @param num num
     * @return boolean
     */
    public static boolean isPhoneNum(String num) {
        return isMatch(num, PHONE);
    }

    /**
     * 验证固话合法性
     *
     * @param num num
     * @return boolean
     */
    public static boolean isTelephoneNum(String num) {
        return isMatch(num, TELEPHONE);
    }

    /**
     * 验证15位省份证合法性
     *
     * @param num num
     * @return boolean
     */
    public static boolean isIdCard15(String num) {
        return isMatch(num, REGEX_ID_CARD15);
    }

    /**
     * 验证15位省份证合法性
     *
     * @param num num
     * @return boolean
     */
    public static boolean isIdCard18(String num) {
        return isMatch(num, REGEX_ID_CARD18);
    }

    /**
     * 验证邮箱
     *
     * @param num num
     * @return boolean
     */
    public static boolean isEmail(String num) {
        return isMatch(num, REGEX_EMAIL);
    }

    /**
     * 验证URL
     *
     * @param num 待验证文本
     * @return boolean
     */
    public static boolean isURL(String num) {
        return isMatch(num, REGEX_URL);
    }

    /**
     * 验证汉字
     *
     * @param str string
     * @return boolean
     */
    public static boolean isChinese(String str) {
        return isMatch(str, REGEX_ZH);
    }


}
