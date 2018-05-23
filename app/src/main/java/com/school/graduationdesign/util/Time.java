package com.school.graduationdesign.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 时间戳
 * @date 2016/5/3
 */
public class Time {
    /**
     * 获取“年月日”格式的时间
     *
     * @param time 时间戳
     * @return
     */
    public static String getDate(long time,String format)
    {
        //获取日期
        Date date=new Date();
        //创建日期格式
        SimpleDateFormat Format=new SimpleDateFormat("HH,mm");
        //转换格式
        String Time=Format.format(date);
        return Time;
    }
}
