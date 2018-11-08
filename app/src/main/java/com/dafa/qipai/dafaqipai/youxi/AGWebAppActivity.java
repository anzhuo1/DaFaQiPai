package com.dafa.qipai.dafaqipai.youxi;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoZhenren;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.EncodingUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.lzy.okgo.OkGo;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class AGWebAppActivity extends BaseYouxiActivity {


    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.stateLayout)
    StateLayout stateLayout;


    private String url;
    private String url2;
    boolean isOnPause;


    private BigDecimal tiyuYUeSportAmount = new BigDecimal("0");
    private BigDecimal qipaiYueChessAmount = new BigDecimal("0");
    private BigDecimal agYueChessAmount = new BigDecimal("0");
    private BigDecimal bbinYueChessAmount = new BigDecimal("0");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);


        webView.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
        webView.setBackgroundResource(R.color.appbg);

        stateLayout.showProgressView();

//
//
//
//        OkGo.post(ApiConstant.API_DOMAIN + "/chess/getAGAmount.json")
//                .tag(this)
//                .params("token", UserUtil.getToken(context))
//                .params("uid", UserUtil.getUserID(context))
//                .execute(new OkGoCallBack(this, false) {
//
//                    @Override
//                    protected void _onNext(String json) {
//
//                        DoZhenrenYue doZhenrenYue = GsonUtil.GsonToBean(json, DoZhenrenYue.class);
//
//                        agYueChessAmount = doZhenrenYue.getAgAmount();
//
//                        if (isFinishing()) {
//                            return;
//                        }
//
//                        if(agYueChessAmount.compareTo(new BigDecimal("0")) == 0){
//
//                            new ZhuceDialog(AGWebAppActivity.this, "温馨提示", "立即转换","继续进入" ,"您的AG视讯游戏额度不足\n\n请转换额度再进行游戏\n") {
//                                @Override
//                                public void mustDoThing(Dialog mDialog) {
//                                    mDialog.dismiss();
//
//                                    gotoActivity(ZhuanHuanActivity.class);
//                                }
//
//                                @Override
//                                public void mustDoThingOnCancle(Dialog mDialog) {
//
//                                    mDialog.dismiss();
//
//
//                                }
//                            }.show();
//
//                        }
//
//
//
//
//
//                    }
//                });


        url = getIntent().getStringExtra("url");


        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setLoadsImagesAutomatically(true);

        settings.setDomStorageEnabled(true);                     //开启DOM storage API功能


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {


                super.onProgressChanged(view, newProgress);


            }


        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                stateLayout.showContentView();
            }
        });


        loadData();


    }

    private void loadData() {
//        statelayout.showProgressView();

        OkGo.post(ApiConstant.API_DOMAIN + "/chess/getAGLoginUrl2.json")
                .tag(this)
                .params("clientType", "Android")
                .params("KindID", "")
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        DoZhenren qipai = GsonUtil.GsonToBean(json, DoZhenren.class);

                        if (qipai.getResult() == 1) {
//                            statelayout.showContentView();

                            String key = qipai.getKey();
                            String params = qipai.getParams();
                            String loginurl = qipai.getLoginUrl();


                            StringBuilder builder1 = new StringBuilder();
                            try {//拼接post提交参数
                                builder1.append("key=").append(key).append("&")
                                        .append("params=").append(URLEncoder.encode(params, "UTF-8"));

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            String postData = builder1.toString();
                            System.out.println(loginurl);
                            webView.postUrl(loginurl, EncodingUtils.getBytes(postData, "UTF-8"));
//                            webView.loadUrl("https://blog.csdn.net/justinaelx/article/details/79635739");


                        } else {

                            stateLayout.setErrorAction((View.OnClickListener) view -> loadData());
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        stateLayout.showErrorView();
                        stateLayout.setErrorAction((View.OnClickListener) view -> loadData());
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
        if (webView == null) {
            finish();
            return;
        }
        if (webView.canGoBack()) { //从界面创建后到现在，有历史记录可以返回
            webView.goBack();
            finish();
        } else {
            finish();   //没有历史记录直接退出
        }
    }


    @Override
    protected void onPause() {
//        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
        try {
            if (webView != null) {
                webView.onPause();
                webView.loadUrl("www.baidu.com");
                finish();
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
//        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
        try {
            if (isOnPause) {
                if (webView != null) {
                    webView.onResume();
                }
                isOnPause = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
