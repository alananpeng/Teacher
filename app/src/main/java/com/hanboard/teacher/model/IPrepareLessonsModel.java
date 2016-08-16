package com.hanboard.teacher.model;


import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.entity.Domine;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/26 0026 14:21
 */
public interface IPrepareLessonsModel {
    /**
     * 获取课程版本列表
     */
    void getChapterList(String tid, String vid, String sid, String key, IDataCallback<Domine> iDataCallback);
    void getChapterDetials(String accountId, String chapterId, String teachBookId, String pageNum, IDataCallback<Domine> iDataCallback);
}
