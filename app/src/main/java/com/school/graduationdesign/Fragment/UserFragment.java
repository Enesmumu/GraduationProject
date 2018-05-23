package com.school.graduationdesign.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.graduationdesign.Activity.UserInfo;
import com.school.graduationdesign.Bean.Contants;
import com.school.graduationdesign.Database.DBConfig;
import com.school.graduationdesign.Database.DBHelper;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/9
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    private ImageView touxiang;
    private TextView nick, des;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private DBHelper helper;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取Activity
        Activity activity = getActivity();
        //加载布局
        View view = inflater.inflate(R.layout.activity_user, null);
        //查找控件
        touxiang = (ImageView) view.findViewById(R.id.touxiang);
        nick= (TextView) view.findViewById(R.id.nick);
        des= (TextView) view.findViewById(R.id.des);
        touxiang.setOnClickListener(this);


        //获取登录时的ID
        sharedPreferences = getActivity().getSharedPreferences(Contants.CONFIG, Context.MODE_PRIVATE);
        String num = sharedPreferences.getString("NUM", "");
        openDB();
        //查询该登录ID
        Cursor cursor = db.query(DBConfig.User.USER_TABLE, null, DBConfig.User.USER_NUM + "=?", new String[]{num}, null, null, null);
        while (cursor.moveToNext()) {
            String nicheng = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_NICK));
            String jianjie = cursor.getString(cursor.getColumnIndex(DBConfig.User.USER_DES));
            if (TextUtils.isEmpty(nicheng)) {

            } else {
                nick.setText(nicheng);
            }
            if (TextUtils.isEmpty(jianjie)) {

            } else {
                des.setText(jianjie);
            }
        }
        cursor.close();
        db.close();
        //加载数据
        return view;
    }

    private void openDB() {
        //创建帮助类对象
        helper = new DBHelper(getActivity());
        //创建或打开数据库
        db = helper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.touxiang:
                Intent intent = new Intent(getActivity(), UserInfo.class);
                startActivity(intent);
                break;
        }
    }
}
