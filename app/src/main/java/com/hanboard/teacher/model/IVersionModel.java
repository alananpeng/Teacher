package com.hanboard.teacher.model;
import com.hanboard.teacher.common.callback.UpdateCallback;
/**
 * 项目名称：TeacherHD1.0
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/13 0013 14:12
 */
public interface IVersionModel {
    /*检查版本更新*/
    void checkVersion(UpdateCallback updateCallback);
}
