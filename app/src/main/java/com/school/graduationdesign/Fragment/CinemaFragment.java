package com.school.graduationdesign.Fragment;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.school.graduationdesign.Activity.DianYing;
import com.school.graduationdesign.Activity.YingYuan;
import com.school.graduationdesign.Adapter.CinemaAdapter;
import com.school.graduationdesign.Administration.AdmCinemaUpdate;
import com.school.graduationdesign.Administration.Bean.AddCinemaBean;
import com.school.graduationdesign.Bean.CinemaBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/9
 */
public class CinemaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private List<CinemaBean> list = new ArrayList();
    private ListView cinema_list;
    private DBHelper helper;
    private SQLiteDatabase db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取Activity
        Activity activity = getActivity();
        //加载布局
        View view = inflater.inflate(R.layout.activity_cinema, null);
        cinema_list = (ListView) view.findViewById(R.id.cinema_list);
        //打开数据库
        openDB();
        //查询数据库，得到联系人列表
        getContacts();
        setData();
        return view;
    }

    private void setData() {
        //创建适配器
        CinemaAdapter adapter = new CinemaAdapter(this, list);
        //设置适配器
        cinema_list.setAdapter(adapter);
        cinema_list.setOnItemClickListener(this);
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(getActivity());
        //创建或打开数据库
        db = helper.getWritableDatabase();
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
            CinemaBean bean = new CinemaBean();
            bean.setCinemaname_list(name);
            bean.setCinemaadd_list(add);
            //添加联系人到集合
            list.add(bean);
            Log.e("AdmCinema", name);
        }
        //关闭资源
        cursor.close();
        db.close();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CinemaBean bean = list.get(position);
        Intent intent = new Intent(getActivity(), YingYuan.class);
        intent.putExtra("bean", bean);
        startActivity(intent);
    }

}
