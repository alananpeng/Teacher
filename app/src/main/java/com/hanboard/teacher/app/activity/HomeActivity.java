package com.hanboard.teacher.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hanboard.teacher.R;
import com.hanboard.teacher.common.base.BaseActivity;
import com.hanboard.teacher.common.callback.UpdateCallback;
import com.hanboard.teacher.common.tools.VersionUtils;
import com.hanboard.teacher.entity.Version;
import com.hanboard.teacher.model.IVersionModel;
import com.hanboard.teacher.model.impl.VersionModelImpl;
import com.hanboard.teacherhd.lib.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements UpdateCallback, AdapterView.OnItemClickListener {
    //左边菜单栏
    @BindView(R.id.activity_home_leftMenu)
    ListView mListMenu;
    //检测登录跟新的接口
    private IVersionModel iVersionModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        iVersionModel = new VersionModelImpl();
        checkUpdate();
        //设置菜单的点击
        mListMenu.setOnItemClickListener(this);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected void handler(Message msg) {

    }

    private void checkUpdate() {
        iVersionModel.checkVersion(this);
    }
    /////////////////////////////////////////////////////

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtils.showShort(me, "点击了菜单" + i);
    }

    //跟新版本接口
    @Override
    public void onVersion(Version version) {
        int versionCode = VersionUtils.getVersionCode(me);
        if (versionCode < version.versionCode) {
            Intent intent = new Intent();
            intent.putExtra("version", version);
            intent.setClass(this, VersionActivity.class);
            startActivity(intent);
        }
    }
}
