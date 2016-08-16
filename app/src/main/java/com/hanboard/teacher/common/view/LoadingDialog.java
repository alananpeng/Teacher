package com.hanboard.teacher.common.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hanboard.teacher.R;

/**
 * Created by Administrator on 2016/6/17.
 */
public class LoadingDialog {
    private Context context;
    private String content;
    private Dialog dialog;
    public LoadingDialog(Context context, String content){
        this.context = context;
        this.content = content;
        dialog();
    }
    public void dialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.loading_dialog, null);
        TextView lodingTextView = (TextView) view.findViewById(R.id.loading_textview);
        lodingTextView.setText(content);
        dialog = new AlertDialog.Builder(context,R.style.loading_dialog).create();
        dialog.setCancelable(false);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity( Gravity.TOP);
        dialog.show();
        dialog.getWindow().setContentView(view);
    }
    public void dismiss(){
        dialog.dismiss();
    }
}
