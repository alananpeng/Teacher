package com.hanboard.teacher.entity;

import java.util.List;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/8 0008 18:07
 */
public class Elements<T> extends Domine {
    public List<T> elements;
    public int number;
    public int size;
    public boolean first;
    public boolean last;
    public int numberOfElements;
    public int displayStartNum;
    public int totalPages;
    public int totalElements;
    public int displayEndNum;
}
