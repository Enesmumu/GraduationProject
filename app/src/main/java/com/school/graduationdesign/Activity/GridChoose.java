package com.school.graduationdesign.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Adapter.GridChooseAdapter;
import com.school.graduationdesign.Bean.GridChooseBean;
import com.school.graduationdesign.Bean.YingYuanCinemaTimeBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 座位选择
 * @date 2016/4/25
 */
public class GridChoose extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private List<GridChooseBean> list = new ArrayList();
    private GridView gv_grid_seat;
    private ImageView gv_choose_fanhui;
    private Button gv_seat_queren;
    private TextView gv_seat_name, gv_seat_time, gv_seat_price, gv_seat_num, gv_choose_price;
    private YingYuanCinemaTimeBean yingYuanCinemaTimeBean;
    private GridChooseBean gridChooseBean;
    private Intent intent;
    private DBHelper helper;
    private SQLiteDatabase db;
    private double choose_price;
    private double zongjia;
   // private String price;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridchoose);
//        intent=getIntent();
        yingYuanCinemaTimeBean = (YingYuanCinemaTimeBean) getIntent().getSerializableExtra("bean");

        findView();
        openDB();
        getContacts();
        getData(36);
        setData();
        setClick();
    }

    private void getContacts() {
        list.clear();
        //查询数据库
        Cursor cursor = db.query(DBConfig.Seat.SEAT_TABLE, null, null, null, null, null, null);
        //遍历数值
        while (cursor.moveToNext()) {
            //根据列名获得对应角标
            int index_status = cursor.getColumnIndex(DBConfig.Seat.SEAT_STATUS);
            String status = cursor.getString(index_status);
            String num = cursor.getString(cursor.getColumnIndex(DBConfig.Seat.SEAT_NUM));
            //创建对象并赋值
            GridChooseBean bean = new GridChooseBean();
            bean.setGv_seat(status);
            bean.setGv_num(num);
            list.add(bean);
        }
        cursor.close();
        db.close();
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(this);
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    private void findView() {
        gv_grid_seat = (GridView) findViewById(R.id.gv_grid_seat);
        gv_choose_fanhui = (ImageView) findViewById(R.id.gv_choose_fanhui);
        gv_seat_queren = (Button) findViewById(R.id.gv_seat_queren);
        gv_seat_name = (TextView) findViewById(R.id.gv_seat_name);
        gv_seat_time = (TextView) findViewById(R.id.gv_seat_time);
        gv_seat_price = (TextView) findViewById(R.id.gv_seat_price);
        gv_seat_num = (TextView) findViewById(R.id.gv_seat_num);
        gv_choose_price = (TextView) findViewById(R.id.gv_choose_price);

//        gv_seat_name.setText(intent.getStringExtra("name"));
//        gv_seat_time.setText(intent.getStringExtra("time"));

        gv_seat_name.setText(yingYuanCinemaTimeBean.getYingyuan_cinematimelist_name());
        gv_seat_time.setText("时间：" + yingYuanCinemaTimeBean.getYingyuan_cinematimelist_time());
        gv_seat_price.setText("单价：" + yingYuanCinemaTimeBean.getYingyuan_cinematimelist_price());
        gv_seat_num.setText("影厅：" + yingYuanCinemaTimeBean.getYingyuan_cinematimelist_num());
       // price = yingYuanCinemaTimeBean.getYingyuan_cinematimelist_price();
    }

    private void getData(int number) {
        for (int i = 0; i < number; i++) {

        }
    }

    private void setData() {
        zongjia = 0;
        choose_price = Double.parseDouble(yingYuanCinemaTimeBean.getYingyuan_cinematimelist_price());
        GridChooseAdapter adapter = new GridChooseAdapter(this, list, gv_choose_price, choose_price, zongjia);
        gv_grid_seat.setAdapter(adapter);

    }

    private void setClick() {
        gv_choose_fanhui.setOnClickListener(this);
        gv_seat_queren.setOnClickListener(this);
        // gv_grid_seat.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gv_seat_queren:
                final AlertDialog.Builder builder = new AlertDialog.Builder(GridChoose.this);
                AlertDialog dialog = builder.create();
                dialog.setMessage("确认座位选择？");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(GridChoose.this, Confirm.class);
                        String name = yingYuanCinemaTimeBean.getYingyuan_cinematimelist_name();
                        String time = yingYuanCinemaTimeBean.getYingyuan_cinematimelist_time();
                        String num = yingYuanCinemaTimeBean.getYingyuan_cinematimelist_num();
                        //price=gv_choose_price.toString();
                        //String num = gridChooseBean.getGv_num();
                        intent.putExtra("name", name);
                        intent.putExtra("time", time);
                        intent.putExtra("num", num);
                        //intent.putExtra("money", price);
                        //intent.putExtra("num", num);
                        startActivity(intent);
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.gv_choose_fanhui:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GridChooseBean bean = list.get(position);
        Intent intent = new Intent();
        intent.putExtra("choosebean", bean);
    }
}
