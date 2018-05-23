package com.school.graduationdesign.Administration;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.school.graduationdesign.Administration.Adapter.SeatAdapter;
import com.school.graduationdesign.Administration.Bean.SeatBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 座位信息表
 * @date 2016/5/5
 */
public class AdmSeat extends Activity implements View.OnClickListener {
    private ImageView adm_seat_fanhui, adm_del_seat;
    private Button adm_add_seat;
    private ListView adm_seat_list;
    private DBHelper helper;
    private SQLiteDatabase db;
    private List<SeatBean> list = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_adm_seat);
        //查找控件
        adm_seat_fanhui = (ImageView) findViewById(R.id.adm_seat_fanhui);
        adm_add_seat = (Button) findViewById(R.id.adm_add_seat);
        adm_seat_list = (ListView) findViewById(R.id.adm_seat_list);
        adm_del_seat = (ImageView) findViewById(R.id.adm_del_seat);
        //设置监听事件
        adm_seat_fanhui.setOnClickListener(this);
        adm_add_seat.setOnClickListener(this);
        adm_del_seat.setOnClickListener(this);
        openDB();
        getContacts();
        setData();
    }

    private void setData() {
        SeatAdapter adapter = new SeatAdapter(this, list);
        adm_seat_list.setAdapter(adapter);
    }

    private void getContacts() {
        list.clear();
        //查询数据库
        Cursor cursor = db.query(DBConfig.Seat.SEAT_TABLE, null, null, null, null, null, null);
        //遍历数值
        while (cursor.moveToNext()) {
            //根据列名获得对应角标
            int index_name = cursor.getColumnIndex(DBConfig.Seat.SEAT_STATUS);
            //获取列对应的值
            String status = cursor.getString(index_name);
            String num = cursor.getString(cursor.getColumnIndex(DBConfig.Seat.SEAT_NUM));
            int id = cursor.getInt(cursor.getColumnIndex(DBConfig.Seat._ID));
            //创建联系人对象，并赋值
            SeatBean bean = new SeatBean();
            bean.setAdm_seat_list_status(status);
            bean.setAdm_seat_list_num(num);
            bean.setId(id);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_seat_fanhui:
                finish();
                break;
            case R.id.adm_del_seat:
                openDB();
                db.delete(DBConfig.Seat.SEAT_TABLE, null, null);
                break;
            case R.id.adm_add_seat:
                AddSeat();
                break;
        }
    }

    private void AddSeat() {
        //打开数据库
        DBHelper dbHelper = new DBHelper(AdmSeat.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String status[] = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
        String num[] = {"1排1座", "1排2座", "1排3座", "1排4座", "1排5座", "1排6座", "2排1座", "2排2座", "2排3座", "2排4座", "2排5座", "2排6座", "3排1座", "3排2座", "3排3座", "3排4座", "3排5座", "3排6座", "4排1座", "4排2座", "4排3座", "4排4座", "4排5座", "4排6座", "5排1座", "5排2座", "5排3座", "5排4座", "5排5座", "5排6座", "6排1座", "6排2座", "6排3座", "6排4座", "6排5座", "6排6座"};

        for (int i = 0; i < status.length; i++) {
            String st = status[i];
            values.put(DBConfig.Seat.SEAT_STATUS, String.valueOf(st));
            String s = num[i];
            values.put(DBConfig.Seat.SEAT_NUM, String.valueOf(s));
          /*  for(int j=0;j<num.length;j++) {
                //long id = db.insert(DBConfig.Seat.SEAT_TABLE, null, values);
                db.insert(DBConfig.Seat.SEAT_TABLE, null, values);
               // isSuccess(id);
            }*/
            db.insert(DBConfig.Seat.SEAT_TABLE, null, values);
        }


    }

    private void isSuccess(long id) {
        if (id != -1) {
            Toast.makeText(AdmSeat.this, "成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AdmSeat.this, "失败", Toast.LENGTH_SHORT).show();
        }
    }
}
