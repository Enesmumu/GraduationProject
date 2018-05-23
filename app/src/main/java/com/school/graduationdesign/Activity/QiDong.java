package com.school.graduationdesign.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.school.graduationdesign.Adapter.QidongAdapter;
import com.school.graduationdesign.Base.NewActivity;
import com.school.graduationdesign.Bean.Contants;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 启动界面
 * @date 2016/4/5
 */
public class QiDong extends NewActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager qidong_view;
    private ImageView point1, point2, point3;
    private ArrayList arrayList = new ArrayList();
    private List images;
    private Button pager_login;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private int[] pic = {R.mipmap.viewpager1, R.mipmap.viewpager2, R.mipmap.viewpager3};


    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_qidong);
    }

    @Override
    protected void findView() {
        //查找控件
        qidong_view = (ViewPager) findViewById(R.id.qidong_view);
        pager_login = (Button) findViewById(R.id.pager_login);
        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);
        images = new ArrayList<>();
        images.add(point1);
        images.add(point2);
        images.add(point3);
    }

    @Override
    protected void getData() {
        initImage();//初始化
        preferences = getSharedPreferences(Contants.CONFIG, MODE_PRIVATE);//获取对象
        editor = preferences.edit();//获取编辑器
    }

    private void initImage() {
        for (int i = 0; i < pic.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(pic[i]);
            arrayList.add(imageView);
        }

    }

    @Override
    protected void setData() {
       //创建适配器
        QidongAdapter adapter = new QidongAdapter(arrayList);
        //设置适配器
        qidong_view.setAdapter(adapter);
    }

    @Override
    protected void setClick() {
        qidong_view.setOnPageChangeListener(this);
        pager_login.setOnClickListener(this);
    }
    //页面滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //页面选中监听
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < images.size(); i++) {
            if (position == i) {
                ImageView imageView = (ImageView) images.get(position);
                imageView.setImageResource(R.mipmap.dian);
            } else {
                ImageView imageView = (ImageView) images.get(i);
                imageView.setImageResource(R.mipmap.dian2);
            }
        }
        //判断按钮是否显示
        if (position == (images.size() - 1)) {
            pager_login.setVisibility(View.VISIBLE);
        } else {
            pager_login.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        //保存用户进入状态
        editor.putBoolean(Contants.ISFIRSTENTER, true);
        editor.commit();
        startActivity(new Intent(QiDong.this, Start.class));
        finish();
    }
}

