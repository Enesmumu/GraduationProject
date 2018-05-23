package com.school.graduationdesign.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.school.graduationdesign.Activity.GridChoose;
import com.school.graduationdesign.Bean.GridChooseBean;
import com.school.graduationdesign.R;

import java.util.List;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/25
 */
public class GridChooseAdapter extends BaseAdapter {
    private GridChoose gridChoose;
    private List<GridChooseBean> list;
    private TextView gv_choose_price;
    private double choose_price;
    private double zongjia;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor seat_editor;

    public GridChooseAdapter(GridChoose gridChoose, List<GridChooseBean> list, TextView gv_choose_price, double choose_price, double zongjia) {
        this.gridChoose = gridChoose;
        this.list = list;
        this.gv_choose_price = gv_choose_price;
        this.choose_price = choose_price;
        this.zongjia = zongjia;
        SharedPreferences sp = gridChoose.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = gridChoose.getSharedPreferences("seat",Context.MODE_PRIVATE);
        editor = sp.edit();
        seat_editor=sharedPreferences.edit();

        //sharedPreferences = gridChoose.getSharedPreferences("Seat", 0);
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
    public View getView(final int position, View convertView, final ViewGroup parent) {

        GridChooseBean gridChooseBean = list.get(position);
        //ChooseBean chooseBean = new ChooseBean();
        View view = View.inflate(gridChoose, R.layout.activity_gridchooselist, null);
        CheckBox gv_seat = (CheckBox) view.findViewById(R.id.gv_seat);
//        String status = gridChooseBean.getGv_seat();
//        String xuanzhong = chooseBean.getXuanzhong();
        final String num = gridChooseBean.getGv_num();
        gv_seat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //SharedPreferences.Editor edit = sharedPreferences.edit();

                if (isChecked) {
                    zongjia += choose_price;
                   seat_editor.putString("seat",num);
                    //editor.putString(String.valueOf(position), num);
                } else {
                    zongjia -= choose_price;
                    seat_editor.remove(num);
                    //edit.remove(num);
                }
                //edit.commit();
                seat_editor.commit();
                gv_choose_price.setText(zongjia + "元");
                editor.putString("money",String.valueOf(zongjia)).commit();
                Toast.makeText(gridChoose, isChecked ? num : "取消", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
