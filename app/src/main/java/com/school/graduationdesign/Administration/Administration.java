package com.school.graduationdesign.Administration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.school.graduationdesign.Base.BaseActivity;
import com.school.graduationdesign.R;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 管理员界面
 * @date 2016/4/19
 */
public class Administration extends BaseActivity implements View.OnClickListener {
    private Button adm_cinema, adm_movie, adm_user, adm_cinematime, adm_seat;
    private ImageView adm_fanhui;

    @Override
    protected void loadLayout() {
        setContentView(R.layout.activity_administration);
    }

    @Override
    protected void findView() {
        adm_fanhui = (ImageView) findViewById(R.id.adm_fanhui);
        adm_cinema = (Button) findViewById(R.id.adm_cinema);
        adm_movie = (Button) findViewById(R.id.adm_movie);
        adm_user = (Button) findViewById(R.id.adm_user);
        adm_cinematime = (Button) findViewById(R.id.adm_cinematime);
        adm_seat = (Button) findViewById(R.id.adm_seat);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setClick() {
        adm_fanhui.setOnClickListener(this);
        adm_cinema.setOnClickListener(this);
        adm_movie.setOnClickListener(this);
        adm_user.setOnClickListener(this);
        adm_cinematime.setOnClickListener(this);
        adm_seat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adm_fanhui:
                finish();
                break;
            case R.id.adm_cinema:
                Intent intent1 = new Intent(Administration.this, AdmCinema.class);
                startActivity(intent1);
                break;
            case R.id.adm_movie:
                Intent intent2 = new Intent(Administration.this, AdmMovie.class);
                startActivity(intent2);
                break;
            case R.id.adm_user:
                Intent intent3 = new Intent(Administration.this, AdmUser.class);
                startActivity(intent3);
                break;
            case R.id.adm_cinematime:
                Intent intent4 = new Intent(Administration.this, AdmCinemaTime.class);
                startActivity(intent4);
                break;
            case R.id.adm_seat:
                Intent intent5 = new Intent(Administration.this, AdmSeat.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Administration.this);
            AlertDialog dialog = builder.create();
            dialog.setMessage("确认退出登录？");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
        return super.onKeyDown(keyCode, event);
    }
}
