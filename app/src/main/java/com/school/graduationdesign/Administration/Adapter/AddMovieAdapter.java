package com.school.graduationdesign.Administration.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.graduationdesign.Administration.AdmMovie;
import com.school.graduationdesign.Administration.Bean.AddCinemaBean;
import com.school.graduationdesign.Administration.Bean.AddMovieBean;
import com.school.graduationdesign.R;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/21
 */
public class AddMovieAdapter extends BaseAdapter {
    private AdmMovie admMovie;
    private List<AddMovieBean> list;

    public AddMovieAdapter(AdmMovie admMovie, List<AddMovieBean> list) {
        this.admMovie = admMovie;
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
            convertView = View.inflate(admMovie, R.layout.activity_adm_movie_list, null);
            holder.name = (TextView) convertView.findViewById(R.id.adm_movie_list_name);
            holder.des = (TextView) convertView.findViewById(R.id.adm_movie_list_des);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        AddMovieBean bean = list.get(position);
        holder.name.setText(bean.getAdm_movie_list_name());
        holder.des.setText(bean.getAdm_movie_list_des());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView des;
    }
}
