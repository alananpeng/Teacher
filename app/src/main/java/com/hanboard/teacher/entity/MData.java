package com.hanboard.teacher.entity;

import java.io.Serializable;

/**
 * 项目名称：TeacherHD
 * 类描述：数据交互模型
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/20 0020 17:53
 */
public class MData<T> implements Serializable {
    /**
     * 说明
     */
    public String message;
    /**
     * 代码
     */
    public String code;
    /**
     * 结果
     */
    public T result;
}
