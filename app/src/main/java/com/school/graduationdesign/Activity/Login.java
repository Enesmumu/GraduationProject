package com.school.graduationdesign.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.Contants;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 登陆界面
 * @date 2016/3/31
 */
public class Login extends BaseActivity {
    private Button login_register;
    private ImageView fanhui1;
    private Button button_login;
    private EditText num;
    private EditText password;
    private TextView forget_password;
    private CheckBox login_check;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String zhanghao;
    private String mima;
    private boolean isChecked;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void findView() {
        num = (EditText) findViewById(R.id.num);
        password = (EditText) findViewById(R.id.password);
        fanhui1 = (ImageView) findViewById(R.id.fanhui1);
        login_register = (Button) findViewById(R.id.login_register);
        button_login = (Button) findViewById(R.id.button_login);
        forget_password = (TextView) findViewById(R.id.forget_password);
        login_check = (CheckBox) findViewById(R.id.login_check);
        //记住密码
        preferences = getSharedPreferences(Contants.CONFIG, MODE_PRIVATE);
        isChecked = preferences.getBoolean("isChecked", false);
        login_check.setChecked(isChecked);
        login_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Login.this.isChecked = isChecked;
            }
        });
        num.setText(preferences.getString("NUM", ""));
        password.setText(preferences.getString("PASSWORD", ""));
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        fanhui1.setOnClickListener(this);
        login_register.setOnClickListener(this);
        button_login.setOnClickListener(this);
        forget_password.setOnClickListener(this);
        login_check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui1:
                finish();
                break;
            case R.id.login_register:
                Intent intent1 = new Intent(Login.this, Register.class);
                startActivity(intent1);
                break;
            case R.id.forget_password:
                Intent intent2 = new Intent(Login.this, FindPassword.class);
                startActivity(intent2);
                break;
            case R.id.button_login:
                //当点击按钮是先获取信息   然后处理时间，用户什么时候操作，我们就什么时候处理
                zhanghao = num.getText().toString().trim();
                mima = password.getText().toString().trim();
                editor = preferences.edit();
                //打开数据库
                openDB();
                Cursor cursor = db.query(DBConfig.User.USER_TABLE, new String[]{DBConfig.User._ID}, DBConfig.User.USER_NUM + " =? and " + DBConfig.User.USER_PASSWORD + " =?",
                        new String[]{zhanghao, mima}, null, null, null);
                if (cursor.moveToNext()) {
                    String user_ID = cursor.getInt(cursor.getColumnIndex(DBConfig.User._ID)) + "";
                    editor.putString("USER_ID", user_ID);
                    editor.putString("NUM", zhanghao);
                    if (isChecked) {
                        editor.putString("PASSWORD", mima);

                    } else {
                        editor.putString("PASSWORD", "");
                    }
                    editor.putBoolean("isChecked", isChecked);
                    editor.commit();
                    Intent intent = new Intent(Login.this, HuaDong.class);
                    intent.putExtra("user",zhanghao);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                db.close();
                break;
        }
    }

    private void openDB() {
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
    }
}
