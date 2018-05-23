package com.school.graduationdesign.util;

import android.app.Activity;
import android.content.SharedPreferences;

import com.school.graduationdesign.Bean.Contants;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 保存账号密码
 * @date 2016/4/11
 */
public class BaoCun {

    public static void saveString(Activity activity, String key, String value) {
        SharedPreferences preferences = activity.getSharedPreferences(Contants.CONFIG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static void saveBoolean(Activity activity, String key, boolean value) {
        SharedPreferences preferences = activity.getSharedPreferences(Contants.CONFIG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

}
