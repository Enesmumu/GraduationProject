package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/3
 */
public class AdmCinemaTimeAdd extends Activity implements View.OnClickListener {
    private EditText adm_et_cinematime_name, adm_et_cinematime_time, adm_et_cinematime_price, adm_et_cinematime_num;
    private ImageView adm_ad_cinematime_fanhui;
    private Button adm_cinematime_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_cinematime_add);
        //查找控件
        adm_et_cinematime_name = (EditText) findViewById(R.id.adm_et_cinematime_name);
        adm_et_cinematime_time = (EditText) findViewById(R.id.adm_et_cinematime_time);
        adm_et_cinematime_price = (EditText) findViewById(R.id.adm_et_cinematime_price);
        adm_et_cinematime_num = (EditText) findViewById(R.id.adm_et_cinematime_num);
        adm_ad_cinematime_fanhui = (ImageView) findViewById(R.id.adm_ad_cinematime_fanhui);
        adm_cinematime_save = (Button) findViewById(R.id.adm_cinematime_save);
        //设置监听
        adm_ad_cinematime_fanhui.setOnClickListener(this);
        adm_cinematime_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_ad_cinematime_fanhui:
                finish();
                break;
            case R.id.adm_cinematime_save:
                //获取输入的信息
                String name = adm_et_cinematime_name.getText().toString().trim();
                String time = adm_et_cinematime_time.getText().toString().trim();
                String price = adm_et_cinematime_price.getText().toString().trim();
                String num = adm_et_cinematime_num.getText().toString().trim();
                //打开数据库，添加到数据库
                DBHelper dbHelper = new DBHelper(AdmCinemaTimeAdd.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储要插入的数据
                ContentValues values = new ContentValues();
                values.put(DBConfig.CinemaTime.CINEMATIME_NAME, name);
                values.put(DBConfig.CinemaTime.CINEMATIME_TIME, time);
                values.put(DBConfig.CinemaTime.CINEMATIME_PRICE, price);
                values.put(DBConfig.CinemaTime.CINEMATIME_NUM, num);
                //插入数据
                long id = db.insert(DBConfig.CinemaTime.CINEMATIME_TABLE, null, values);
                if (id != -1) {
                    Toast.makeText(AdmCinemaTimeAdd.this, "成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdmCinemaTimeAdd.this, AdmCinemaTime.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdmCinemaTimeAdd.this, "失败", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
