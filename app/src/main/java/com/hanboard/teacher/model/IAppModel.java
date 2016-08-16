package com.hanboard.teacher.model;

import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.entity.Banner;

import java.util.List;

/**
 * 项目名称：TeacherHD1.0
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/12 0012 11:24
 */
public interface IAppModel {
    /**获取APP_BANNER*/
    void getBanner(IDataCallback<List<Banner>> iDataCallback);
}
