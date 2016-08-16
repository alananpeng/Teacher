package com.hanboard.teacher.entity;

/**
 * Created by Administrator on 2016/8/11.
 */
public class BookAndChapterId {
    private  String textBookId;
    private String chapterId;

    public BookAndChapterId(String textBookId, String chapterId) {
        this.textBookId = textBookId;
        this.chapterId = chapterId;
    }

    public String getTextBookId() {
        return textBookId;
    }

    public void setTextBookId(String textBookId) {
        this.textBookId = textBookId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}
