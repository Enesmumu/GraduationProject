package com.school.graduationdesign.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.school.graduationdesign.Bean.Contants;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 判断账号密码
 * @date 2016/4/12
 */
public class CheckedInfo {
    public static void Check(Activity activity, SharedPreferences.Editor editor, String value1, String value2) {
        if (!TextUtils.isEmpty(value1) && PanDuan.isPhonenum(value1)) {
            if (!TextUtils.isEmpty(value2) && value2.length() > 5) {
                //保存用户信息
                editor.putString(Contants.USERNAME, value1);
                editor.putString(Contants.PASSWORD, value2);
                //开启服务
                editor.commit();
            } else {
                Toast.makeText(activity, "输入的密码格式不正确", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(activity, "输入账号格式不正确", Toast.LENGTH_SHORT).show();
        }
    }
}
