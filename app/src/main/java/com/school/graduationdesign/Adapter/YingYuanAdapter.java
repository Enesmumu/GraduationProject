package com.school.graduationdesign.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Activity.YingYuan;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.MovieBean;
import com.school.graduationdesign.Bean.YingYuanBean;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 电影界面下的影院适配器
 * @date 2016/4/6
 */
public class YingYuanAdapter extends BaseAdapter {
    private YingYuan yingYuan;
    private List<YingYuanBean> list;

    public YingYuanAdapter(YingYuan yingYuan, List<YingYuanBean> list) {
        this.yingYuan = yingYuan;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(yingYuan, R.layout.activity_yingyuanlist, null);
            holder.name = (TextView) convertView.findViewById(R.id.dianyingname_list);
            holder.des = (TextView) convertView.findViewById(R.id.dianyingdes_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        YingYuanBean bean = list.get(position);
        holder.name.setText(bean.getDianyingname_list());
        holder.des.setText(bean.getDianyingdes_list());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView des;
    }
}
