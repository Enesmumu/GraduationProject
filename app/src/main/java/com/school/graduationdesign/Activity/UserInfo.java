package com.school.graduationdesign.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.Contants;
import com.school.graduationdesign.Bean.UserUpdateBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/16
 */
public class UserInfo extends BaseActivity implements View.OnClickListener {
    private TextView user_tv_nick, user_tv_des, user_tv_phone, user_tv_school;
    private Button user_update;
    private ImageView userinfo_fanhui;
    private DBHelper helper;
    private SQLiteDatabase db;
    private List<UserUpdateBean> list = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private String num;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_user_info);
        openDB();
        getNum();
    }

    private void getNum() {
        sharedPreferences = this.getSharedPreferences(Contants.CONFIG, Context.MODE_PRIVATE);
        num = sharedPreferences.getString("NUM", "");
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(this);
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    @Override
    protected void findView() {
        user_tv_nick = (TextView) findViewById(R.id.user_tv_nick);
        user_tv_des = (TextView) findViewById(R.id.user_tv_des);
        user_tv_phone = (TextView) findViewById(R.id.user_tv_phone);
        user_tv_school = (TextView) findViewById(R.id.user_tv_school);
        userinfo_fanhui = (ImageView) findViewById(R.id.userinfo_fanhui);
        user_update = (Button) findViewById(R.id.user_update);
    }

    @Override
    protected void getData() {
        Cursor cursor = db.query(DBConfig.User.USER_TABLE, null, DBConfig.User.USER_NUM + "=?", new String[]{num}, null, null, null);
        while (cursor.moveToNext()) {
            String nick = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_NICK));
            String des = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_DES));
            String phone = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_PHONE));
            String school = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_SCHOOL));

            if (TextUtils.isEmpty(nick)) {

            } else {
                user_tv_nick.setText(nick);
            }
            if (TextUtils.isEmpty(des)) {

            } else {
                user_tv_des.setText(des);
            }
            if (TextUtils.isEmpty(phone)) {

            } else {
                user_tv_phone.setText(phone);
            }
            if (TextUtils.isEmpty(school)) {

            } else {
                user_tv_school.setText(school);
            }
            cursor.close();
            db.close();
        }
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        user_update.setOnClickListener(this);
        userinfo_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_update:
                Intent intent = new Intent(UserInfo.this, UserUpdate.class);
                startActivity(intent);
                break;
            case R.id.userinfo_fanhui:
                finish();
                break;
        }
    }
}
