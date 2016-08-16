package com.hanboard.teacher.common.callback;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class JsonCallback<T> extends CommonCallback<T> {
    private Class<T> clazz;
    private Type type;
    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }
    public JsonCallback(Type type) {
        this.type = type;
    }
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String responseData = response.body().string();
        if (TextUtils.isEmpty(responseData)) throw new IllegalStateException("服务器异常"); ;
        JSONObject jsonObject = new JSONObject(responseData);
        final String msg = jsonObject.optString("message", "");
        final String code = jsonObject.optString("code", "");
        String data = jsonObject.optString("data", "");
        switch (code) {
            case "200":
                if (clazz == String.class) return (T) data;
                if (clazz != null) return new Gson().fromJson(data, clazz);
                if (type != null) return new Gson().fromJson(data, type);
                break;
            case "30010":
                throw new IllegalStateException("上传失败");
            default:
                throw new IllegalStateException("错误代码：" + code + "，错误信息：" + msg);
        }
        return null;
    }
}