package com.dafa.qipai.dafaqipai.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GengXinActivity extends BaseActivity {


    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.no)
    TextView no;
    @BindView(R.id.yes)
    TextView yes;
    @BindView(R.id.bg)
    RelativeLayout bg;

    private String url;
    private String remarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_gengxin);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        url = getIntent().getExtras().getString("url");
        remarks = getIntent().getExtras().getString("remarks");

        text.setText(remarks);

    }

    @OnClick({R.id.close, R.id.no, R.id.yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.no:
                finish();
                break;
            case R.id.yes:

                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(GengXinActivity.this, "请安装浏览器", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
