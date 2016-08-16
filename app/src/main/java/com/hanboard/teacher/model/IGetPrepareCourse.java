package com.hanboard.teacher.model;


import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.entity.Elements;
import com.hanboard.teacher.entity.PrepareSelectCourse;

/**
 * Created by Administrator on 2016/8/9.
 */
public interface IGetPrepareCourse {
   void  getPrepareCourse(String accountId, String pageNum, IDataCallback<Elements<PrepareSelectCourse>> elementsIDataCallback);
}
