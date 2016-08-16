package com.hanboard.teacher.app.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanboard.teacher.R;
import com.hanboard.teacher.common.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SplashActivity extends BaseActivity {
    //显示整块启动页的图片
    @BindView(R.id.splash_iv)
    ImageView mSplashIv;
    //启动页面的倒计时
    @BindView(R.id.counter)
    TextView mCounter;
    //用于倒计时的时间控制器
    private Timer mTimer;
    //倒计时的起始时间
    private int mRecLen = 6;
    //时间控制器里面执行的线程操作
    private TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mRecLen--;
                    mCounter.setVisibility(View.VISIBLE);
                    mCounter.setText("跳过  " + mRecLen + "秒");
                    if (mRecLen < 1) {
                        //取消计时
                        mTimer.cancel();
                        startActivity(LoginActivity.class);
                        finish();
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTimer = new Timer();
        //计时器执行调度,延迟1秒执行一次
        mTimer.schedule(mTask, 1000, 1000);
        //设置图片的动画效果
        PropertyValuesHolder pvhx = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f);
        PropertyValuesHolder pvhy = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f);
        PropertyValuesHolder pvhalpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(mSplashIv, pvhalpha, pvhx, pvhy).setDuration(20000).start();


    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected void handler(Message msg) {

    }
    //显示倒计时的文本控件点击跳转事件
    @OnClick(R.id.counter)
    public void onClick() {
        startActivity(LoginActivity.class);
        finish();
        mTimer.cancel();
    }

}
