package com.hanboard.teacher.common.callback;


import com.hanboard.teacher.entity.Version;

/**
 * 项目名称：TeacherHD1.0
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/13 0013 14:38
 */
public interface UpdateCallback {
    /**
     * 新版本
     */
    void onVersion(Version version);

}
