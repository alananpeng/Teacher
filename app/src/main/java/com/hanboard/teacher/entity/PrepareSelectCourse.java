package com.hanboard.teacher.entity;

/**
 * Created by Administrator on 2016/8/9.
 */
public class PrepareSelectCourse extends Domine {


    @Override
    public String toString() {
        return "PrepareSelectCourse{" +
                "teachBookId='" + teachBookId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", suitObjectName='" + suitObjectName + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }

    /**
     * id : ab75d48eee114d26b752d8f521290ac1
     * teachBookId : 087ff757ca46407e9e01062d3233f8b4
     * accountId : 45895eef98c911e582030090f5e1dfdb
     * subjectName : 语文
     * suitObjectName : 0～2 岁
     * sectionName : 学前教育
     * versionName : 人教版
     * publishDate : 2016-08-04
     */


    private String teachBookId;
    private String accountId;
    private String subjectName;
    private String suitObjectName;
    private String sectionName;
    private String versionName;
    private String publishDate;
    public String getTeachBookId() {
        return teachBookId;
    }

    public void setTeachBookId(String teachBookId) {
        this.teachBookId = teachBookId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSuitObjectName() {
        return suitObjectName;
    }

    public void setSuitObjectName(String suitObjectName) {
        this.suitObjectName = suitObjectName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
