package com.hanboard.teacher.model;

import android.content.Context;

import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.entity.Account;
import com.hanboard.teacher.entity.Domine;

/**
 * Created by Administrator on 2016/8/3.
 */
public interface ILoginModel {
    /*用户登录*/
    void doLogin(Account account, IDataCallback<Domine> iDataCallback, Context context);
}
