package com.hanboard.teacher.entity;

/**
 * Created by Administrator on 2016/8/10.
 */
public class PrepareContent extends Domine{

    /**
     * contentTitle : 语文u
     * contentObject : 教授
     * createTime : 1470729223000
     * updateTime : null
     * courseHour : 1
     * accountId : null
     * lessonPlan : null
     * exercises : null
     * courseWares : null
     */

    private String contentTitle;
    private String contentObject;
    private long createTime;
    private String updateTime;
    private int courseHour;
    private String accountId;
    private String lessonPlan;
    private String exercises;
    private String courseWares;

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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    public String getLessonPlan() {
        return lessonPlan;
    }

    public void setLessonPlan(String lessonPlan) {
        this.lessonPlan = lessonPlan;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getCourseWares() {
        return courseWares;
    }

    public void setCourseWares(String courseWares) {
        this.courseWares = courseWares;
    }
}
