package com.school.graduationdesign.Base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/13
 */
public abstract class BaseFragment extends Fragment {
    public Activity activity;

    //Fragment的创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    //处理Fragment布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // 依附的activity创建完成
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //界面已经创建完成了，界面已经可以展示给我们了，这个时候我们最好初始化一下数据
        initData();
    }

    //初始化数据（因为布局创建完成之后有很多数据需要处理，所以需要初始化一下数据，这个方法可以实现，也可以不实现）
    public void initData() {

    }
}
