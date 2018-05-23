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
 * @describe 添加电影界面
 * @date 2016/4/21
 */
public class AdmMovieAdd extends Activity implements View.OnClickListener {
    private EditText adm_et_movie_name, adm_et_movie_des;
    private Button adm_movie_save;
    private ImageView adm_ad_movie_fanhui, adm_iv_movie_pic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_movie_add);
        //查找控件
        adm_et_movie_name = (EditText) findViewById(R.id.adm_et_movie_name);
        adm_et_movie_des = (EditText) findViewById(R.id.adm_et_movie_des);
        adm_movie_save = (Button) findViewById(R.id.adm_movie_save);
        adm_ad_movie_fanhui = (ImageView) findViewById(R.id.adm_ad_movie_fanhui);
        //设置监听
        adm_movie_save.setOnClickListener(this);
        adm_ad_movie_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_ad_movie_fanhui:
                finish();
                break;
            case R.id.adm_movie_save:
                //获取输入的信息
                String name = adm_et_movie_name.getText().toString().trim();
                String des = adm_et_movie_des.getText().toString().trim();
                //打开数据库，添加到数据库
                DBHelper dbHelper = new DBHelper(AdmMovieAdd.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储要插入的数据
                ContentValues values = new ContentValues();
                values.put(DBConfig.Movie.MOVIE_NAME, name);
                values.put(DBConfig.Movie.MOVIE_DES, des);
                //插入数据
                long id = db.insert(DBConfig.Movie.MOVIE_TABLE, null, values);
                if (id != -1) {
                    Toast.makeText(AdmMovieAdd.this, "成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdmMovieAdd.this, AdmMovie.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdmMovieAdd.this, "失败", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
