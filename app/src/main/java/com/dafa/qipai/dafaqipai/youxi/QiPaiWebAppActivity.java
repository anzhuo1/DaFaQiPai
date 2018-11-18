package com.dafa.qipai.dafaqipai.youxi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoTuiChu;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.DragView;
import com.dafa.qipai.dafaqipai.wihget.MoveView;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.kongzue.dialog.v2.SelectDialog;
import com.lzy.okgo.OkGo;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QiPaiWebAppActivity extends BaseYouxiActivity {


    @BindView(R.id.statelayout)
    StateLayout statelayout;
    @BindView(R.id.forum_context)
    WebView forumContext;
    @BindView(R.id.fanhui)
    MoveView fanhui;


    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewapp2);
        ButterKnife.bind(this);


        url = getIntent().getStringExtra("url");


        forumContext.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

                statelayout.showContentView();
            }
        });

        loadData();


        //fanhui.setImageResource(R.mipmap.fanhui);

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void loadData() {


        statelayout.showProgressView();


        forumContext.loadUrl(url);

    }


    @Override
    public void onBackPressed() {
        webViewGoBack();

//

    }

    /**
     * 内置浏览器能否返回。
     */
    private void webViewGoBack() {


        tuiChu();


    }

    private void tuiChu() {


        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                .params("clientType", "Android")
                .params("type", 1)
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, true) {
                    @Override
                    protected void _onNext(String json) {

                        DoTuiChu doTuiChu = GsonUtil.GsonToBean(json, DoTuiChu.class);
                        int result = doTuiChu.getResult();

                        if (result == 1) {

                            finish();

                        } else {

                            SelectDialog.show(QiPaiWebAppActivity.this, "提示", "退出失败", "重试", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tuiChu();
                                }
                            }, "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });

                        }

                    }
                });


    }


    @OnClick(R.id.fanhui)
    public void onViewClicked() {

        BaseDialog dialog = new BaseDialog(this) {
            @Override
            public void mustDoThing(Dialog mDialog) {

            }

            @Override
            public void mustDoThingOnCancle(Dialog mDialog) {

            }
        };
        dialog.show();
    }
}
