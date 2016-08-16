package com.hanboard.teacher.app.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.EditText;

import com.hanboard.teacher.R;
import com.hanboard.teacher.common.base.BaseActivity;
import com.hanboard.teacher.common.callback.IDataCallback;
import com.hanboard.teacher.common.view.ClearEditText;
import com.hanboard.teacher.entity.Account;
import com.hanboard.teacher.entity.Domine;
import com.hanboard.teacher.model.ILoginModel;
import com.hanboard.teacher.model.impl.LoginModelImpl;
import com.hanboard.teacherhd.lib.common.utils.SharedPreferencesUtils;
import com.hanboard.teacherhd.lib.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IDataCallback<Domine> {
    //密码输入框
    @BindView(R.id.edt_loging_pwd)
    EditText mEdtLogingPwd;
    //账号输入框
    @BindView(R.id.edt_loging_username)
    ClearEditText mEdtLogingUsername;
    //登录的逻辑接口
    private ILoginModel loginModel;
    //用来暂时存储账号密码的变量
    String accountName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initEditText();
    }
    ////////////////////////////////////////

    /**
     * 从SharePreference里面获取用户信息(用户初始化输入框,
     * 如果从SharePreference里面读取到已经存过的账号密码就直接初始化在输入框里面)
     */
    private void initEditText() {
        accountName = (String) SharedPreferencesUtils.getParam(me, "accountName", "null");
        password = (String) SharedPreferencesUtils.getParam(me, "password", "null");
        loginModel = new LoginModelImpl();
        if (!accountName.equals("null") || !password.equals("null")) {
            mEdtLogingUsername.setText(accountName);
            mEdtLogingPwd.setText(password);
        }
    }

    /**
     * 登录的具体操作
     */
    private void doLogin() {
        showProgress("正在登录");
        Account account = new Account();
        account.accountName = accountName;
        account.password = password;
        if (accountName.equals("null") || password.equals("null")) {
            account.accountName = mEdtLogingUsername.getText().toString().trim();
            account.password = mEdtLogingPwd.getText().toString().trim();
        }
        loginModel.doLogin(account, this, this);
    }

    ////////////////////////////////////

    /**
     * 登录请求后的数据回调
     *
     * @param data 返回的是一个account数据
     */
    @Override
    public void onSuccess(Domine data) {
        disProgress();
        if (data instanceof Account) {
            SharedPreferencesUtils.setParam(me, "accountName", mEdtLogingUsername.getText().toString().trim());
            SharedPreferencesUtils.setParam(me, "password", mEdtLogingPwd.getText().toString().trim());
            SharedPreferencesUtils.setParam(me, "id", ((Account) data).id);
            SharedPreferencesUtils.setParam(me, "userImg", ((Account) data).avatarUrl);
            ToastUtils.showShort(me, "登录成功");
             startActivity(HomeActivity.class);
        }
        finish();

    }

    @Override
    public void onError(String msg, int code) {
        disProgress();
        ToastUtils.showShort(this, msg + "错误码" + code);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected void handler(Message msg) {

    }

    /**
     * 登录按钮的点击事件
     */
    @OnClick(R.id.btn_login)
    public void onClick() {
        doLogin();
    }
}
