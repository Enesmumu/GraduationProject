package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.school.graduationdesign.Administration.Adapter.AddMovieAdapter;
import com.school.graduationdesign.Administration.Bean.AddCinemaBean;
import com.school.graduationdesign.Administration.Bean.AddMovieBean;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 添加电影管理界面
 * @date 2016/4/20
 */
public class AdmMovie extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button adm_add_movie;
    private ImageView adm_movie_fanhui;
    private List<AddMovieBean> list = new ArrayList<>();
    private DBHelper helper;
    private SQLiteDatabase db;
    private ListView adm_movie_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_movie);
        //查找控件
        adm_movie_fanhui = (ImageView) findViewById(R.id.adm_movie_fanhui);
        adm_add_movie = (Button) findViewById(R.id.adm_add_movie);
        adm_movie_list = (ListView) findViewById(R.id.adm_movie_list);
        //设置监听事件
        adm_movie_fanhui.setOnClickListener(this);
        adm_add_movie.setOnClickListener(this);
        openDB();
        getContacts();
        setData();
    }

    private void setData() {
        AddMovieAdapter adapter = new AddMovieAdapter(this, list);
        adm_movie_list.setAdapter(adapter);
        adm_movie_list.setOnItemClickListener(this);
    }

    public void getContacts() {
        list.clear();
        //查询数据库
        Cursor cursor = db.query(DBConfig.Movie.MOVIE_TABLE, null, null, null, null, null, null);
        //遍历数值
        while (cursor.moveToNext()) {
            //根据列名获得对应角标
            int index_name = cursor.getColumnIndex(DBConfig.Movie.MOVIE_NAME);
            //获取列对应的值
            String name = cursor.getString(index_name);
            String des = cursor.getString(cursor.getColumnIndex(DBConfig.Movie.MOVIE_DES));
            int id = cursor.getInt(cursor.getColumnIndex(DBConfig.Cinema._ID));
            //创建联系人对象，并赋值
            AddMovieBean bean = new AddMovieBean();
            bean.setAdm_movie_list_name(name);
            bean.setAdm_movie_list_des(des);
            bean.setId(id);
            //添加联系人到集合
            list.add(bean);
            Log.e("AdmMovie", name);
        }
        //关闭资源
        cursor.close();
        db.close();
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(this);
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_movie_fanhui:
                finish();
                break;
            case R.id.adm_add_movie:
                Intent intent = new Intent(AdmMovie.this, AdmMovieAdd.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AddMovieBean bean = list.get(position);
        Intent intent = new Intent(AdmMovie.this, AdmMovieUpdate.class);
        intent.putExtra("bean", bean);
        startActivity(intent);
        finish();
    }
}
