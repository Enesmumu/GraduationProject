package com.school.graduationdesign.Adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Administration.Bean.AddMovieBean;
import com.school.graduationdesign.Bean.MovieBean;
import com.school.graduationdesign.Fragment.MovieFragment;
import com.school.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 电影适配器
 * @date 2016/3/31
 */
public class MovieAdapter extends BaseAdapter {
    private MovieFragment fragment;
    private List<MovieBean> list;


    public MovieAdapter(MovieFragment fragment, List<MovieBean> list) {
        this.fragment=fragment;
        this.list=list;
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
            convertView = View.inflate(fragment.getActivity(), R.layout.activity_movielist, null);
            holder.name = (TextView) convertView.findViewById(R.id.moviename_list);
            holder.des = (TextView) convertView.findViewById(R.id.moviedes_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        MovieBean bean = list.get(position);
        holder.name.setText(bean.getMoviename_list());
        holder.des.setText(bean.getMoviedes_list());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView des;
    }
}