package com.school.graduationdesign.Administration.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.graduationdesign.Administration.AdmUser;
import com.school.graduationdesign.Administration.Bean.UserBean;
import com.school.graduationdesign.R;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/26
 */
public class UserAdapter extends BaseAdapter {
    private AdmUser admUser;
    private List<UserBean> list;

    public UserAdapter(AdmUser admUser, List<UserBean> list) {
        this.admUser = admUser;
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
            convertView = View.inflate(admUser, R.layout.activity_adm_user_list, null);
            holder.num = (TextView) convertView.findViewById(R.id.adm_user_list_num);
            holder.password = (TextView) convertView.findViewById(R.id.adm_user_list_password);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        UserBean bean = list.get(position);
        holder.num.setText(bean.getAdm_user_list_num());
        holder.password.setText(bean.getAdm_user_list_password());
        return convertView;
    }

    private class ViewHolder {
        TextView num;
        TextView password;
    }
}
