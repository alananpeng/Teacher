package com.hanboard.teacher.entity;


/**
 * 课件表
 * Created by Administrator on 2016/7/21 0021.
 */
public class CourseWare extends Domine{

    public String courseWareTitle;

    public String courseWareUrl;

    public String courseWareType;

    public String contentId;

    public CourseWare(String courseWareTitle, String contentId, String courseWareType, String courseWareUrl) {
        this.courseWareTitle = courseWareTitle;
        this.contentId = contentId;
        this.courseWareType = courseWareType;
        this.courseWareUrl = courseWareUrl;
    }
}
