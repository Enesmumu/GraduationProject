package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.school.graduationdesign.Administration.Adapter.UserAdapter;
import com.school.graduationdesign.Administration.Bean.AddCinemaBean;
import com.school.graduationdesign.Administration.Bean.UserBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/20
 */
public class AdmUser extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private DBHelper helper;
    private SQLiteDatabase db;
    private ListView adm_user_list;
    private ImageView adm_user_fanhui;
    private List<UserBean> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_user);
        //查找控件
        adm_user_list = (ListView) findViewById(R.id.adm_user_list);
        adm_user_fanhui = (ImageView) findViewById(R.id.adm_user_fanhui);
        adm_user_fanhui.setOnClickListener(this);
        openDB();
        getContacts();
        setData();
    }

    private void setData() {
        //创建适配器
        UserAdapter adapter = new UserAdapter(this, list);
        //设置适配器
        adm_user_list.setAdapter(adapter);
        adm_user_list.setOnItemClickListener(this);
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(this);
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    private void getContacts() {
        list.clear();
        //查询数据库
        Cursor cursor = db.query(DBConfig.User.USER_TABLE, null, null, null, null, null, null);
        //遍历数值
        while (cursor.moveToNext()) {
            //根据列名获得对应角标
            int index_num = cursor.getColumnIndex(DBConfig.User.USER_NUM);
            //获取列对应的值
            String num = cursor.getString(index_num);
            String password = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_PASSWORD));
            int id = cursor.getInt(cursor.getColumnIndex(DBConfig.User._ID));
            //创建联系人对象，并赋值
            UserBean bean = new UserBean();
            bean.setAdm_user_list_num(num);
            bean.setAdm_user_list_password(password);
            bean.setId(id);
            //添加联系人到集合
            list.add(bean);
        }
        //关闭资源
        cursor.close();
        db.close();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_user_fanhui:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserBean bean = list.get(position);
        Intent intent = new Intent(AdmUser.this, AdmUserUpdate.class);
        intent.putExtra("bean", bean);
        startActivity(intent);
        finish();
    }
}
