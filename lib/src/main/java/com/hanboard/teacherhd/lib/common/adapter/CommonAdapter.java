package com.hanboard.teacherhd.lib.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {

    /**
     * Context
     */
    public Context mContext;
    /**
     * 要展示的数据列表
     */
    public List<T> mData;
    /**
     * 每一项的布局id
     */
    private int mItemLayoutId = -1;

    /**
     * @param context Context
     * @param itemLayoutResId
     * @param dataSource 数据源
     */
    public CommonAdapter(Context context, int itemLayoutResId, List<T> dataSource) {
        checkParams(context, itemLayoutResId, dataSource);
        mContext = context;
        mItemLayoutId = itemLayoutResId;
        mData = dataSource;
    }

    /**
     * 检查参数的有效性
     *
     * @param context
     * @param itemLayoutResId
     * @param dataSource
     */
    private void checkParams(Context context, int itemLayoutResId, List<T> dataSource) {
        if (context == null || itemLayoutResId < 0 || dataSource == null) {
            throw new RuntimeException("context == null || itemLayoutResId < 0 || dataSource == null,请检查你的参数是否正确");
        }
    }

    /**
     * 返回数据的总数
     */
    @Override
    public int getCount() {
        return mData.size();
    }
    /**
     * 返回position位置的数据
     */
    @Override
    public T getItem(int position) {
        return mData.get(position);
    }
    /**
     * item id, 返回position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 返回position位置的view, 即listview、gridview的第postion个view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取ViewHolder
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(mContext, convertView, parent,mItemLayoutId);
        // 填充数据
        fillItemData(viewHolder, position, getItem(position));
        // 返回convertview
        return viewHolder.getContentView();
    }
    /**
     * 覆写该方法来讲数据填充到视图中
     */
    protected abstract void fillItemData(CommonViewHolder viewHolder, int position, T item);

}