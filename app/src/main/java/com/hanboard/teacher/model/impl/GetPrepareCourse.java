package com.hanboard.teacher.model.impl;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.common.config.BaseMap;
import com.hanboard.teacher.common.config.CodeInfo;
import com.hanboard.teacher.common.config.CoderConfig;
import com.hanboard.teacher.common.config.Constants;
import com.hanboard.teacher.common.config.Urls;
import com.hanboard.teacher.common.tools.JsonGenericsSerializator;
import com.hanboard.teacher.entity.Elements;
import com.hanboard.teacher.entity.MData;
import com.hanboard.teacher.entity.PrepareSelectCourse;
import com.hanboard.teacher.model.IGetPrepareCourse;
import com.hanboard.teacherhd.lib.common.http.okhttp.OkHttpUtils;
import com.hanboard.teacherhd.lib.common.http.okhttp.callback.GenericsCallback;
import com.hanboard.teacherhd.lib.common.utils.DESCoder;
import com.hanboard.teacherhd.lib.common.utils.JsonUtil;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/9.
 */
public class GetPrepareCourse implements IGetPrepareCourse {
    private static final String TAG = "GetPrepareCourse";
    @Override
    public void getPrepareCourse(String accountId, String pageNum, final IDataCallback<Elements<PrepareSelectCourse>> elementsIDataCallback) {
        try {
            Map<String,String> params=new BaseMap().initMap();
            params.put("pageNum",pageNum);
            params.put("accountId",new DESCoder(CoderConfig.CODER_CODE).encrypt(accountId));
            OkHttpUtils.get().params(params).url(Urls.URL_GETPREPARECOUSER).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
                @Override
                public void onError(Call call, Exception e, int id) {
                           elementsIDataCallback.onError(e.getMessage(),600);
                    Log.e(TAG, "onError: "+e.getMessage() );
                }

                @Override
                public void onResponse(String response, int id) {
                      if (null!=response||!response.equals("")){
                          MData<Elements<PrepareSelectCourse>> res = JsonUtil.fromJson(response,new TypeToken<MData<Elements<PrepareSelectCourse>>>(){}.getType());
                          if (res.code.equals(Constants.CODE_SUCCESS)){
                              elementsIDataCallback.onSuccess(res.result);
                          }else elementsIDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
                      }else elementsIDataCallback.onError(CodeInfo.REQUEST_EMPTY,0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
