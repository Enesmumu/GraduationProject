package com.school.graduationdesign.Administration.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.graduationdesign.Administration.AdmSeat;
import com.school.graduationdesign.Administration.Bean.SeatBean;
import com.school.graduationdesign.R;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/5
 */
public class SeatAdapter extends BaseAdapter {
    private AdmSeat admSeat;
    private List<SeatBean> list;

    public SeatAdapter(AdmSeat admSeat, List<SeatBean> list) {
        this.admSeat = admSeat;
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
            convertView = View.inflate(admSeat, R.layout.activity_adm_seat_list, null);
            holder.status = (TextView) convertView.findViewById(R.id.adm_seat_list_status);
            holder.num = (TextView) convertView.findViewById(R.id.adm_seat_list_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取item
        SeatBean bean = list.get(position);
        holder.status.setText(bean.getAdm_seat_list_status());
        holder.num.setText(bean.getAdm_seat_list_num());
        return convertView;
    }

    private class ViewHolder {
        TextView status;
        TextView num;
    }
}
