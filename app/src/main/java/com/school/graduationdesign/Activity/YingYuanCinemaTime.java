package com.school.graduationdesign.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.school.graduationdesign.Adapter.YingYuanCinemaTimeAdapter;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.YingYuanBean;
import com.school.graduationdesign.Bean.YingYuanCinemaTimeBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 选择电影场次界面
 * @date 2016/4/19
 */
public class YingYuanCinemaTime extends BaseActivity implements View.OnClickListener {
    private List<YingYuanCinemaTimeBean> list = new ArrayList();
    private ListView yingyuan_cinematime_list;
    private ImageView yingyuan_cinematime_fanhui;
    private TextView yingyuan_movie_time_name, yingyuan_movie_time_des;
    private YingYuanBean yingYuanBean;
    private DBHelper helper;
    private SQLiteDatabase db;
    private String cinemaname;
    private String cinematime;
    private YingYuanCinemaTimeBean bean;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_yingyuancinematime);
        openDB();
        yingYuanBean = (YingYuanBean) getIntent().getSerializableExtra("yingyuanbean");
        cinemaname = yingYuanBean.getDianyingname_list();
        Log.e("cinemaname", cinemaname);
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
        yingyuan_cinematime_list = (ListView) findViewById(R.id.yingyuan_cinematime_list);
        yingyuan_cinematime_fanhui = (ImageView) findViewById(R.id.yingyuan_cinematime_fanhui);
        yingyuan_movie_time_name = (TextView) findViewById(R.id.yingyuan_movie_time_name);
        yingyuan_movie_time_des = (TextView) findViewById(R.id.yingyuan_movie_time_des);
    }

    @Override
    protected void getData() {
        yingyuan_movie_time_name.setText(yingYuanBean.getDianyingname_list());
        yingyuan_movie_time_des.setText(yingYuanBean.getDianyingdes_list());
    }

    @Override
    protected void setData() {
        YingYuanCinemaTimeAdapter adapter = new YingYuanCinemaTimeAdapter(this, list);
        yingyuan_cinematime_list.setAdapter(adapter);

    }


    @Override
    protected void setClick() {
        yingyuan_cinematime_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void getContacts() {
        list.clear();
        //查询数据库
        //Cursor cursor=db.query(DBConfig.CinemaTime.CINEMATIME_TABLE,null,null,null,null,null,DBConfig.CinemaTime.CINEMATIME_NAME+" asc");
        Cursor cursor = db.query(DBConfig.CinemaTime.CINEMATIME_TABLE, null, "cinematime_name = ?", new String[]{cinemaname}, null, null, DBConfig.CinemaTime.CINEMATIME_TIME + " asc");
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
            YingYuanCinemaTimeBean bean = new YingYuanCinemaTimeBean();
            bean.setYingyuan_cinematimelist_name(name);
            bean.setYingyuan_cinematimelist_time(time);
            bean.setYingyuan_cinematimelist_price(price);
            bean.setYingyuan_cinematimelist_num(num);
            //添加联系人到集合
            list.add(bean);
        }
        //关闭资源
        cursor.close();
        db.close();
    }

}
