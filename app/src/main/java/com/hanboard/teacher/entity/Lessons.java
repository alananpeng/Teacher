package com.hanboard.teacher.entity;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/9 0009 12:42
 */
public class Lessons extends Domine {

    public String chapterId;
    public String contentId;
    public String teachBookId;
    public String title;
    public long createTime;
    public ContentBean content;

    public static class ContentBean {
        public String id;
        public String contentTitle;
        public String contentObject;
        public long createTime;
        public String updateTime;
        public int courseHour;
        public String accountId;
        public String lessonPlan;
        public String exercises;
        public String courseWares;
    }
}
