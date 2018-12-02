package com.dafa.qipai.dafaqipai.youxi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoTuiChu;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.kongzue.dialog.v2.SelectDialog;
import com.lzy.okgo.OkGo;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeYouWebAppActivity extends BaseYouxiActivity {


    @BindView(R.id.statelayout)
    StateLayout statelayout;
    @BindView(R.id.forum_context)
    WebView forumContext;
//    @BindView(R.id.fanhui)
//    MoveView fanhui;
//    @BindView(R.id.dragView)
//    DragView dragView;


    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewapp2);
        ButterKnife.bind(this);



        url = getIntent().getStringExtra("url");


        loadData();


        //fanhui.setImageResource(R.mipmap.fanhui);

//        dragView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


        // dragView.setImageResource(R.drawable.fanhui);

    }


    private void loadData() {


        statelayout.showProgressView();


        forumContext.loadUrl(url);


        forumContext.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

                System.out.println(s + "乐友");
                statelayout.showContentView();
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                System.out.println(s + "乐友1");
                super.onPageStarted(webView, s, bitmap);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                System.out.println(s + "乐友2");

                return super.shouldOverrideUrlLoading(webView, s);
            }

        });


         forumContext.setWebChromeClient(new WebChromeClient(){
             @Override
             public void onCloseWindow(WebView webView) {
                 super.onCloseWindow(webView);
                 finish();
             }
         });

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


        BaseDialog dialog = new BaseDialog(this, "是否返回大厅？") {

            @Override
            public void btn1DoThing(Dialog mDialog) {
                mDialog.dismiss();
            }

            @Override
            public void btn2DoThing(Dialog mDialog) {
                mDialog.dismiss();
                tuiChu();
            }
        };
        dialog.show();


    }

    private void tuiChu() {


        OkGo.post(ApiConstant.API_DOMAIN + "/wallet/oneKeyToWallet.json")
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, true) {
                    @Override
                    protected void _onNext(String json) {

                        DoTuiChu doTuiChu = GsonUtil.GsonToBean(json, DoTuiChu.class);
                        int result = doTuiChu.getResult();

                        if (result == 1) {

                            finish();
                            forumContext.destroy();


                        } else {


                            BaseDialog dialog = new BaseDialog(LeYouWebAppActivity.this, "退出失败","取消","重试") {

                                @Override
                                public void btn1DoThing(Dialog mDialog) {
                                    mDialog.dismiss();
                                    forumContext.destroy();
                                    finish();
                                }

                                @Override
                                public void btn2DoThing(Dialog mDialog) {
                                    mDialog.dismiss();
                                    tuiChu();
                                }
                            };
                            dialog.show();

                        }

                    }
                });


    }


//    @OnClick(R.id.fanhui)
//    public void onViewClicked() {
//
//        BaseDialog dialog = new BaseDialog(this, "是否返回大厅？") {
//
//            @Override
//            public void btn1DoThing(Dialog mDialog) {
//
//            }
//
//            @Override
//            public void btn2DoThing(Dialog mDialog) {
//
//            }
//        };
//        dialog.show();
//    }
}
