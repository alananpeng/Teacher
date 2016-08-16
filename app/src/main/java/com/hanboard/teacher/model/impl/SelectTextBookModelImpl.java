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
import com.hanboard.teacher.entity.Status;
import com.hanboard.teacher.entity.Subject;
import com.hanboard.teacher.entity.SuitObject;
import com.hanboard.teacher.entity.TextBook;
import com.hanboard.teacher.entity.listentity.LessonsList;
import com.hanboard.teacher.entity.listentity.SubjectList;
import com.hanboard.teacher.entity.listentity.SuitObjectList;
import com.hanboard.teacher.entity.listentity.TextBookList;
import com.hanboard.teacher.model.ISelectTextBookModel;
import com.hanboard.teacherhd.lib.common.http.okhttp.OkHttpUtils;
import com.hanboard.teacherhd.lib.common.http.okhttp.callback.GenericsCallback;
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
 * 创建时间：2016/8/5 0005 16:45
 */
public class SelectTextBookModelImpl implements ISelectTextBookModel {
    @Override
    public void getAllSubject(final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> params = new BaseMap().initMap();
            OkHttpUtils.get().url(Urls.URL_ALLSUBJECT).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator())
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
                        MData<List<Subject>> res = JsonUtil.fromJson(response,new TypeToken<MData<List<Subject>>>(){}.getType());
                        SubjectList jsonResult = new SubjectList();
                        jsonResult.subjects = res.result;
                        if(res.code.equals(Constants.CODE_SUCCESS))
                            iDataCallback.onSuccess(jsonResult);
                        else
                            iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
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
    public void getAllSuitObject(final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> params = new BaseMap().initMap();
            OkHttpUtils.get().url(Urls.URL_ALLSUITOBJECT).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator())
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
                        MData<List<SuitObject>> res = JsonUtil.fromJson(response,new TypeToken<MData<List<SuitObject>>>(){}.getType());
                        SuitObjectList jsonResult = new SuitObjectList();
                        jsonResult.suitObjects = res.result;
                        if(res.code.equals(Constants.CODE_SUCCESS))
                            iDataCallback.onSuccess(jsonResult);
                        else
                            iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
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
    public void getTextbookBySubIdAndSuitID(String subjectId, String suitObjectId, final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> params = new BaseMap().initMap();
            params.put("subjectId", new DESCoder(CoderConfig.CODER_CODE).encrypt(subjectId));
            params.put("suitObjectId",new DESCoder(CoderConfig.CODER_CODE).encrypt(suitObjectId));
            params.put("sectionId","");
            params.put("versionId","");
            OkHttpUtils.get().url(Urls.URL_ALLTEXTBOOK).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator())
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
                        MData<List<TextBook>> res = JsonUtil.fromJson(response,new TypeToken<MData<List<TextBook>>>(){}.getType());
                        TextBookList jsonResult = new TextBookList();
                        jsonResult.textBooks = res.result;
                        if(res.code.equals(Constants.CODE_SUCCESS))
                            iDataCallback.onSuccess(jsonResult);
                        else
                            iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
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
    public void addTextbook(String accountId,TextBook textBook,final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> param = new BaseMap().initMap();
            param.put("teachBookId", new DESCoder(CoderConfig.CODER_CODE).encrypt(textBook.id));
            param.put("accountId",new DESCoder(CoderConfig.CODER_CODE).encrypt(accountId));
            param.put("subjectName",new DESCoder(CoderConfig.CODER_CODE).encrypt(textBook.subjectName));
            param.put("suitObjectName",new DESCoder(CoderConfig.CODER_CODE).encrypt(textBook.suitObjectName));
            param.put("sectionName",new DESCoder(CoderConfig.CODER_CODE).encrypt(textBook.sectionName));
            param.put("versionName",new DESCoder(CoderConfig.CODER_CODE).encrypt(textBook.versionName));
            param.put("publishDate",new DESCoder(CoderConfig.CODER_CODE).encrypt(textBook.publishDate));
            OkHttpUtils.get().url(Urls.URL_ADDTEXTBOOK).params(param).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator())
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
                        Status res = JsonUtil.fromJson(response,Status.class);
                        if(res.code.equals(Constants.CODE_SUCCESS))
                            iDataCallback.onSuccess(res);
                        else
                            iDataCallback.onError(CodeInfo.ADD_FAILDE,Integer.valueOf(res.code));
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
    public void getTextbookChapter(String pageNum, String accountId,final IDataCallback<Elements<Chapter>> iDataCallback) {
        try {
            Map<String,String> param = new BaseMap().initMap();
            param.put("pageNum",pageNum);
            param.put("accountId",new DESCoder(CoderConfig.CODER_CODE).encrypt(accountId));
            OkHttpUtils.get().url(Urls.URL_GETCHAPTER).params(param).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator())
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
                        MData<Elements<Chapter>> res = JsonUtil.fromJson(response,new TypeToken<MData<Elements<Chapter>>>(){}.getType());
                        if(res.code.equals(Constants.CODE_SUCCESS))
                            iDataCallback.onSuccess(res.result);
                        else
                            iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
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
    public void getPrepareLessons(String chapterId, String accountId, String textBookId, String pageNum, final IDataCallback<Domine> iDataCallback) {
        try {
            Map<String,String> param = new BaseMap().initMap();
            param.put("accountId",new DESCoder(CoderConfig.CODER_CODE).encrypt(accountId));
            param.put("chapterId",new DESCoder(CoderConfig.CODER_CODE).encrypt(chapterId));
            param.put("teachBookId",new DESCoder(CoderConfig.CODER_CODE).encrypt(textBookId));
            param.put("pageNum",pageNum);
            OkHttpUtils.get().url(Urls.URL_GETLESSONS).params(param).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator())
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
                        MData<LessonsList> res = JsonUtil.fromJson(response,new TypeToken<MData<LessonsList>>(){}.getType());
                        if(res.code.equals(Constants.CODE_SUCCESS))
                            iDataCallback.onSuccess(res.result);
                        else
                            iDataCallback.onError(CodeInfo.REQUEST_FAILDE,Integer.valueOf(res.code));
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
