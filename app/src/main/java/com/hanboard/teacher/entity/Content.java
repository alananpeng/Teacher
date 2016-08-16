package com.hanboard.teacher.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class Content{

    public String contentTitle;

    public String contentObject;

    public Date createTime;

    public Date updateTime;

    public byte courseHour;

    public String accountId;

    public LessonPlan lessonPlan;

    public List<Exercises> exercises;

    public List<CourseWare> courseWares;

}
