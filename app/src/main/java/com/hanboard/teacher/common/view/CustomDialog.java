package com.hanboard.teacher.common.view;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.hanboard.teacher.R;

/**
 * 项目名称：TeacherHD
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/8 0008 11:43
 */
public class CustomDialog extends Dialog{

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }


    public static class Builder  implements View.OnClickListener{
        private LeaveDialogListener leaveDialogListener;
        private Context context;
        private TextView mTitle;
        private TextView mMessage;
        private TextView mCanale;
        private TextView mConfirm;
        private String title;
        private String message;
        public Builder(Context context,String title,String message,LeaveDialogListener leaveDialogListener) {
            this.context = context;
            this.title = title;
            this.message = message;
            this.leaveDialogListener = leaveDialogListener;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog, null);
            dialog.addContentView(layout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dialog.setContentView(layout);
            mTitle = (TextView)layout.findViewById(R.id.dialog_title);
            mMessage = (TextView)layout.findViewById(R.id.dialog_message);
            mCanale = (TextView)layout.findViewById(R.id.dialog_cancel);
            mConfirm = (TextView)layout.findViewById(R.id.dialog_confirm);
            mTitle.setText(title);
            mMessage.setText(message);
            mCanale.setOnClickListener(this);
            mConfirm.setOnClickListener(this);
            return dialog;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.dialog_cancel:
                    leaveDialogListener.cancel(v);
                    break;
                case R.id.dialog_confirm:
                    leaveDialogListener.confirm(v);
                    break;
            }
        }

        public interface LeaveDialogListener{
            void cancel(View v);
            void confirm(View v);
        }
    }
}
