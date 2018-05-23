package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
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
 * @describe 添加影院界面
 * @date 2016/4/20
 */
public class AdmCinemaAdd extends Activity implements View.OnClickListener {
    private Button adm_cinema_save;
    private ImageView adm_ad_fanhui,adm_iv_cinema_pic ;
    private EditText adm_et_cinema, adm_et_ad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_cinema_add);
        //查找控件
        adm_ad_fanhui = (ImageView) findViewById(R.id.adm_ad_fanhui);
        adm_cinema_save = (Button) findViewById(R.id.adm_cinema_save);
        adm_et_cinema = (EditText) findViewById(R.id.adm_et_cinema);
        adm_et_ad = (EditText) findViewById(R.id.adm_et_ad);
        //设置监听
        adm_cinema_save.setOnClickListener(this);
        adm_ad_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_ad_fanhui:
                finish();
                break;
            case R.id.adm_cinema_save:
                //获取输入的信息
                String name = adm_et_cinema.getText().toString().trim();
                String ad = adm_et_ad.getText().toString().trim();
                //打开数据库，添加到数据库
                DBHelper dbHelper = new DBHelper(AdmCinemaAdd.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储要插入的数据
                ContentValues values = new ContentValues();
                values.put(DBConfig.Cinema.CINEMA_NAME, name);
                values.put(DBConfig.Cinema.CINEMA_ADD, ad);
                //插入数据
                long id = db.insert(DBConfig.Cinema.CINEMA_TABLE, null, values);
                if (id != -1) {
                    Toast.makeText(AdmCinemaAdd.this, "成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdmCinemaAdd.this, AdmCinema.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdmCinemaAdd.this, "失败", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
