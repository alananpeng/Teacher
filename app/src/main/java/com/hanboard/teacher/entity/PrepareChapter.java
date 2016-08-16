package com.hanboard.teacher.entity;

/**
 * Created by Administrator on 2016/8/10.
 */
public class PrepareChapter extends Domine{

    public PrepareContent content;
    /**
     * chapterId : b766cf7a13634258934d901d612e4f0d
     * contentId : 02418kwyi3fsd654fsa14f41f54sdf45
     * teachBookId : 087ff757ca46407e9e01062d3233f8b4
     * title : 语文u
     * createTime : 1470729223000
     */

    private String chapterId;
    private String contentId;
    private String teachBookId;
    private String title;
    private long createTime;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTeachBookId() {
        return teachBookId;
    }

    public void setTeachBookId(String teachBookId) {
        this.teachBookId = teachBookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
