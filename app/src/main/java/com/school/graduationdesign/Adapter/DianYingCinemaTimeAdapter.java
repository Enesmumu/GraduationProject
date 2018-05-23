package com.school.graduationdesign.Adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.school.graduationdesign.Activity.DianYingCinemaTime;
import com.school.graduationdesign.Bean.DianYingCinemaTimeBean;
import com.school.graduationdesign.ChooseSeat.SelectMovieSeatActivity;
import com.school.graduationdesign.R;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/21
 */
public class DianYingCinemaTimeAdapter extends BaseAdapter implements View.OnClickListener {
    private DianYingCinemaTime dianYingCinemaTime;
    private List<DianYingCinemaTimeBean> list;

    public DianYingCinemaTimeAdapter(DianYingCinemaTime dianYingCinemaTime, List<DianYingCinemaTimeBean> list) {
        this.dianYingCinemaTime = dianYingCinemaTime;
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
        DianYingCinemaTimeBean dianYingCinemaTimeBean = list.get(position);
        ViewHold viewHold = null;
        //先判断是否有缓存
        if (convertView == null) {
            //创建缓存对象
            viewHold = new ViewHold();
            //填充view
            convertView = View.inflate(dianYingCinemaTime, R.layout.activity_dianyingcinematimelist, null);
            viewHold.dianying_cinematimelist_name = (TextView) convertView.findViewById(R.id.dianying_cinematimelist_name);
            viewHold.dianying_cinematimelist_time = (TextView) convertView.findViewById(R.id.dianying_cinematimelist_time);
            viewHold.dianying_cinematimelist_btn = (Button) convertView.findViewById(R.id.dianying_cinematimelist_btn);
            viewHold.dianying_cinematimelist_price = (TextView) convertView.findViewById(R.id.dianying_cinematimelist_price);
            viewHold.dianying_cinematimelist_num = (TextView) convertView.findViewById(R.id.dianying_cinematimelist_num);
            //把ViewHoid传给convertView
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.dianying_cinematimelist_name.setText(dianYingCinemaTimeBean.getDianying_cinematimelist_name());
        viewHold.dianying_cinematimelist_time.setText(dianYingCinemaTimeBean.getDianying_cinematimelist_time());
        viewHold.dianying_cinematimelist_price.setText("单价：" + dianYingCinemaTimeBean.getDianying_cinematimelist_price());
        viewHold.dianying_cinematimelist_num.setText("影厅:" + dianYingCinemaTimeBean.getDianying_cinematimelist_num());
        viewHold.dianying_cinematimelist_btn.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(dianYingCinemaTime, SelectMovieSeatActivity.class);
        dianYingCinemaTime.startActivity(intent);
    }

    class ViewHold {
        TextView dianying_cinematimelist_name;
        TextView dianying_cinematimelist_time;
        TextView dianying_cinematimelist_price;
        TextView dianying_cinematimelist_num;
        Button dianying_cinematimelist_btn;
    }
}
