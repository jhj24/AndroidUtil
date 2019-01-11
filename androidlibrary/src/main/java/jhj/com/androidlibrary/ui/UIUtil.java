package jhj.com.androidlibrary.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 界面信息
 * Created by jhj on 17-7-27.
 */

public class UIUtil {
    /**
     * dp to px
     *
     * @param context  context
     * @param dipValue dipValue
     * @return pxValue
     */
    public static int dip2px(Context context, int dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px To dp
     *
     * @param context context
     * @param pxValue pxValue
     * @return dp
     */
    public static int px2dip(Context context, int pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context context
     * @return px
     */
    public static int getWindowWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context contex
     * @return px
     */
    public static int getWindowHeigth(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    public static void percentWidth(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        int heigthPixels = dm.heightPixels;
        //return width * csw / WindowUtil.width;
    }

    /**
     * 显示软键盘
     *
     * @param context context
     * @param view    view
     */
    public static void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    /**
     * 隐藏软键盘
     *
     * @param context context
     * @param view    view
     */
    public static void dismissKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 设置EditText是否可以输入
     *
     * @param isEnable  　boolean
     * @param editTexts View
     */
    public static void setEditTextEnable(boolean isEnable, EditText... editTexts) {
        if (editTexts != null) {
            for (int i = editTexts.length - 1; i >= 0; i--) {
                EditText editText = editTexts[i];
                editText.setFocusable(isEnable);
                editText.setCursorVisible(isEnable);
                editText.setFocusableInTouchMode(isEnable);
                if (isEnable) {
                    editText.requestFocus();
                }
            }
        }
    }

    /**
     * 设置控件是否可见
     *
     * @param visibility int
     * @param views      View
     */
    public static void ssetViewVisibility(int visibility, View... views) {
        if (null != views) {
            for (View view : views) {
                view.setVisibility(visibility);
            }
        }
    }

}
