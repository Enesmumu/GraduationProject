package com.school.graduationdesign.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.school.graduationdesign.Adapter.DianYingCinemaTimeAdapter;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.DianYingCinemaTimeBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/21
 */
public class DianYingCinemaTime extends BaseActivity implements View.OnClickListener {
    private List<DianYingCinemaTimeBean> list = new ArrayList();
    private ListView dianying_cinematime_list;
    private ImageView dianying_cinematime_fanhui;
    private TextView dianying_movie_time_name, dianying_movie_time_des;
    private Intent intent;
    private String name;
    private String des;
    private DBHelper helper;
    private SQLiteDatabase db;
    private DianYingCinemaTimeBean bean;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_dianyingcinematime);
        intent = getIntent();
        name = intent.getStringExtra("name");
        des = intent.getStringExtra("des");
        openDB();
        //查询数据库，得到列表
        getContacts();

    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(this);
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    @Override
    protected void findView() {
        dianying_cinematime_list = (ListView) findViewById(R.id.dianying_cinematime_list);
        dianying_cinematime_fanhui = (ImageView) findViewById(R.id.dianying_cinematime_fanhui);
        dianying_movie_time_name = (TextView) findViewById(R.id.dianying_movie_time_name);
        dianying_movie_time_des = (TextView) findViewById(R.id.dianying_movie_time_des);
    }

    @Override
    protected void getData() {
        dianying_movie_time_name.setText(name);
        dianying_movie_time_des.setText(des);
    }

    @Override
    protected void setData() {
        DianYingCinemaTimeAdapter adapter = new DianYingCinemaTimeAdapter(this, list);
        dianying_cinematime_list.setAdapter(adapter);
    }

    @Override
    protected void setClick() {
        dianying_cinematime_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dianying_cinematime_fanhui:
                finish();
                break;
        }
    }

    public void getContacts() {
        list.clear();
        //查询数据库
        //Cursor cursor=db.query(DBConfig.CinemaTime.CINEMATIME_TABLE,null,null,null,null,null,DBConfig.CinemaTime.CINEMATIME_NAME+" asc");
        Cursor cursor = db.query(DBConfig.CinemaTime.CINEMATIME_TABLE, null, "cinematime_name = ?", new String[]{name}, null, null, DBConfig.CinemaTime.CINEMATIME_TIME + " asc");
        //Cursor cursor = db.query(DBConfig.CinemaTime.CINEMATIME_TABLE,new String[]{DBConfig.CinemaTime.CINEMATIME_NAME},"cinematime_name = ?",new String[]{name}, null, null,DBConfig.CinemaTime.CINEMATIME_NAME+" desc");
        //db.rawQuery("SELECT * FROM CINEMATINE_TABLE ORDER BY CINEMATIME_TIME",null);
        //遍历数值
        while (cursor.moveToNext()) {
            //根据列名获得对应角标
            int index_name = cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_NAME);
            //获取列对应的值
            String name = cursor.getString(index_name);
            String time = cursor.getString(cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_TIME));
            String price = cursor.getString(cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_PRICE));
            String num = cursor.getString(cursor.getColumnIndex(DBConfig.CinemaTime.CINEMATIME_NUM));
            //创建联系人对象，并赋值
            DianYingCinemaTimeBean bean = new DianYingCinemaTimeBean();
            bean.setDianying_cinematimelist_name(name);
            bean.setDianying_cinematimelist_time(time);
            bean.setDianying_cinematimelist_price(price);
            bean.setDianying_cinematimelist_num(num);
            //添加联系人到集合
            list.add(bean);
        }
        //关闭资源
        cursor.close();
        db.close();
    }
}
