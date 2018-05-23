package com.school.graduationdesign.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 滑动适配器
 * @date 2016/4/9
 */
public class HuaDongAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public HuaDongAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
