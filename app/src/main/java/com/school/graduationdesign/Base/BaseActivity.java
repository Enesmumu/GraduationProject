package com.school.graduationdesign.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/3/31
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    public BaseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        //1.加载布局
        loadLayout();
        //2.查找控件
        findView();
        //3.获取数据
        getData();
        //4.设置数据
        setData();
        //5.设置点击事件
        setClick();

    }

    protected abstract void loadLayout();

    protected abstract void findView();

    protected abstract void getData();

    protected abstract void setData();

    protected abstract void setClick();
}

