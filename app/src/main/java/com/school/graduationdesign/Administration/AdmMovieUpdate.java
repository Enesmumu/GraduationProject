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

import com.school.graduationdesign.Administration.Bean.AddMovieBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 电影信息删改界面
 * @date 2016/4/22
 */
public class AdmMovieUpdate extends Activity implements View.OnClickListener {
    private Button adm_movie_update, adm_movie_del;
    private ImageView adm_iv_movie_pic, adm_up_movie_fanhui;
    private EditText adm_et_up_movie, adm_et_up_des;
    private AddMovieBean bean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_movie_update);
        //查找控件
        adm_up_movie_fanhui = (ImageView) findViewById(R.id.adm_up_movie_fanhui);
        adm_movie_update = (Button) findViewById(R.id.adm_movie_update);
        adm_movie_del = (Button) findViewById(R.id.adm_movie_del);
        adm_et_up_movie = (EditText) findViewById(R.id.adm_et_up_movie);
        adm_et_up_des = (EditText) findViewById(R.id.adm_et_up_des);
        //设置监听
        adm_movie_del.setOnClickListener(this);
        adm_movie_update.setOnClickListener(this);
        adm_up_movie_fanhui.setOnClickListener(this);
        //获取数据
        bean = (AddMovieBean) getIntent().getSerializableExtra("bean");
        adm_et_up_movie.setText(bean.getAdm_movie_list_name());
        adm_et_up_des.setText(bean.getAdm_movie_list_des());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_up_movie_fanhui:
                finish();
                break;
            case R.id.adm_movie_update:
                updateData();
                break;
            case R.id.adm_movie_del:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdmMovieUpdate.this);
                AlertDialog dialog = builder.create();
                dialog.setMessage("确认删除？");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //打开数据库
                        DBHelper dbHelper = new DBHelper(AdmMovieUpdate.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //删除数据库
                        int row = db.delete(DBConfig.Movie.MOVIE_TABLE, DBConfig.Cinema._ID + "=?", new String[]{bean.getId() + ""});
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
            Toast.makeText(AdmMovieUpdate.this, "成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdmMovieUpdate.this, AdmMovie.class);
            startActivity(intent);
        } else {
            Toast.makeText(AdmMovieUpdate.this, "失败", Toast.LENGTH_LONG).show();
        }
    }

    private void updateData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdmMovieUpdate.this);
        AlertDialog dialog = builder.create();
        dialog.setMessage("确认修改？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取输入的信息
                String name = adm_et_up_movie.getText().toString().trim();
                String des = adm_et_up_des.getText().toString().trim();
                //打开数据库，添加到数据库
                DBHelper dbHelper = new DBHelper(AdmMovieUpdate.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储要插入的数据
                ContentValues values = new ContentValues();
                values.put(DBConfig.Movie.MOVIE_NAME, name);
                values.put(DBConfig.Movie.MOVIE_DES, des);
                //修改数据
                int row = db.update(DBConfig.Movie.MOVIE_TABLE, values, DBConfig.Movie._ID + "=?", new String[]{bean.getId() + ""});
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
