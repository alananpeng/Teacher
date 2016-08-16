package com.hanboard.teacher.model;


import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.entity.Chapter;
import com.hanboard.teacher.entity.Domine;
import com.hanboard.teacher.entity.Elements;
import com.hanboard.teacher.entity.TextBook;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/5 0005 16:43
 */
public interface ISelectTextBookModel {
    /*获取所有科目*/
    void getAllSubject(IDataCallback<Domine> iDataCallback);
    /**获取所有学段*/
    void getAllSuitObject(IDataCallback<Domine> iDataCallback);
    /**根据科目ID，年级ID获取教材*/
    void getTextbookBySubIdAndSuitID(String subjectId, String suitObjectId, IDataCallback<Domine> iDataCallback);
    /**添加教材至教师账户*/
    void addTextbook(String accountId, TextBook textBook, IDataCallback<Domine> iDataCallback);
    /*获取教材章节*/
    void getTextbookChapter(String pageNum, String accountId, IDataCallback<Elements<Chapter>> iDataCallback);
    /*通过章节id 账号id 课本id获取该章节下所有备课信息*/
    void getPrepareLessons(String chapterId, String accountId, String textBookId, String pageNum, IDataCallback<Domine> iDataCallback);
}
