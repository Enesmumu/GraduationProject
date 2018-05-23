package com.school.graduationdesign.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/11
 */
public abstract class NewActivity extends FragmentActivity {
    public NewActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        loadLayout();
        findView();
        getData();
        setData();
        setClick();

    }

    //加载布局
    protected abstract void loadLayout();

    //查找控件
    protected abstract void findView();

    //获取数据
    protected abstract void getData();

    //设置数据
    protected abstract void setData();

    //设置监听
    protected abstract void setClick();


}
