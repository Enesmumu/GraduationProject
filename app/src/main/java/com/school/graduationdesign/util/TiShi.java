package com.school.graduationdesign.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/11
 */
public class TiShi {
    /**
     * 显示提示
     *
     * @param activity 上下文
     * @param resId    提示的资源id
     */
    public static void showToast(Activity activity, int resId) {
        Toast.makeText(activity, resId, Toast.LENGTH_LONG).show();
    }


    public static void showToast(Activity activity, String value) {
        Toast.makeText(activity, value, Toast.LENGTH_LONG).show();
    }
}
