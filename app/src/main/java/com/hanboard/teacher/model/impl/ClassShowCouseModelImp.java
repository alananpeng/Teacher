package com.hanboard.teacher.model.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.common.config.BaseMap;
import com.hanboard.teacher.common.config.CodeInfo;
import com.hanboard.teacher.common.config.CoderConfig;
import com.hanboard.teacher.common.config.Constants;
import com.hanboard.teacher.common.config.Urls;
import com.hanboard.teacher.common.tools.JsonGenericsSerializator;
import com.hanboard.teacher.entity.CoursewareInfo;
import com.hanboard.teacher.entity.Domine;
import com.hanboard.teacher.entity.MData;
import com.hanboard.teacher.model.IClassShowCouseModel;
import com.hanboard.teacherhd.lib.common.http.okhttp.OkHttpUtils;
import com.hanboard.teacherhd.lib.common.http.okhttp.callback.GenericsCallback;
import com.hanboard.teacherhd.lib.common.utils.DESCoder;
import com.hanboard.teacherhd.lib.common.utils.JsonUtil;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/11.
 */
public class ClassShowCouseModelImp implements IClassShowCouseModel {
    private static final String TAG = "ClassShowCouseModelImp";
    @Override
    public void getAllCouseWareInfo(String contentId,Context context, final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> params=new BaseMap().initMap();
            params.put("contentId",new DESCoder(CoderConfig.CODER_CODE).encrypt(contentId));
            OkHttpUtils.get().params(params).url(Urls.URL_GETPRECOURSEWARE).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
                @Override
                public void onError(Call call, Exception e, int id) {
                    iDataCallback.onError(e.getMessage(),600);
                    Log.e(TAG, "onError: "+e.getMessage() );
                }

                @Override
                public void onResponse(String response, int id) {
                    if (null!=response||!response.equals("")){
                        MData<CoursewareInfo> res = JsonUtil.fromJson(response,new TypeToken<MData<CoursewareInfo>>(){}.getType());
                        if (res.code.equals(Constants.CODE_SUCCESS)){
                            iDataCallback.onSuccess(res.result);
                        }else iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
                    }else iDataCallback.onError(CodeInfo.REQUEST_EMPTY,0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
