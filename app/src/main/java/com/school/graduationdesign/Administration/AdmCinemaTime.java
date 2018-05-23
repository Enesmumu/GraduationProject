package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.school.graduationdesign.Administration.Adapter.CinemaTimeAdapter;
import com.school.graduationdesign.Administration.Bean.CinemaTimeBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 影院电影场次安排界面
 * @date 2016/5/3
 */
public class AdmCinemaTime extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button adm_add_cinematime;
    private ImageView adm_cinematime_fanhui;
    private ListView adm_cinematime_list;
    private List<CinemaTimeBean> list = new ArrayList();
    private DBHelper helper;
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_cinematime);
        //查找控件
        adm_add_cinematime = (Button) findViewById(R.id.adm_add_cinematime);
        adm_cinematime_fanhui = (ImageView) findViewById(R.id.adm_cinematime_fanhui);
        adm_cinematime_list = (ListView) findViewById(R.id.adm_cinematime_list);
        //设置监听
        adm_add_cinematime.setOnClickListener(this);
        adm_cinematime_fanhui.setOnClickListener(this);
        openDB();
        getContacts();
        setData();
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(this);
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    private void setData() {
        //创建适配器
        CinemaTimeAdapter adapter = new CinemaTimeAdapter(this, list);
        //设置适配器
        adm_cinematime_list.setAdapter(adapter);
        adm_cinematime_list.setOnItemClickListener(this);
    }

    public void getContacts() {
        list.clear();
        //查询数据库
        Cursor cursor = db.query(DBConfig.CinemaTime.CINEMATIME_TABLE, null, null, null, null, null, null);
        //遍历数值
        while (cursor.moveToNext()) {
            //根据列名获得对应角标
            int index_name = cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_NAME);
            //获取列对应的值
            String name = cursor.getString(index_name);
            String time = cursor.getString(cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_TIME));
            String price = cursor.getString(cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_PRICE));
            String num = cursor.getString(cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_NUM));
            int id = cursor.getInt(cursor.getColumnIndex(DBConfig.CinemaTime._ID));
            //创建联系人对象，并赋值
            CinemaTimeBean bean = new CinemaTimeBean();
            bean.setAdm_cinematime_list_name(name);
            bean.setAdm_cinematime_list_time(time);
            bean.setAdm_cinematime_list_price(price);
            bean.setAdm_cinematime_list_num(num);
            bean.setId(id);
            //添加联系人到集合
            list.add(bean);
            Log.e("CinemaTime", name);
        }
        //关闭资源
        cursor.close();
        db.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_cinematime_fanhui:
                finish();
                break;
            case R.id.adm_add_cinematime:
                Intent intent = new Intent(AdmCinemaTime.this, AdmCinemaTimeAdd.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CinemaTimeBean bean = list.get(position);
        Intent intent = new Intent(AdmCinemaTime.this, AdmCinemaTimeUpdate.class);
        intent.putExtra("bean", bean);
        startActivity(intent);
        finish();
    }
}
