package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.school.graduationdesign.Administration.Bean.UserBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 账号管理界面
 * @date 2016/4/28
 */
public class AdmUserUpdate extends Activity implements View.OnClickListener {
    private Button adm_user_update, adm_user_del;
    private ImageView adm_up_user_fanhui;
    private EditText adm_up_user_password;
    private TextView adm_up_user_num;
    private UserBean bean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_user_update);
        //查找控件
        adm_up_user_fanhui = (ImageView) findViewById(R.id.adm_up_user_fanhui);
        adm_up_user_num = (TextView) findViewById(R.id.adm_up_user_num);
        adm_up_user_password = (EditText) findViewById(R.id.adm_up_user_password);
        adm_user_update = (Button) findViewById(R.id.adm_user_update);
        adm_user_del = (Button) findViewById(R.id.adm_user_del);
        //设置监听
        adm_user_update.setOnClickListener(this);
        adm_user_del.setOnClickListener(this);
        adm_up_user_fanhui.setOnClickListener(this);
        //获取数据
        bean = (UserBean) getIntent().getSerializableExtra("bean");
        adm_up_user_num.setText(bean.getAdm_user_list_num());
        adm_up_user_password.setText(bean.getAdm_user_list_password());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_up_user_fanhui:
                finish();
                break;
            case R.id.adm_user_update:
                updateData();
                break;
            case R.id.adm_user_del:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdmUserUpdate.this);
                AlertDialog dialog = builder.create();
                dialog.setMessage("确认删除？");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //打开数据库
                        DBHelper dbHelper = new DBHelper(AdmUserUpdate.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //删除数据库
                        int row = db.delete(DBConfig.User.USER_TABLE, DBConfig.Cinema._ID + "=?", new String[]{bean.getId() + ""});
                        isSuccess(row);
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
                break;
        }
    }

    private void isSuccess(int row) {
        if (row != 0) {
            Toast.makeText(AdmUserUpdate.this, "成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdmUserUpdate.this, AdmUser.class);
            startActivity(intent);
        } else {
            Toast.makeText(AdmUserUpdate.this, "失败", Toast.LENGTH_LONG).show();
        }
    }

    private void updateData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdmUserUpdate.this);
        AlertDialog dialog = builder.create();
        dialog.setMessage("确认修改？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取输入的信息
                String num = adm_up_user_num.getText().toString().trim();
                String password = adm_up_user_password.getText().toString().trim();
                //打开数据库，添加到数据库
                DBHelper dbHelper = new DBHelper(AdmUserUpdate.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储要插入的数据
                ContentValues values = new ContentValues();
                values.put(DBConfig.User.USER_NUM, num);
                values.put(DBConfig.User.USER_PASSWORD, password);
                //修改数据
                int row = db.update(DBConfig.User.USER_TABLE, values, DBConfig.User._ID + "=?", new String[]{bean.getId() + ""});
                isSuccess(row);
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
}
