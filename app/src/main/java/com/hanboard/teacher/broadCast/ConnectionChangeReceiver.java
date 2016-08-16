package com.hanboard.teacher.broadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/28 0028 11:15
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mConnectivityManager.getActiveNetworkInfo();
            if(netInfo != null && netInfo.isAvailable()) {
                /////////////网络连接
                String name = netInfo.getTypeName();

                if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){
                    /////WiFi网络

                }else if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
                    /////有线网络

                }else if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                    /////////3g网络

                }
            } else {
                ////////网络断开

            }
        }

    }
}
