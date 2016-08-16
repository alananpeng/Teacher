package com.hanboard.teacher.broadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hanboard.teacher.common.config.Constants;


/**
 * Created by Administrator on 2016/8/8.
 */
public class WPSReciver_ extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Constants.ACTION_BACK://返回键广播
                System.out.println(Constants.ACTION_BACK);

                break;
            case Constants.ACTION_CLOSE://关闭文件时候的广播
                System.out.println(Constants.ACTION_CLOSE);

                break;
            case Constants.ACTION_HOME://home键广播
                System.out.println(Constants.ACTION_HOME);

                break;
            case Constants.ACTION_SAVE://保存广播
                System.out.println(Constants.ACTION_SAVE);

                break;

            default:
                break;
        }
    }
}
