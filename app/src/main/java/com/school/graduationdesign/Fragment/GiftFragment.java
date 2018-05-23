package com.school.graduationdesign.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.school.graduationdesign.Adapter.GiftPagerAdapter;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 活动界面
 * @date 2016/4/9
 */
public class GiftFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager gift_viewpager;
    private ListView gift_list;
    private ImageView giftdian1, giftdian2, giftdian3;
    private List list=new ArrayList();
    private List images;
    int[] pic = {R.mipmap.giftpager1, R.mipmap.giftpager2, R.mipmap.shaw};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Activity activity = getActivity();
        View view = View.inflate(activity, R.layout.activity_gift, null);
        initImage();//初始化图片
        findView(view);//查找控件
        setDate();//加载数据,空链表也可以建立一个适配器，所以可以向设置适配器再给其传数据
        getDate(10);//设置数据
        setClick();//设置事件
        return view;
    }


    private void findView(View view) {
        //查找控件
        gift_viewpager = (ViewPager) view.findViewById(R.id.gift_viewpager);
        gift_list = (ListView) view.findViewById(R.id.gift_list);
        //设置圆点
        giftdian1 = (ImageView) view.findViewById(R.id.giftdian1);
        giftdian2 = (ImageView) view.findViewById(R.id.giftdian2);
        giftdian3 = (ImageView) view.findViewById(R.id.giftdian3);
        images = new ArrayList<>();
        images.add(giftdian3);
        images.add(giftdian2);
        images.add(giftdian1);

    }


    private void getDate(int i) {

    }

    private void setDate() {
        //创建适配器
        GiftPagerAdapter giftPagerAdapter = new GiftPagerAdapter(list);
        //设置适配器
        gift_viewpager.setAdapter(giftPagerAdapter);
        //监听
        gift_viewpager.setOnPageChangeListener(this);
    }

    private void setClick() {
        gift_viewpager.setOnPageChangeListener(this);
    }

    private void initImage() {
        for (int i = 0; i < pic.length; i++) {
            ImageView images = new ImageView(getActivity());
            images.setBackgroundResource(pic[i]);
            list.add(images);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

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
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
