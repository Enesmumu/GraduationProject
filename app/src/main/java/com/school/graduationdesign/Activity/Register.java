package com.school.graduationdesign.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.school.graduationdesign.Administration.Bean.UserBean;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;
import com.school.graduationdesign.util.PanDuan;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 注册界面
 * @date 2016/3/31
 */
public class Register extends BaseActivity {
    private ImageView fanhui2;
    private EditText shoujihao, mima, queren_mima;
    private Button zhuce;
    private ContentValues values;
    private DBHelper helper;
    private SQLiteDatabase db;
    private UserBean bean;
    private String use_num;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void findView() {
        fanhui2 = (ImageView) findViewById(R.id.fanhui2);
        shoujihao = (EditText) findViewById(R.id.shoujihao);
        mima = (EditText) findViewById(R.id.mima);
        zhuce = (Button) findViewById(R.id.zhuce);
        queren_mima = (EditText) findViewById(R.id.queren_mima);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        fanhui2.setOnClickListener(this);
        zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui2:
                finish();
                break;
            case R.id.zhuce:
                use_num = null;
                String num = shoujihao.getText().toString().trim();
                openDB();
                Cursor cursor = db.query(DBConfig.User.USER_TABLE, new String[]{DBConfig.User.USER_NUM}, "user_num = ?", new String[]{num}, null, null, null);
                while (cursor.moveToNext()) {
                    use_num = cursor.getString(0);
                }
                if (TextUtils.isEmpty(use_num)) {
                    RegisterSuccess();
                } else {
                    Toast.makeText(this, "用户名已存在", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


//    private String ifNumOK() {
//        //查询数据库中列名为user_num的一列数据
//        //获取用户输入的内容
//        final String num = shoujihao.getText().toString().trim();
//        openDB();
//        Cursor cursor = db.query(DBConfig.User.USER_TABLE, new String[]{DBConfig.User.USER_NUM}, "user_num = ?", new String[]{num}, null, null, null);
//        while (cursor.moveToNext()) {
//            use_num = cursor.getString(0);
//        }
//        return use_num;
//    }

    private void RegisterSuccess() {
        //获取输入的信息
        String num = shoujihao.getText().toString().trim();
        String password1 = mima.getText().toString().trim();
        String password2 = queren_mima.getText().toString().trim();
        //打开数据库
        openDB();
        //传输数据
        values = new ContentValues();
        if (PanDuan.isEmail(num)) {
            values.put(DBConfig.User.USER_NUM, num);
            ifPasswordOk(password1, password2);
        } else if (PanDuan.isPhonenum(num)) {
            values.put(DBConfig.User.USER_NUM, num);
            ifPasswordOk(password1, password2);
        } else {
            Toast.makeText(this, "注册账号格式不正确", Toast.LENGTH_LONG).show();
        }
    }

    private void openDB() {
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
    }


    //判断密码是否一致
    private void ifPasswordOk(String password1, String password2) {
        if ((password1.length() >= 5 && password1.length() <= 19) && (password2.length() >= 5 && password2.length() <= 19)) {
            if (password1.equals(password2)) {
                values.put(DBConfig.User.USER_PASSWORD, password1);
                long row = db.insert(DBConfig.User.USER_TABLE, null, values);//向数据库user表中插入数据
                ifSuccess(row);
            } else {
                Toast.makeText(this, "输入密码不一致", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "密码长度必须是6--20位", Toast.LENGTH_LONG).show();
        }
    }


    private void ifSuccess(long row) {
        if (row != -1) {
            Toast.makeText(this, "成功", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Login.class));
            finish();
        } else {
            Toast.makeText(this, "失败", Toast.LENGTH_LONG).show();
        }
    }
}
