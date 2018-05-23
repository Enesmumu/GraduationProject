package com.school.graduationdesign.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.school.graduationdesign.Adapter.HuaDongAdapter;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Fragment.CinemaFragment;
import com.school.graduationdesign.Fragment.GiftFragment;
import com.school.graduationdesign.Fragment.MovieFragment;
import com.school.graduationdesign.Fragment.UserFragment;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 滑动界面
 * @date 2016/4/9
 */
public class HuaDong extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager huadong_view;
    private ImageView btn_yingyuan, btn_dianying, btn_liwu, btn_geren;
    private List<Fragment> fragments = new ArrayList<>();
    private Intent intent;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_huadong);
    }

    @Override
    protected void findView() {
        huadong_view = (ViewPager) findViewById(R.id.huadong_view);
        btn_yingyuan = (ImageView) findViewById(R.id.btn_yingyuan);
        btn_dianying = (ImageView) findViewById(R.id.btn_dianying);
        btn_liwu = (ImageView) findViewById(R.id.btn_liwu);
        btn_geren = (ImageView) findViewById(R.id.btn_geren);
    }

    @Override
    protected void getData() {
        intent=getIntent();
        String num=intent.getStringExtra("user");
        //添加到集合
        fragments.add(new CinemaFragment());
        fragments.add(new MovieFragment());
        fragments.add(new GiftFragment());
        fragments.add(new UserFragment());
        //创建适配器
        HuaDongAdapter adapter = new HuaDongAdapter(getSupportFragmentManager(), fragments);
        huadong_view.setAdapter(adapter);
        huadong_view.setCurrentItem(3);
        huadong_view.setOnPageChangeListener(this);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        btn_geren.setOnClickListener(this);
        btn_yingyuan.setOnClickListener(this);
        btn_dianying.setOnClickListener(this);
        btn_liwu.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HuaDong.this);
            AlertDialog dialog = builder.create();
            dialog.setMessage("确认退出登录？");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    android.os.Process.killProcess(android.os.Process.myPid());
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

    @Override
    public void onClick(View v) {
        changeImageBackground();
        switch (v.getId()) {
            case R.id.btn_yingyuan:
                huadong_view.setCurrentItem(0);
                btn_yingyuan.setBackgroundResource(R.mipmap.yingyuan2);
                break;
            case R.id.btn_dianying:
                huadong_view.setCurrentItem(1);
                btn_dianying.setBackgroundResource(R.mipmap.dianying2);
                break;
            case R.id.btn_liwu:
                huadong_view.setCurrentItem(2);
                btn_liwu.setBackgroundResource(R.mipmap.liwu2);
                break;
            case R.id.btn_geren:
                huadong_view.setCurrentItem(3);
                btn_geren.setBackgroundResource(R.mipmap.geren2);
                break;
        }
    }

    private void changeImageBackground() {
        btn_geren.setBackgroundResource(R.mipmap.geren1);
        btn_yingyuan.setBackgroundResource(R.mipmap.yianyuan1);
        btn_dianying.setBackgroundResource(R.mipmap.dianying1);
        btn_liwu.setBackgroundResource(R.mipmap.liwu1);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeImageBackground();
        if (position == 0) {
            huadong_view.setCurrentItem(0);
            btn_yingyuan.setBackgroundResource(R.mipmap.yingyuan2);
        } else if (position == 1) {
            huadong_view.setCurrentItem(1);
            btn_dianying.setBackgroundResource(R.mipmap.dianying2);
        } else if (position == 2) {
            huadong_view.setCurrentItem(2);
            btn_liwu.setBackgroundResource(R.mipmap.liwu2);
        } else {
            huadong_view.setCurrentItem(3);
            btn_geren.setBackgroundResource(R.mipmap.geren2);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}