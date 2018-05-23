package com.school.graduationdesign.Adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.school.graduationdesign.Activity.GridChoose;
import com.school.graduationdesign.Activity.YingYuanCinemaTime;
import com.school.graduationdesign.Bean.YingYuanCinemaTimeBean;
import com.school.graduationdesign.R;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 座位选择适配器
 * @date 2016/4/19
 */
public class YingYuanCinemaTimeAdapter extends BaseAdapter {
    private YingYuanCinemaTime yingYuanCinemaTime;
    private List<YingYuanCinemaTimeBean> list;


    public YingYuanCinemaTimeAdapter(YingYuanCinemaTime yingYuanCinemaTime, List<YingYuanCinemaTimeBean> list) {
        this.yingYuanCinemaTime = yingYuanCinemaTime;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final YingYuanCinemaTimeBean yingYuanCinemaTimeBean = list.get(position);
        ViewHold viewHold = null;
        //先判断是否有缓存
        if (convertView == null) {
            //创建缓存对象
            viewHold = new ViewHold();
            //填充view
            convertView = View.inflate(yingYuanCinemaTime, R.layout.activity_yingyuancinematimelist, null);
            viewHold.yingyuan_cinematimelist_name = (TextView) convertView.findViewById(R.id.yingyuan_cinematimelist_name);
            viewHold.yingyuan_cinematimelist_time = (TextView) convertView.findViewById(R.id.yingyuan_cinematimelist_time);
            viewHold.yingyuan_cinematimelist_price = (TextView) convertView.findViewById(R.id.yingyuan_cinematimelist_price);
            viewHold.yingyuan_cinematimelist_num = (TextView) convertView.findViewById(R.id.yingyuan_cinematimelist_num);
            viewHold.yingyuan_cinematimelist_btn = (Button) convertView.findViewById(R.id.yingyuan_cinematimelist_btn);
            //把ViewHoid传给convertView
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.yingyuan_cinematimelist_name.setText(yingYuanCinemaTimeBean.getYingyuan_cinematimelist_name());
        viewHold.yingyuan_cinematimelist_time.setText(yingYuanCinemaTimeBean.getYingyuan_cinematimelist_time());
        viewHold.yingyuan_cinematimelist_price.setText("单价：" + yingYuanCinemaTimeBean.getYingyuan_cinematimelist_price());
        viewHold.yingyuan_cinematimelist_num.setText("影厅:" + yingYuanCinemaTimeBean.getYingyuan_cinematimelist_num());
        viewHold.yingyuan_cinematimelist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yingYuanCinemaTime, GridChoose.class);
                intent.putExtra("bean", yingYuanCinemaTimeBean);
                yingYuanCinemaTime.startActivity(intent);
            }
        });
        return convertView;
    }


    class ViewHold {
        TextView yingyuan_cinematimelist_name;
        TextView yingyuan_cinematimelist_time;
        TextView yingyuan_cinematimelist_price;
        TextView yingyuan_cinematimelist_num;
        Button yingyuan_cinematimelist_btn;
    }
}
