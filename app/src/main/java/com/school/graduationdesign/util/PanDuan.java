package com.school.graduationdesign.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 判断是否为手机号
 * @date 2016/4/12
 */
public class PanDuan {

    public static boolean isPhonenum(String phoneNum) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }

    public static boolean isEmail(String emailAdd) {
        String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(emailAdd);
        if (m.matches()) {
            return true;
        }else {
            return false;
        }
    }
}
