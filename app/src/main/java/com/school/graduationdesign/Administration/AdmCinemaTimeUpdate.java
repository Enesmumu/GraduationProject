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
import android.widget.Toast;

import com.school.graduationdesign.Administration.Bean.CinemaTimeBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/3
 */
public class AdmCinemaTimeUpdate extends Activity implements View.OnClickListener {
    private EditText adm_et_up_cinemaname, adm_et_up_cinematime, adm_et_up_cinemaprice, adm_et_up_cinemanum;
    private Button adm_cinematime_update, adm_cinematime_del;
    private ImageView adm_up_cinematime_fanhui;
    private CinemaTimeBean bean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_cinematime_update);
        //查找控件
        adm_et_up_cinemaname = (EditText) findViewById(R.id.adm_et_up_cinemaname);
        adm_et_up_cinematime = (EditText) findViewById(R.id.adm_et_up_cinematime);
        adm_et_up_cinemaprice = (EditText) findViewById(R.id.adm_et_up_cinemaprice);
        adm_et_up_cinemanum = (EditText) findViewById(R.id.adm_et_up_cinemanum);
        adm_cinematime_update = (Button) findViewById(R.id.adm_cinematime_update);
        adm_cinematime_del = (Button) findViewById(R.id.adm_cinematime_del);
        adm_up_cinematime_fanhui = (ImageView) findViewById(R.id.adm_up_cinematime_fanhui);
        //设置监听
        adm_cinematime_update.setOnClickListener(this);
        adm_cinematime_del.setOnClickListener(this);
        adm_up_cinematime_fanhui.setOnClickListener(this);
        //获取数据
        bean = (CinemaTimeBean) getIntent().getSerializableExtra("bean");
        adm_et_up_cinemaname.setText(bean.getAdm_cinematime_list_name());
        adm_et_up_cinematime.setText(bean.getAdm_cinematime_list_time());
        adm_et_up_cinemaprice.setText(bean.getAdm_cinematime_list_price());
        adm_et_up_cinemanum.setText(bean.getAdm_cinematime_list_num());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_up_cinematime_fanhui:
                finish();
                break;
            case R.id.adm_cinematime_update:
                updateData();
                break;
            case R.id.adm_cinematime_del:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdmCinemaTimeUpdate.this);
                AlertDialog dialog = builder.create();
                dialog.setMessage("确认删除？");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //打开数据库
                        DBHelper dbHelper = new DBHelper(AdmCinemaTimeUpdate.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //删除数据库
                        int row = db.delete(DBConfig.CinemaTime.CINEMATIME_TABLE, DBConfig.CinemaTime._ID + "=?", new String[]{bean.getId() + ""});
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
            Toast.makeText(AdmCinemaTimeUpdate.this, "成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdmCinemaTimeUpdate.this, AdmCinemaTime.class);
            startActivity(intent);
        } else {
            Toast.makeText(AdmCinemaTimeUpdate.this, "失败", Toast.LENGTH_LONG).show();
        }
    }

    private void updateData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdmCinemaTimeUpdate.this);
        AlertDialog dialog = builder.create();
        dialog.setMessage("确认修改？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取输入的信息
                String name = adm_et_up_cinemaname.getText().toString().trim();
                String time = adm_et_up_cinematime.getText().toString().trim();
                String price = adm_et_up_cinemaprice.getText().toString().trim();
                String num = adm_et_up_cinemanum.getText().toString().trim();
                //打开数据库，添加到数据库
                DBHelper dbHelper = new DBHelper(AdmCinemaTimeUpdate.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储要插入的数据
                ContentValues values = new ContentValues();
                values.put(DBConfig.CinemaTime.CINEMATIME_NAME, name);
                values.put(DBConfig.CinemaTime.CINEMATIME_TIME, time);
                values.put(DBConfig.CinemaTime.CINEMATIME_PRICE, price);
                values.put(DBConfig.CinemaTime.CINEMATIME_NUM, num);
                //修改数据
                int row = db.update(DBConfig.CinemaTime.CINEMATIME_TABLE, values, DBConfig.CinemaTime._ID + "=?", new String[]{bean.getId() + ""});
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
