package com.school.graduationdesign.Administration.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.graduationdesign.Administration.AdmCinema;
import com.school.graduationdesign.Administration.Bean.AddCinemaBean;
import com.school.graduationdesign.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 添加影院适配器
 * @date 2016/4/20
 */
public class AddCinemaAdapter extends BaseAdapter {
    private AdmCinema admCinema;
    private List<AddCinemaBean> list;

    public AddCinemaAdapter(AdmCinema admCinema, List<AddCinemaBean> list) {
        this.admCinema = admCinema;
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
            convertView = View.inflate(admCinema, R.layout.activity_adm_cinema_list, null);
            holder.name = (TextView) convertView.findViewById(R.id.adm_cinema_list_name);
            holder.ad = (TextView) convertView.findViewById(R.id.adm_cinema_list_ad);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        AddCinemaBean bean = list.get(position);
        holder.name.setText(bean.getAdm_cinema_list_name());
        holder.ad.setText(bean.getAdd_cinema_list_ad());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView ad;
    }
}
