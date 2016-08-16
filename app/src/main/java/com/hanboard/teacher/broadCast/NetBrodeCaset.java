package com.hanboard.teacher.broadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.hanboard.teacher.app.AppContext;
import com.hanboard.teacher.model.InetEventHandler;
import com.hanboard.teacherhd.lib.common.utils.NetUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/12.
 */
public class NetBrodeCaset extends BroadcastReceiver{
    public static ArrayList<InetEventHandler> mListeners = new ArrayList<InetEventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION )) {
            AppContext.mNetWorkState = NetUtil.getNetworkState(context);
            if (mListeners.size() > 0)// 通知接口完成加载
                for (InetEventHandler handler : mListeners) {
                    handler.onWifiNet();
                }
        }
    }


}
