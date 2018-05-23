package com.school.graduationdesign.Adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Administration.Bean.AddMovieBean;
import com.school.graduationdesign.Bean.CinemaBean;
import com.school.graduationdesign.Bean.MovieBean;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Fragment.CinemaFragment;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 影院适配器
 * @date 2016/3/31
 */
public class CinemaAdapter extends BaseAdapter {
    private CinemaFragment fragment;
    private List<CinemaBean> list;

    public CinemaAdapter(CinemaFragment fragment, List<CinemaBean> list) {
        this.fragment = fragment;
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
            convertView = View.inflate(fragment.getActivity(),R.layout.activity_cinemalist, null);
            holder.name = (TextView) convertView.findViewById(R.id.cinemaname_list);
            holder.add = (TextView) convertView.findViewById(R.id.cinemaadd_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        CinemaBean bean = list.get(position);
        holder.name.setText(bean.getCinemaname_list());
        holder.add.setText(bean.getCinemaadd_list());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView add;
    }
}