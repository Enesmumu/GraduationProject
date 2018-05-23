package com.school.graduationdesign.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.graduationdesign.Activity.DianYing;
import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.Bean.DianYingBean;
import com.school.graduationdesign.Bean.YingYuanBean;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 影院界面下的电影适配器
 * @date 2016/4/6
 */
public class DianYingAdapter extends BaseAdapter {
    private DianYing dianYing;
    private List<DianYingBean> list;

    public DianYingAdapter(DianYing dianYing, List<DianYingBean> list) {
        this.dianYing = dianYing;
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
            convertView = View.inflate(dianYing, R.layout.activity_dianyinglist, null);
            holder.name = (TextView) convertView.findViewById(R.id.yingyuanname_list);
            holder.add = (TextView) convertView.findViewById(R.id.yingyuanadd_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        DianYingBean bean = list.get(position);
        holder.name.setText(bean.getYingyuanname_list());
        holder.add.setText(bean.getYingyuanadd_list());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView add;
    }
}
