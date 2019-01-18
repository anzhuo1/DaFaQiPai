package com.dafa.qipai.dafaqipai.youxi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoTuiChu;
import com.dafa.qipai.dafaqipai.bean.DoZhenren;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.EncodingUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.kongzue.dialog.v2.SelectDialog;
import com.lzy.okgo.OkGo;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class AGWebAppActivity extends BaseYouxiActivity {


    @BindView(R.id.statelayout)
    StateLayout statelayout;
//    @BindView(R.id.forum_context)
//    com.tencent.smtt.sdk.WebView forumContext;


    private String url;
    private String url2;
    private String key;
    private String params;
    boolean isOnPause;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewapp2);
        ButterKnife.bind(this);


        forumContext.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
        forumContext.setBackgroundResource(R.color.appbg);

        statelayout.showProgressView();



        url = getIntent().getStringExtra("url");
        params = getIntent().getStringExtra("params");
        key = getIntent().getStringExtra("key");


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


        StringBuilder builder1 = new StringBuilder();
        try {//拼接post提交参数
            builder1.append("key=").append(key).append("&")
                    .append("params=").append(URLEncoder.encode(params, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String postData = builder1.toString();

        forumContext.postUrl(url, EncodingUtils.getBytes(postData, "UTF-8"));
        //forumContext.loadUrl(url);

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


//        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
//                .params("token", UserUtil.getToken(context))
//                .params("uid", UserUtil.getUserID(context))
//                .execute(new OkGoCallBack(this, true) {
//                    @Override
//                    protected void _onNext(String json) {
//
//                        DoTuiChu doTuiChu = GsonUtil.GsonToBean(json, DoTuiChu.class);
//                      int result = doTuiChu.getResult();
//
//
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//
//                        forumContext.destroy();
//                        finish();
//
//                    }
//                });



        forumContext.destroy();
        finish();

    }


}
