package jhj.com.androidlibrary.util;

/**
 * 字符串处理
 * Created by jhj on 17-7-31.
 */

public class StringUtil {
    /**
     * 字符串s每隔长度leng进行换行
     *
     * @param s    字符串
     * @param leng 字符串长度
     * @return 新字符串
     */
    public String subString(String s, int leng) {
        StringBuilder s1 = new StringBuilder(s);
        int index;
        for (index = leng; index < s1.length(); index += leng + 1) {
            s1.insert(index, '\n');
        }
        return s1.toString();
    }

}
