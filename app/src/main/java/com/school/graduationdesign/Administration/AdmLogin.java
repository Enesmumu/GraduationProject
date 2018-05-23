package com.school.graduationdesign.Administration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 管理员登陆界面
 * @date 2016/4/20
 */
public class AdmLogin extends BaseActivity implements View.OnClickListener {
    private EditText adm_num, adm_password;
    private Button adm_login_btn;
    private ImageView adm_fanhui;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_adm_login);
    }

    @Override
    protected void findView() {
        adm_fanhui = (ImageView) findViewById(R.id.adm_fanhui);
        adm_login_btn = (Button) findViewById(R.id.adm_login_btn);
    }

    @Override
    protected void getData() {
        adm_num = (EditText) findViewById(R.id.adm_num);
        adm_password = (EditText) findViewById(R.id.adm_password);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        adm_fanhui.setOnClickListener(this);
        adm_login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_fanhui:
                finish();
                break;
            case R.id.adm_login_btn:
                openDB();
                String num = adm_num.getText().toString().trim();
                String password = adm_password.getText().toString().trim();
                Cursor cursor = db.query(DBConfig.User.USER_TABLE, new String[]{DBConfig.User._ID}, DBConfig.User.USER_NUM + " =? and " + DBConfig.User.USER_PASSWORD + " =?",
                        new String[]{num, password}, null, null, null);
                if (num.equals("18203660985") && password.equals("541213470102")) {
                    Intent intent = new Intent(AdmLogin.this, Administration.class);
                    startActivity(intent);
                    finish();
                } else if(cursor.moveToNext()){
                    Intent intent = new Intent(AdmLogin.this, Administration.class);
                    startActivity(intent);
                    finish();

                }else
            {Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();}
                break;
        }
    }

    private void openDB() {
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(AdmLogin.this);
            AlertDialog dialog = builder.create();
            dialog.setMessage("确认退出？");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    android.os.Process.killProcess(android.os.Process.myPid());
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
        return super.onKeyDown(keyCode, event);
    }
}
