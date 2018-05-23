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

import com.school.graduationdesign.Administration.Adapter.AddCinemaAdapter;
import com.school.graduationdesign.Administration.Bean.AddCinemaBean;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 添加影院管理界面
 * @date 2016/4/20
 */
public class AdmCinema extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button adm_add_cinema;
    private ImageView adm_fanhui;
    private List<AddCinemaBean> list = new ArrayList<>();
    private DBHelper helper;
    private SQLiteDatabase db;
    private ListView adm_cinema_list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_cinema);
        //查找控件
        adm_fanhui = (ImageView) findViewById(R.id.adm_fanhui);
        adm_cinema_list = (ListView) findViewById(R.id.adm_cinema_list);
        adm_add_cinema = (Button) findViewById(R.id.adm_add_cinema);
        adm_add_cinema.setOnClickListener(this);
        adm_fanhui.setOnClickListener(this);
        openDB();
        getContacts();
        setData();
    }

    private void setData() {
        //创建适配器
        AddCinemaAdapter adapter = new AddCinemaAdapter(this, list);
        //设置适配器
        adm_cinema_list.setAdapter(adapter);
        adm_cinema_list.setOnItemClickListener(this);
    }

    public void getContacts() {
        list.clear();
        //查询数据库
        Cursor cursor = db.query(DBConfig.Cinema.CINEMA_TABLE, null, null, null, null, null, null);
        //遍历数值
        while (cursor.moveToNext()) {
            //根据列名获得对应角标
            int index_name = cursor.getColumnIndex(DBConfig.Cinema.CINEMA_NAME);
            //获取列对应的值
            String name = cursor.getString(index_name);
            String add = cursor.getString(cursor.getColumnIndex(DBConfig.Cinema.CINEMA_ADD));
            int id = cursor.getInt(cursor.getColumnIndex(DBConfig.Cinema._ID));
            //创建联系人对象，并赋值
            AddCinemaBean bean = new AddCinemaBean();
            bean.setAdm_cinema_list_name(name);
            bean.setAdd_cinema_list_ad(add);
            bean.setId(id);
            //添加联系人到集合
            list.add(bean);
            Log.e("AdmCinema", name);
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
            case R.id.adm_fanhui:
                finish();
                break;
            case R.id.adm_add_cinema:
                Intent intent = new Intent(AdmCinema.this, AdmCinemaAdd.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AddCinemaBean bean = list.get(position);
        Intent intent = new Intent(AdmCinema.this, AdmCinemaUpdate.class);
        intent.putExtra("bean", bean);
        startActivity(intent);
        finish();
    }
}
