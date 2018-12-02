package com.dafa.qipai.dafaqipai.youxi;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoTuiChu;
import com.dafa.qipai.dafaqipai.bean.Qipai;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.kongzue.dialog.v2.SelectDialog;
import com.lzy.okgo.OkGo;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-11-07 14:43                                                 *
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：加载url
 * <p>
 * *******************************************************************************************
 */

public class BBINWebViewActivity extends BaseYouxiActivity {

    @BindView(R.id.statelayout)
    StateLayout statelayout;
//    @BindView(R.id.forum_context)
//    com.tencent.smtt.sdk.WebView forumContext;


    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewapp2);
        ButterKnife.bind(this);


        url = getIntent().getStringExtra("url");


        forumContext.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {

            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String s) {
                super.onPageFinished(webView, s);

                statelayout.showContentView();
            }
        });

        loadData();


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


        OkGo.post(ApiConstant.API_DOMAIN + "/wallet/oneKeyToWallet.json")
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, true) {
                    @Override
                    protected void _onNext(String json) {

                        DoTuiChu doTuiChu = GsonUtil.GsonToBean(json, DoTuiChu.class);
                        int result = doTuiChu.getResult();

                        if (result == 1) {
                            forumContext.destroy();
                            finish();

                        } else {


                            BaseDialog dialog = new BaseDialog(BBINWebViewActivity.this, "退出失败","取消","重试") {

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


}
