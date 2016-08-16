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
import com.hanboard.teacherhd.lib.ui.numberprogressbar.NumberProgressBar;
import com.hanboard.teacherhd.lib.ui.numberprogressbar.OnProgressBarListener;

/**
 * Created by Administrator on 2016/8/8.
 */
public class DowloadDialog implements OnProgressBarListener {
    private Context context;
    private String content;
    private Dialog dialog;
    private NumberProgressBar mProgressBar;
    public DowloadDialog(Context context, String content){
        this.context = context;
        this.content = content;
        dialog();
    }
    public void dialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_downloaddialog, null);
        TextView textView= (TextView) view.findViewById(R.id.dialog_text);
        textView.setText(content);
        mProgressBar= (NumberProgressBar) view.findViewById(R.id.progress);
        dialog = new AlertDialog.Builder(context, R.style.Dialog).create();
        dialog.setCancelable(false);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity( Gravity.CENTER);
        dialog.show();
        dialog.getWindow().setContentView(view);
    }
    public void setPercent(int progress){
        mProgressBar.setProgress(progress);
    }
    public void showDialog(){
        dialog.show();
    }
    public void dismiss(){
        dialog.dismiss();
    }

    @Override
    public void onProgressChange(int current, int max) {

    }
}
