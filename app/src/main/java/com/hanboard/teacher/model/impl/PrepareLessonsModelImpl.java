package com.hanboard.teacher.model.impl;

import com.google.gson.reflect.TypeToken;
import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.common.config.BaseMap;
import com.hanboard.teacher.common.config.CodeInfo;
import com.hanboard.teacher.common.config.CoderConfig;
import com.hanboard.teacher.common.config.Constants;
import com.hanboard.teacher.common.config.Urls;
import com.hanboard.teacher.common.tools.JsonGenericsSerializator;
import com.hanboard.teacher.entity.Chapter;
import com.hanboard.teacher.entity.Domine;
import com.hanboard.teacher.entity.Elements;
import com.hanboard.teacher.entity.MData;
import com.hanboard.teacher.entity.PrepareChapter;
import com.hanboard.teacher.entity.listentity.ListChapter;
import com.hanboard.teacher.model.IPrepareLessonsModel;
import com.hanboard.teacherhd.lib.common.http.okhttp.OkHttpUtils;
import com.hanboard.teacherhd.lib.common.http.okhttp.callback.GenericsCallback;
import com.hanboard.teacherhd.lib.common.http.okhttp.callback.StringCallback;
import com.hanboard.teacherhd.lib.common.utils.DESCoder;
import com.hanboard.teacherhd.lib.common.utils.JsonUtil;

import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/26 0026 14:22
 */
public class PrepareLessonsModelImpl implements IPrepareLessonsModel {

    @Override
    public void getChapterList(String tid, String vid, String sid, String key, final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> param = new BaseMap().initMap();
            param.put("tid",tid);
            param.put("vid",new DESCoder(CoderConfig.CODER_CODE).encrypt(vid));
//            param.put("sid",new DESCoder(CoderConfig.CODER_CODE).encrypt(sid));
//            param.put("key",new DESCoder(CoderConfig.CODER_CODE).encrypt(key));
            OkHttpUtils.get().url(Urls.URL_GETAllCURSOR$CHAPTER).params(param).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator())
            {
                @Override
                public void onError(Call call, Exception e, int id)
                {
                    iDataCallback.onError(e.getMessage(),500);
                }
                @Override
                public void onResponse(String response, int id)
                {
                    if(null!=response||!response.equals("")){
                        MData<List<Chapter>> res = JsonUtil.fromJson(response,new TypeToken<MData<List<Chapter>>>(){}.getType());
                        ListChapter chapterList = new ListChapter();
                        if(res.code.equals(Constants.CODE_SUCCESS)){
                            chapterList.chapters = res.result;
                            iDataCallback.onSuccess(chapterList);
                        }else{
                            iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
                        }
                    }else {
                        iDataCallback.onError(CodeInfo.REQUEST_EMPTY,0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getChapterDetials(String accountId, String chapterId, String teachBookId, String pageNum, final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> param = new BaseMap().initMap();
            param.put("accountId",new DESCoder(CoderConfig.CODER_CODE).encrypt(accountId));
            param.put("chapterId",new DESCoder(CoderConfig.CODER_CODE).encrypt(chapterId));
            param.put("teachBookId",new DESCoder(CoderConfig.CODER_CODE).encrypt(teachBookId));
            param.put("pageNum",pageNum);
            OkHttpUtils.get().params(param).url(Urls.URL_GETPREPARECHATER).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    iDataCallback.onError(e.getMessage(),id);
                }

                @Override
                public void onResponse(String response, int id) {
                    if(null!=response||!response.equals("")){
                        MData<Elements<PrepareChapter>> res = JsonUtil.fromJson(response,new TypeToken<MData<Elements<PrepareChapter>>>(){}.getType());
                        if(res.code.equals(Constants.CODE_SUCCESS)){
                            iDataCallback.onSuccess(res.result);
                        }else{
                            iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
                        }
                    }else {
                        iDataCallback.onError(CodeInfo.REQUEST_EMPTY,0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
