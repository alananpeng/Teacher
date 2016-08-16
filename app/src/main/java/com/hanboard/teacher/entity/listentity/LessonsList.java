package com.hanboard.teacher.entity.listentity;

import com.hanboard.teacher.entity.Domine;
import com.hanboard.teacher.entity.Lessons;

import java.util.List;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/9 0009 12:46
 */
public class LessonsList extends Domine {
    public List<Lessons> elements;
    public int number;
    public int size;
    public int totalElements;
    public int displayEndNum;
    public boolean first;
    public boolean last;
    public int numberOfElements;
    public int displayStartNum;
    public int totalPages;
    public String subject;
}
