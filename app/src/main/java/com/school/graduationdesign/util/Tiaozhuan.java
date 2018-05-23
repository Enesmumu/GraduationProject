package com.school.graduationdesign.util;

import android.app.Activity;
import android.content.Intent;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/11
 */
public class Tiaozhuan {

    public static void jumpActivity(Activity activity, Class cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }


    public static void jumpFinishActivity(Activity activity, Class cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }
}
