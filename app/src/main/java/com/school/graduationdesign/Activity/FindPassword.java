package com.school.graduationdesign.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/20
 */
public class FindPassword extends BaseActivity implements View.OnClickListener {
    private Button find_btn_update, find_btn_num;
    private ImageView find_fanhui;
    private EditText find_et_num, find_et_phone;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_findpasswpord);
    }

    @Override
    protected void findView() {
        find_fanhui = (ImageView) findViewById(R.id.find_fanhui);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        find_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_fanhui:
                finish();
                break;
        }
    }
}
