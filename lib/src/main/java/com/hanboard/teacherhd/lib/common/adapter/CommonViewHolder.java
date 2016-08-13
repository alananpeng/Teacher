package com.hanboard.teacherhd.lib.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


public class CommonViewHolder {
    /**
     *
     */
    private View mContentView;

    /**
     * 构造函数
     *
     * @param context Context
     * @param layoutId ListView、GridView或者其他AbsListVew子类的 Item View的资源布局id
     */
    protected CommonViewHolder(Context context, ViewGroup parent, int layoutId) {
        mContentView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mContentView.setTag(this);
    }

    /**
     * 获取CommonViewHolder，当convertView为空的时候从布局xml装载item view,
     * 并且将该CommonViewHolder设置为convertView的tag, 便于复用convertView.
     */
    public static CommonViewHolder getViewHolder(Context context, View convertView,
                                                 ViewGroup parent, int layoutId) {

        context = (context == null && parent != null) ? parent.getContext() : context;
        CommonViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new CommonViewHolder(context, parent, layoutId);
        } else {
            viewHolder = (CommonViewHolder) convertView.getTag();
        }

        // ViewFinder.initContentView(viewHolder.getContentView());

        return viewHolder;
    }

    public View getContentView() {
        return mContentView;
    }

    public void setTextForTextView(int textViewId, CharSequence text) {
        TextView textView = ViewFinder.findViewById(mContentView, textViewId);
        if (textView != null) {
            textView.setText(text);

        }
    }



    /**
     * 为ImageView设置图片
     */
    public void setImageForView(int imageViewId, int drawableId) {
        ImageView imageView = ViewFinder.findViewById(mContentView, imageViewId);
        if (imageView != null) {
            imageView.setImageResource(drawableId);
        }
    }

    /**
     * 为ImageView设置图片
     */
    public void setImageForView(int imageViewId, Bitmap bmp) {
        ImageView imageView = ViewFinder.findViewById(mContentView, imageViewId);
        if (imageView != null) {
            imageView.setImageBitmap(bmp);
        }
    }

    /**
     * 为CheckBox设置是否选中
     */
    public void setCheckForCheckBox(int checkViewId, boolean isCheck) {
        CheckBox checkBox = ViewFinder.findViewById(mContentView, checkViewId);
        if (checkBox != null) {
            checkBox.setChecked(isCheck);
        }
    }

    public void setVisibility(int viewId, int visibility) {
        View view = ViewFinder.findViewById(mContentView, viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    public void setOnClickListener(int viewId, OnClickListener listener) {
        View view = ViewFinder.findViewById(mContentView, viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public void setOnTouchListener(int viewId, OnTouchListener listener) {
        View view = ViewFinder.findViewById(mContentView, viewId);
        if (view != null) {
            view.setOnTouchListener(listener);
        }
    }
}
