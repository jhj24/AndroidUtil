package jhj.com.androidlibrary.util;

import android.util.Log;

import jhj.com.androidlibrary.BuildConfig;

/**
 * Created by jhj on 19-1-11.
 */
public class Logger {

    private static String TAG = "";

    private static boolean isDebug() {
        return !BuildConfig.DEBUG;
    }


    public static void w(String msg) {
        if (isDebug())
            return;
        Log.w(TAG, msg);
    }

    public static void i(String msg) {
        if (isDebug())
            return;
        Log.i(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug())
            return;
        Log.e(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug())
            return;
        Log.d(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug())
            return;
        Log.v(TAG, msg);
    }
}
