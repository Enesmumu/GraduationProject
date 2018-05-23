package com.school.graduationdesign.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 数据库帮助类
 * @date 2016/4/20
 */
public class DBHelper extends SQLiteOpenHelper {
    //构造方法，确定数据库名称和数据库版本
    public DBHelper(Context context) {
        super(context, DBConfig.DB_NAME, null, DBConfig.DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String cinema_sql = "create table " + DBConfig.Cinema.CINEMA_TABLE + "(" + DBConfig.Cinema._ID
                + " integer primary key autoincrement" + DBConfig.POINT_TYPE +
                DBConfig.Cinema.CINEMA_NAME + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.Cinema.CINEMA_PIC + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.Cinema.CINEMA_ADD + DBConfig.TEXT_TYPE + ")";
        String movie_sql = "create table " + DBConfig.Movie.MOVIE_TABLE + "(" + DBConfig.Movie._ID
                + " integer primary key autoincrement" + DBConfig.POINT_TYPE +
                DBConfig.Movie.MOVIE_NAME + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.Movie.MOVIE_DES + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.Movie.MOVIE_PIC + DBConfig.TEXT_TYPE + ")";
        String user_sql = "create table " + DBConfig.User.USER_TABLE + "(" + DBConfig.User._ID
                + " integer primary key autoincrement" + DBConfig.POINT_TYPE +
                DBConfig.User.USER_NAME + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.User.USER_NUM + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.User.USER_NICK + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.User.USER_DES + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.User.USER_PHONE + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.User.USER_SCHOOL + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.User.USER_PASSWORD + DBConfig.TEXT_TYPE + ")";
        String cinematime_sql = "create table " + DBConfig.CinemaTime.CINEMATIME_TABLE + "(" + DBConfig.CinemaTime._ID
                + " integer primary key autoincrement" + DBConfig.POINT_TYPE +
                DBConfig.CinemaTime.CINEMATIME_NAME + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.CinemaTime.CINEMATIME_NUM + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.CinemaTime.CINEMATIME_PRICE + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.CinemaTime.CINEMATIME_TIME + DBConfig.TEXT_TYPE + ")";
        String administration_sql = "create table " + DBConfig.Administration.ADMINISTRATION_TABLE + "(" + DBConfig.Administration._ID
                + " integer primary key autoincrement" + DBConfig.POINT_TYPE +
                DBConfig.Administration.ADMINISTRATION_NAME + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.Administration.ADMINISTRATION_NUM + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.Administration.ADMINISTRATION_PASSWORD + DBConfig.TEXT_TYPE + ")";
        String seat_sql = "create table " + DBConfig.Seat.SEAT_TABLE + "(" + DBConfig.Seat._ID
                + " integer primary key autoincrement" + DBConfig.POINT_TYPE +
                DBConfig.Seat.SEAT_STATUS + DBConfig.TEXT_TYPE + DBConfig.POINT_TYPE +
                DBConfig.Seat.SEAT_NUM + DBConfig.TEXT_TYPE + ")";
        db.execSQL(seat_sql);

        db.execSQL(cinema_sql);
        db.execSQL(movie_sql);
        db.execSQL(user_sql);
        db.execSQL(cinematime_sql);
        db.execSQL(administration_sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
