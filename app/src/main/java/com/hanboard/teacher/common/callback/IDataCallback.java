package com.hanboard.teacher.common.callback;

/**
 * 项目名称：TeacherHD
 * 类描述：数据回调接口
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/20 0020 18:06
 */
public interface IDataCallback<T> {
    public void onSuccess(T data);
    public void onError(String msg, int code);
}
