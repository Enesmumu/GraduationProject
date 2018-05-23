package com.school.graduationdesign.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.school.graduationdesign.Adapter.DianYingAdapter;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.DianYingBean;
import com.school.graduationdesign.Bean.MovieBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 影院界面下的电影界面
 * @date 2016/4/6
 */
public class DianYing extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private List<DianYingBean> list = new ArrayList();
    private TextView movie_name, movie_des;
    private ListView yingyuan_list;
    private ImageView dianying_fanhui;
    private DBHelper helper;
    private SQLiteDatabase db;
    private MovieBean bean;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_dianying);
        //打开数据库
        openDB();
        //查询数据库，得到列表
        getContacts();
    }

    private void getContacts() {
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
            //创建对象，并赋值
            DianYingBean bean = new DianYingBean();
            bean.setYingyuanname_list(name);
            bean.setYingyuanadd_list(add);
            //添加到集合
            list.add(bean);
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
    protected void findView() {
        yingyuan_list = (ListView) findViewById(R.id.yingyuan_list);
        dianying_fanhui = (ImageView) findViewById(R.id.dianying_fanhui);
        movie_name = (TextView) findViewById(R.id.movie_name);
        movie_des = (TextView) findViewById(R.id.movie_des);
    }

    @Override
    protected void getData() {
        //获取数据
        bean = (MovieBean) getIntent().getSerializableExtra("bean");
        movie_name.setText(bean.getMoviename_list());
        movie_des.setText(bean.getMoviedes_list());
    }

    @Override
    protected void setData() {
        //创建适配器
        DianYingAdapter adapter = new DianYingAdapter(this, list);
        //设置适配器
        yingyuan_list.setAdapter(adapter);
        yingyuan_list.setOnItemClickListener(this);
    }

    @Override
    protected void setClick() {
        dianying_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(DianYing.this, DianYingCinemaTime.class);
        String name = bean.getMoviename_list();
        String des = bean.getMoviedes_list();
        intent.putExtra("name", name);
        intent.putExtra("des", des);
        startActivity(intent);
    }
}
