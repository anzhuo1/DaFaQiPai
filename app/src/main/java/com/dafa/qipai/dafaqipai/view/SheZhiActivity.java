package com.dafa.qipai.dafaqipai.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.kongzue.dialog.v2.SelectDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SheZhiActivity extends BaseActivity {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.nicheng)
    TextView nicheng;
    @BindView(R.id.id_nun)
    TextView idNun;
    @BindView(R.id.qiehuan)
    TextView qiehuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_shezhi);
        ButterKnife.bind(this);
        AutoUtils.auto(this);

        nicheng.setText(MyApp.NICHENG);
        idNun.setText(MyApp.ID);

    }


    @OnClick({R.id.close, R.id.qiehuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.qiehuan:

                tuichu();

                break;
        }
    }


    private void tuichu() {

        SelectDialog.show(SheZhiActivity.this, "提示", "确定退出吗", (dialog, which) -> {

            UserUtil.loginOut(SheZhiActivity.this);
            finish();

            ActivityContainer.getInstance().finishAllActivity();

            Intent intent = new Intent(SheZhiActivity.this, WelcomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        });

    }

}
