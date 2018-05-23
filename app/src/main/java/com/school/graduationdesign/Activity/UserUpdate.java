package com.school.graduationdesign.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.Contants;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/16
 */
public class UserUpdate extends BaseActivity implements View.OnClickListener {
    private EditText user_et_nick, user_et_des, user_et_phone, user_et_school;
    private TextView user_nick, user_des, user_phone, user_school;
    private Button user_save;
    private ImageView userupdate_fanhui;
    private DBHelper helper;
    private SQLiteDatabase db;
    private SharedPreferences sharedPreferences;
    private String num;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_user_update);
        openDB();
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(this);
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    @Override
    protected void findView() {
        user_nick = (TextView) findViewById(R.id.user_nick);
        user_et_nick = (EditText) findViewById(R.id.user_et_nick);
        user_des = (TextView) findViewById(R.id.user_des);
        user_et_des = (EditText) findViewById(R.id.user_et_des);
        user_phone = (TextView) findViewById(R.id.user_phone);
        user_et_phone = (EditText) findViewById(R.id.user_et_phone);
        user_school = (TextView) findViewById(R.id.user_school);
        user_et_school = (EditText) findViewById(R.id.user_et_school);
        user_save = (Button) findViewById(R.id.user_save);
        userupdate_fanhui = (ImageView) findViewById(R.id.userupdate_fanhui);
    }

    @Override
    protected void getData() {
        sharedPreferences = getSharedPreferences(Contants.CONFIG, Context.MODE_PRIVATE);
        num = sharedPreferences.getString("NUM", "");
        Cursor cursor = db.query(DBConfig.User.USER_TABLE, null, DBConfig.User.USER_NUM + "=?", new String[]{num}, null, null, null);
        while (cursor.moveToNext()) {
            String nick = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_NICK));
            String des = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_DES));
            String phone = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_PHONE));
            String school = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_SCHOOL));

            if (TextUtils.isEmpty(nick)) {

            } else {
                user_et_nick.setText(nick);
            }
            if (TextUtils.isEmpty(des)) {

            } else {
                user_et_des.setText(des);
            }
            if (TextUtils.isEmpty(phone)) {

            } else {
                user_et_phone.setText(phone);
            }
            if (TextUtils.isEmpty(school)) {

            } else {
                user_et_school.setText(school);
            }
        }
        cursor.close();
        db.close();
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        user_save.setOnClickListener(this);
        userupdate_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_save:
                openDB();
                updateData();
                break;
            case R.id.userupdate_fanhui:
                getNum();
                finish();
                break;
        }
    }

    private void updateData() {
        final ContentValues values = new ContentValues();
        AlertDialog.Builder builder = new AlertDialog.Builder(UserUpdate.this);
        AlertDialog dialog = builder.create();
        dialog.setMessage("确认修改？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取输入的信息
                String nick1 = user_et_nick.getText().toString().trim();
                String des1 = user_et_des.getText().toString().trim();
                String phone1 = user_et_phone.getText().toString().trim();
                String school1 = user_et_school.getText().toString().trim();
                //存储要插入的数据
                values.put(DBConfig.User.USER_NICK, nick1);
                values.put(DBConfig.User.USER_DES, des1);
                values.put(DBConfig.User.USER_PHONE, phone1);
                values.put(DBConfig.User.USER_SCHOOL, school1);
                //修改数据
                db.update(DBConfig.User.USER_TABLE, values, DBConfig.User.USER_NUM + "=?", new String[]{num});
                db.close();
                finish();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void getNum() {
        sharedPreferences = this.getSharedPreferences(Contants.CONFIG, Context.MODE_PRIVATE);
        num = sharedPreferences.getString("NUM", "");
    }
}
