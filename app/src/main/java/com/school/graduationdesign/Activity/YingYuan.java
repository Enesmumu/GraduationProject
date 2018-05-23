package com.school.graduationdesign.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.school.graduationdesign.Adapter.YingYuanAdapter;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.CinemaBean;
import com.school.graduationdesign.Bean.YingYuanBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 电影界面下的影院界面
 * @date 2016/4/6
 */
public class YingYuan extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private List<YingYuanBean> list = new ArrayList();
    private ListView dianying_list;
    private ImageView yingyuan_fanhui;
    private TextView cinema_name, cinema_add;
    private CinemaBean cinemaBean;
    private DBHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_yingyuan);
        //打开数据库
        openDB();
        //查询数据库，得到联系人列表
        getContacts();
    }

    private void getContacts() {
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
            int id = cursor.getInt(cursor.getColumnIndex(DBConfig.Movie._ID));
            //创建联系人对象，并赋值
            YingYuanBean bean = new YingYuanBean();
            bean.setDianyingname_list(name);
            bean.setDianyingdes_list(des);
            //添加联系人到集合
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
        dianying_list = (ListView) findViewById(R.id.dianying_list);
        yingyuan_fanhui = (ImageView) findViewById(R.id.yingyuan_fanhui);
        cinema_name = (TextView) findViewById(R.id.cinema_name);
        cinema_add = (TextView) findViewById(R.id.cinema_add);
    }

    @Override
    protected void getData() {
        //获取数据
        cinemaBean = (CinemaBean) getIntent().getSerializableExtra("bean");
        cinema_name.setText(cinemaBean.getCinemaname_list());
        cinema_add.setText(cinemaBean.getCinemaadd_list());
    }

    @Override
    protected void setData() {
        //创建适配器
        YingYuanAdapter adapter = new YingYuanAdapter(this, list);
        //设置适配器
        dianying_list.setAdapter(adapter);
        dianying_list.setOnItemClickListener(this);
    }

    @Override
    protected void setClick() {
        yingyuan_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        YingYuanBean bean = list.get(position);
        Intent intent = new Intent(YingYuan.this, YingYuanCinemaTime.class);
        intent.putExtra("yingyuanbean", bean);
        startActivity(intent);
    }
}
