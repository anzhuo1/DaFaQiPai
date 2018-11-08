package com.dafa.qipai.dafaqipai.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class XiaoXiDetailActivity extends BaseActivity {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.fanhui)
    TextView fanhui;
    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.bg)
    RelativeLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_xiaoxi_detail);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        String content = getIntent().getExtras().getString("content");
        long createTime = getIntent().getExtras().getLong("createTime");


        tv.setText(content);


    }


    @OnClick({R.id.fanhui, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.finish:
                finish();
                break;
        }
    }
}
