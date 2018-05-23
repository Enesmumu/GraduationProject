package com.school.graduationdesign.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.school.graduationdesign.Bean.Contants;
import com.school.graduationdesign.Base.NewActivity;
import com.school.graduationdesign.R;
import com.school.graduationdesign.util.Tiaozhuan;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 引导界面
 * @date 2016/4/11
 */
public class Guide extends NewActivity {
    private ImageView guide;
    private SharedPreferences preference;
    private boolean isFirstEnter;


    protected void loadLayout() {
        setContentView(R.layout.activity_guide);
    }

    protected void findView() {
        guide= (ImageView) findViewById(R.id.guide);

    }


    protected void getData() {
        //获取SharedPreferences对象
        preference = getSharedPreferences(Contants.CONFIG, MODE_PRIVATE);
        //获取用户进入的状态
        isFirstEnter = preference.getBoolean(Contants.ISFIRSTENTER, false);

    }


    protected void setData() {

    }


    protected void setClick() {
        startGif();
    }

    private void startGif() {
        AnimationSet animationSet = new AnimationSet(false);
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        //缩放动画(淡入淡出)，设置事件长度可以根据缩放动画来
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(0000);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*
                判断是否是第一次进入，如果是的话就去引导界面，不是的话就不去引导 界面直接去登录界面
                 */
                if(isFirstEnter)
                {
                    startActivity(new Intent(Guide.this, Start.class));
                }else {
                    startActivity(new Intent(Guide.this, QiDong.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        guide.startAnimation(animationSet);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Guide.this);
            AlertDialog dialog = builder.create();
            dialog.setMessage("确认退出？");
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
