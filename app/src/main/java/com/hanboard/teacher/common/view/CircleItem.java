package com.hanboard.teacher.common.view;

import android.view.View;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/28 0028 14:58
 */
public class CircleItem implements Comparable<CircleItem>{
    public View view;
    public float distance; //离屏幕的距离

    @Override
    public int compareTo(CircleItem another) {
        //根据离屏幕的距离倒序排序
        return distance - another.distance < 0 ? 1 : -1;
    }
}
