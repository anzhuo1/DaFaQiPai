package com.dafa.qipai.dafaqipai.youxi;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
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
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.kongzue.dialog.v2.SelectDialog;
import com.lzy.okgo.OkGo;

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

public class TiyuWebViewActivity extends BaseYouxiActivity {


    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.stateLayout)
    StateLayout stateLayout;


    private String mUrl;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        mWebView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        mWebView.setBackgroundResource(R.color.appbg);

        stateLayout.showProgressView();


        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");

        settings.setLoadsImagesAutomatically(true);

        settings.setDomStorageEnabled(true);                     //开启DOM storage API功能


        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {


                super.onProgressChanged(view, newProgress);


            }


            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            //  android 3.0以下：用的这个方法
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // android 3.0以上，android4.0以下：用的这个方法
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //android 4.0 - android 4.3  安卓4.4.4也用的这个方法
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType,
                                        String capture) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //android4.4 无方法。。。
            // Android 5.0及以上用的这个方法
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]>
                    filePathCallback, FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }


        });
        // 这行代码一定加上否则效果不会出现
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                stateLayout.showContentView();


            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {


//handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();

//handleMessage(Message msg); 其他处理
            }

            //是否在webview内加载页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }

                return true;
            }


        });


        OkGo.post(ApiConstant.API_DOMAIN + "/sport/getSportUrl2.json")
                .tag(this)
                .params("clientType", 3)
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(TiyuWebViewActivity.this, false) {
                    @Override
                    protected void _onNext(String json) {
                        Qipai qipai = GsonUtil.GsonToBean(json, Qipai.class);
                        String loginUrl = qipai.getLoginUrl();

                        mWebView.loadUrl(loginUrl);

                    }

                });


//        mWebView.loadUrl("https://chat7.livechatvalue.com/chat/chatClient/chatbox.jsp?companyID=763215&configID=54563&jid=1035408947");

    }

    @Override
    public void onBackPressed() {
        webViewGoBack();
    }

    /**
     * 内置浏览器能否返回。
     */
    private void webViewGoBack() {
        if (mWebView.canGoBack()) { //从界面创建后到现在，有历史记录可以返回
            mWebView.goBack();
            tuiChu();
        } else {
            tuiChu();   //没有历史记录直接退出
        }
    }


    private void tuiChu() {


        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                .params("clientType", "Android")
                .params("type", 4)
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

                            SelectDialog.show(TiyuWebViewActivity.this, "提示", "退出失败", "重试", new DialogInterface.OnClickListener() {
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


    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;


    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"),
                FILE_CHOOSER_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }

}
