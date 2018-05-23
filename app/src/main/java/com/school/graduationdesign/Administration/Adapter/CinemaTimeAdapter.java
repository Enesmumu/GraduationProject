package com.school.graduationdesign.Administration.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.graduationdesign.Administration.AdmCinemaTime;
import com.school.graduationdesign.Administration.Bean.CinemaTimeBean;
import com.school.graduationdesign.R;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/3
 */
public class CinemaTimeAdapter extends BaseAdapter {
    private AdmCinemaTime admCinemaTime;
    private List<CinemaTimeBean> list;

    public CinemaTimeAdapter(AdmCinemaTime admCinemaTime, List<CinemaTimeBean> list) {
        this.admCinemaTime = admCinemaTime;
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
            convertView = View.inflate(admCinemaTime, R.layout.activity_adm_cinematime_list, null);
            holder.name = (TextView) convertView.findViewById(R.id.adm_cinematime_list_name);
            holder.time = (TextView) convertView.findViewById(R.id.adm_cinematime_list_time);
            holder.price = (TextView) convertView.findViewById(R.id.adm_cinematime_list_price);
            holder.num = (TextView) convertView.findViewById(R.id.adm_cinematime_list_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        CinemaTimeBean bean = list.get(position);
        holder.name.setText(bean.getAdm_cinematime_list_name());
        holder.time.setText(bean.getAdm_cinematime_list_time());
        holder.price.setText(bean.getAdm_cinematime_list_price());
        holder.num.setText(bean.getAdm_cinematime_list_num());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView time;
        TextView price;
        TextView num;
    }
}
