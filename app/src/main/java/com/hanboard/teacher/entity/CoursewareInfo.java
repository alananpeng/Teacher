package com.hanboard.teacher.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public class CoursewareInfo extends Domine{


    /**
     * contentTitle : null
     * contentObject : null
     * createTime : 123124
     * updateTime : 12345678
     * courseHour : 1
     * accountId : null
     */

    private String contentTitle;
    private String contentObject;
    private long createTime;
    private long updateTime;
    private int courseHour;
    private String accountId;
    public LessonPlan lessonPlan;
    public List<Exercises> exercises;
    public List<CourseWare> courseWares;

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentObject() {
        return contentObject;
    }

    public void setContentObject(String contentObject) {
        this.contentObject = contentObject;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
