package com.hanboard.teacher.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hanboard.teacher.R;
import com.hanboard.teacher.common.view.DowloadDialog;
import com.hanboard.teacher.entity.Version;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.picasso.Picasso;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class VersionActivity extends Activity {

    @BindView(R.id.v_img)
    ImageView vImg;
    @BindView(R.id.v_name)
    TextView vName;
    @BindView(R.id.v_size)
    TextView vSize;
    @BindView(R.id.v_date)
    TextView vDate;
    @BindView(R.id.v_des)
    TextView vDes;
    @BindView(R.id.v_cancel)
    TextView vCancel;
    @BindView(R.id.v_confirm)
    TextView vConfirm;
    //跟新的下载地址
    private String dowloadUrl;
    //下载进度条
    private DowloadDialog dowload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        ButterKnife.bind(this);
        initDisplay();
        Intent i = getIntent();
        Version v = (Version) i.getSerializableExtra("version");
        Picasso.with(this).load(v.versionImg).into(vImg);
        vName.setText(v.versionName);
        vSize.setText(v.apkSize);
        vDate.setText(v.versionDate);
        vDes.setText(v.versionDes);
        dowloadUrl = v.apkUrl;
    }

    /*初始化屏幕*/
    private void initDisplay() {
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = (int) (d.getHeight() * 0.7);
        p.width = (int) (d.getWidth() * 0.6);
        getWindow().setAttributes(p);
    }

    @OnClick({R.id.v_cancel, R.id.v_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_cancel:
                finish();
                break;
            case R.id.v_confirm:
                dowload = new DowloadDialog(this, "正在更新，请稍等...");
                OkHttpUtils.get(dowloadUrl)
                        .tag(this)
                        .execute(new FileCallback("/sdcard/temp/Hanboard/CourseWare/", getString(R.string.app_name) + ".apk") {
                            @Override
                            public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                                dowload.dismiss();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.fromFile(new File(file.getAbsolutePath())), "application/vnd.android.package-archive");
                                startActivity(intent);
                            }

                            @Override
                            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                                dowload.setPercent(Math.round(Math.round(progress * 10000) * 1.0f / 100));
                            }

                            @Override
                            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                                super.onError(isFromCache, call, response, e);
                                dowload.dismiss();
                                Toast.makeText(VersionActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }
}
