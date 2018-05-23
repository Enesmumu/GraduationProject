package com.school.graduationdesign.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 启动界面适配器
 * @date 2016/4/5
 */
public class QidongAdapter extends PagerAdapter {
    private ArrayList arrayList;

    public QidongAdapter(ArrayList arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    //实例化条目，添加到viewPager
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = (ImageView) arrayList.get(position);
        container.addView(imageView);
        return imageView;
    }

    //判断添加的条目是否是要显示的View
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //销毁
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
