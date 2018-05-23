package com.school.graduationdesign.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.ChooseBean;
import com.school.graduationdesign.R;

import java.util.Iterator;
import java.util.Set;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 确认订单界面
 * @date 2016/4/13
 */
public class Confirm extends BaseActivity {
    private ImageView confirm_fanhui;
    private Button pay;
    private TextView cf_name, cf_time, cf_seat, cf_money, cf_num;
    private Intent intent;
    private String name;
    private String time;
    private String seat;
    private String num;
    private String money;
    private ChooseBean chooseBean;
    private SharedPreferences sp;
    private SharedPreferences sharedPreferences;
    private Set set;
    private Iterator it;
    SharedPreferences.Editor editor;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_confirm);
        intent = getIntent();
        name = intent.getStringExtra("name");
        time = intent.getStringExtra("time");
        num = intent.getStringExtra("num");
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("seat", Context.MODE_PRIVATE);
        money = sp.getString("money", "");
        seat = sharedPreferences.getString("seat", "");

        //sharedPreferences = getSharedPreferences("money", 0);
//        Map map = sharedPreferences.getAll();
//        set = map.entrySet();
//        set = map.keySet();
//        it = set.iterator();
//        num = sharedPreferences.getString("Seat", "");
//        Log.e("object", num);
    }

    @Override
    protected void findView() {
        confirm_fanhui = (ImageView) findViewById(R.id.confirm_fanhui);
        pay = (Button) findViewById(R.id.pay);
        cf_name = (TextView) findViewById(R.id.cf_name);
        cf_time = (TextView) findViewById(R.id.cf_time);
        cf_seat = (TextView) findViewById(R.id.cf_seat);
        cf_money = (TextView) findViewById(R.id.cf_money);
        cf_num = (TextView) findViewById(R.id.cf_num);
    }

    @Override
    protected void getData() {
        cf_name.setText(name);
        cf_time.setText(time);
        cf_seat.setText(seat);
        cf_num.setText(num);
        cf_money.setText(money);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        pay.setOnClickListener(this);
        confirm_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay:
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(Confirm.this);
                AlertDialog dialog1 = builder1.create();
                dialog1.setMessage("确认支付订单？"+money);
                dialog1.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().clear().commit();
                        sp.edit().clear();
                        sp.edit().commit();
                        finish();
                    }
                });
                dialog1.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog1.show();
                break;
            case R.id.confirm_fanhui:
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(Confirm.this);
                AlertDialog dialog2 = builder2.create();
                dialog2.setMessage("确认取消订单？");
                dialog2.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent();
                        intent1.putExtra("clear", "clear");
                        intent1.setAction("delete");
                        sendBroadcast(intent1);
                        sharedPreferences.edit().clear().commit();
                        sp.edit().clear().commit();
                        finish();
                    }
                });
                dialog2.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog2.show();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Confirm.this);
            AlertDialog dialog = builder.create();
            dialog.setMessage("确认取消订单？");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sp.edit().clear();
                    sp.edit().commit();
                    finish();
                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
        return super.onKeyDown(keyCode, event);
    }
}
