package com.hanboard.teacher.app;

import android.app.Application;

import com.hanboard.teacherhd.lib.common.http.okhttp.OkHttpUtils;
import com.hanboard.teacherhd.lib.common.http.okhttp.log.LoggerInterceptor;
import com.hanboard.teacherhd.lib.common.utils.NetUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/7/16 0016.
 */
public class AppContext extends Application {

    private static final String TAG = "InitApplication";
    private static boolean isLogged = false;
    private static AppContext instance;
    public static int mNetWorkState;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initOkHttp();
        initData();
        com.lzy.okhttputils.OkHttpUtils.init(this);
        /*Crash异常上报*/
        CrashReport.initCrashReport(getApplicationContext());
    }
    /**初始化okhttp*/
   public void initOkHttp(){
       OkHttpClient okHttpClient = new OkHttpClient.Builder()
               .addInterceptor(new LoggerInterceptor("TAG"))
               .connectTimeout(10000L, TimeUnit.MILLISECONDS)
               .readTimeout(10000L, TimeUnit.MILLISECONDS)
               .build();
       OkHttpUtils.initClient(okHttpClient);
   }
    public static AppContext getInstance()
    {
        return instance;
    }

    public void initData() {
        mNetWorkState = NetUtil.getNetworkState(this);
    }

}
