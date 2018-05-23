package com.school.graduationdesign.Database;

import android.provider.BaseColumns;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 数据库契约类
 * @date 2016/4/20
 */
public class DBConfig {
    //定义数据库名字
    public static final String DB_NAME = "DesignDB";
    //定义数据库版本
    public static final int DB_VERSION = 1;
    //定义类型
    public static final String TEXT_TYPE = " text";
    //定义分隔符
    public static final String POINT_TYPE = ",";

    //定义内部类管理单独的表
    public static final class Cinema implements BaseColumns {
        //定义表名
        public static final String CINEMA_TABLE = "cinema";
        //定义列
        public static final String CINEMA_NAME = "cinema_name";
        public static final String CINEMA_ADD = "cinema_add";
        public static final String CINEMA_PIC = "cinema_pic";
    }

    public static final class Movie implements BaseColumns {
        //定义表名
        public static final String MOVIE_TABLE = "movie";
        //定义列
        public static final String MOVIE_NAME = "movie_name";
        public static final String MOVIE_DES = "movie_add";
        public static final String MOVIE_PIC = "movie_pic";
    }

    public static final class User implements BaseColumns {
        //定义表名
        public static final String USER_TABLE = "user";
        //定义列
        public static final String USER_NAME = "user_name";
        public static final String USER_NICK = "user_nick";
        public static final String USER_DES = "user_des";
        public static final String USER_NUM = "user_num";
        public static final String USER_PASSWORD = "user_password";
        public static final String USER_PHONE = "user_phone";
        public static final String USER_SCHOOL = "user_school";
    }

    public static final class CinemaTime implements BaseColumns {
        //定义表名
        public static final String CINEMATIME_TABLE = "cinematime";
        //定义列
        public static final String CINEMATIME_NAME = "cinematime_name";
        public static final String CINEMATIME_TIME = "cinematime_time";
        public static final String CINEMATIME_NUM = "cinematimr_num";
        public static final String CINEMATIME_PRICE = "cinematimr_price";
    }

    public static final class Administration implements BaseColumns {
        //定义表名
        public static final String ADMINISTRATION_TABLE = "administration";
        //定义列
        public static final String ADMINISTRATION_NAME = "administration_name";
        public static final String ADMINISTRATION_NUM = "administration_num";
        public static final String ADMINISTRATION_PASSWORD = "administration_password";
    }

    public static final class Seat implements BaseColumns {
        //定义表名
        public static final String SEAT_TABLE = "seat";
        //定义列
        public static final String SEAT_STATUS = "seat_status";
        public static final String SEAT_NUM = "seat_num";
    }
}
