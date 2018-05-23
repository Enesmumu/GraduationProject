package com.school.graduationdesign.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.school.graduationdesign.Administration.AdmLogin;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 开始界面
 * @date 2016/3/31
 */
public class Start extends BaseActivity {
    private Button button1;
    private Button button2;
    private TextView adm_login;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_start);

    }

    @Override
    protected void findView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        adm_login= (TextView) findViewById(R.id.adm_login);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        adm_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent1=new Intent(Start.this,Login.class);
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2=new Intent(Start.this,Register.class);
                startActivity(intent2);
                break;
            case R.id.adm_login:
                Intent intent3=new Intent(Start.this, AdmLogin.class);
                startActivity(intent3);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Start.this);
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
