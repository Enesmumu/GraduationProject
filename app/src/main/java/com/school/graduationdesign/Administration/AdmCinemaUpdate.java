package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.school.graduationdesign.Administration.Bean.AddCinemaBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 影院信息修改界面
 * @date 2016/4/20
 */
public class AdmCinemaUpdate extends Activity implements View.OnClickListener {
    private Button adm_cinema_update, adm_cinema_del;
    private ImageView adm_up_fanhui;
    private EditText adm_et_up_cinema, adm_et_up_ad;
    private AddCinemaBean bean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_cinema_update);
        //查找控件
        adm_cinema_update = (Button) findViewById(R.id.adm_cinema_update);
        adm_cinema_del = (Button) findViewById(R.id.adm_cinema_del);
        adm_up_fanhui = (ImageView) findViewById(R.id.adm_up_fanhui);
        adm_et_up_cinema = (EditText) findViewById(R.id.adm_et_up_cinema);
        adm_et_up_ad = (EditText) findViewById(R.id.adm_et_up_ad);
        //设置监听
        adm_up_fanhui.setOnClickListener(this);
        adm_cinema_update.setOnClickListener(this);
        adm_cinema_del.setOnClickListener(this);
        //获取数据
        bean = (AddCinemaBean) getIntent().getSerializableExtra("bean");
        adm_et_up_cinema.setText(bean.getAdm_cinema_list_name());
        adm_et_up_ad.setText(bean.getAdd_cinema_list_ad());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_up_fanhui:
                finish();
                break;
            case R.id.adm_cinema_update:
                updateData();
                break;
            case R.id.adm_cinema_del:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdmCinemaUpdate.this);
                AlertDialog dialog = builder.create();
                dialog.setMessage("确认删除？");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //打开数据库
                        DBHelper dbHelper = new DBHelper(AdmCinemaUpdate.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //删除数据库
                        int row = db.delete(DBConfig.Cinema.CINEMA_TABLE, DBConfig.Cinema._ID + "=?", new String[]{bean.getId() + ""});
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
            Toast.makeText(AdmCinemaUpdate.this, "成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdmCinemaUpdate.this, AdmCinema.class);
            startActivity(intent);
        } else {
            Toast.makeText(AdmCinemaUpdate.this, "失败", Toast.LENGTH_LONG).show();
        }
    }

    private void updateData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdmCinemaUpdate.this);
        AlertDialog dialog = builder.create();
        dialog.setMessage("确认修改？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取输入的信息
                String name = adm_et_up_cinema.getText().toString().trim();
                String ad = adm_et_up_ad.getText().toString().trim();
                //打开数据库，添加到数据库
                DBHelper dbHelper = new DBHelper(AdmCinemaUpdate.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储要插入的数据
                ContentValues values = new ContentValues();
                values.put(DBConfig.Cinema.CINEMA_NAME, name);
                values.put(DBConfig.Cinema.CINEMA_ADD, ad);
                //修改数据
                int row = db.update(DBConfig.Cinema.CINEMA_TABLE, values, DBConfig.Cinema._ID + "=?", new String[]{bean.getId() + ""});
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
