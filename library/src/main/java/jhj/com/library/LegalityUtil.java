package jhj.com.library;

/**
 * 合法性验证
 * Created by jhj on 17-7-27.
 */

public class LegalityUtil {
    /**
     * 验证手机号是否合法
     *
     * @param num num
     * @return boolean
     */
    public static boolean isPhoneNum(String num) {
        if (num == null || "".equals(num)) {
            return false;
        } else {
            String s = num.replace(" ", "");
            String regex = "[1][34578]\\d{9}";
            return s.matches(regex);
        }
    }

    /**
     * 验证固话是否合法
     * 区号：以0开头，3位或4位
     * 座机号：7位或8位
     * 分机号：1到4位
     *
     * @param num num
     * @return boolean
     */
    public static boolean isTelephoneNum(String num) {
        if (num == null || "".equals(num)) {
            return false;
        } else {
            String s = num.replace(" ", "");
            String regex = "(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?";
            return s.matches(regex);
        }
    }


}
