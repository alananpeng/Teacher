package com.hanboard.teacher.common.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanboard.teacher.common.view.LoadingDialog;

import butterknife.ButterKnife;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/22 0022 10:38
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
    public View rootView;
    private LoadingDialog loadingDialog;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        rootView  = initView(inflater,container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    public View getRootView(){
        return rootView;
    }
    protected abstract View initView(LayoutInflater inflater,ViewGroup container);
    protected abstract void initData();
    /**
     * 显示progress对话框
     */
    protected void showProgress(final String msg) {
        loadingDialog = new LoadingDialog(context,msg);
    }

    /**
     * 取消progress对话框
     */
    protected void disProgress() {
        loadingDialog.dismiss();
    }
}
