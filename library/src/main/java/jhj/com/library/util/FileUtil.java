package jhj.com.library.util;

import android.os.Environment;

import java.io.File;

/**
 * 文件管理工具类
 * Created by jhj on 17-7-27.
 */

public class FileUtil {

    /**
     * 新建文件夹
     *
     * @param subDir 　文件夹名称
     * @return　文件夹路径
     */
    public static String getSDPath(String subDir) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!path.endsWith("/"))
                path += "/";

            if (subDir != null && subDir.trim().length() > 0)
                path += (subDir + "/");

            File f = new File(path);

            if (!f.exists()) {
                if (f.mkdirs())
                    return path;
                else
                    return null;
            } else {
                if (f.isFile()) {
                    if (f.delete()) {
                        if (f.mkdir())
                            return path;
                        else
                            return null;
                    } else
                        return null;
                } else
                    return path;
            }
        }
        return null;
    }

}
