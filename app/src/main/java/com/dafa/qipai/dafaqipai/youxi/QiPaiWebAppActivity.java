package com.dafa.qipai.dafaqipai.youxi;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.Qipai;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseActivity;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.just.library.AgentWeb;
import com.lzy.okgo.OkGo;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class QiPaiWebAppActivity extends BaseYouxiActivity {


    @BindView(R.id.LinearLayout)
    android.widget.LinearLayout LinearLayout;

    @BindView(R.id.statelayout)
    StateLayout statelayout;
    private AgentWeb mAgentWeb;
    private WebView webView;
    private String url;
    private String url2;
    boolean isOnPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewapp2);
        ButterKnife.bind(this);

        int type = MyApp.type;
        String jine = MyApp.jine;


        if (type == 1) {

            OkGo.post(ApiConstant.API_DOMAIN + "/chess/changeAmount.json")
                    .params("token", UserUtil.getToken(this))
                    .params("uid", UserUtil.getUserID(this))
                    .params("userBalance", jine)
                    .execute(new OkGoCallBack(this, true) {
                        @Override
                        protected void _onNext(String json) {
                            System.out.println(json);
                            BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                            if (baseDo.getResult() != 1) {
                                AppUtils.showToast(context, baseDo.getDescription());
                            }

                        }

                    });

        }

        if (type == 2) {

            OkGo.post(ApiConstant.API_DOMAIN + "/chess/changeBbinAmount.json")
                    .tag(this)
                    .params("token", UserUtil.getToken(this))
                    .params("uid", UserUtil.getUserID(this))
                    .params("userBalance", jine)
                    .execute(new OkGoCallBack(this, true) {
                        @Override
                        protected void _onNext(String json) {
                            System.out.println(json);
                            BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                            if (baseDo.getResult() != 1) {
                                AppUtils.showToast(context, baseDo.getDescription());
                            }


                        }

                    });
        }

        if (type == 3) {

            OkGo.post(ApiConstant.API_DOMAIN + "/chess/changeAgAmount.json")
                    .tag(this)
                    .params("token", UserUtil.getToken(this))
                    .params("uid", UserUtil.getUserID(this))
                    .params("userBalance", jine)
                    .execute(new OkGoCallBack(this, true) {
                        @Override
                        protected void _onNext(String json) {
                            System.out.println(json);
                            BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                            if (baseDo.getResult() != 1) {
                                AppUtils.showToast(context, baseDo.getDescription());
                            }


                        }

                    });
        }

        if (type == 4) {

            OkGo.post(ApiConstant.API_DOMAIN + "/sport/changeAmount.json")
                    .tag(this)
                    .params("token", UserUtil.getToken(this))
                    .params("uid", UserUtil.getUserID(this))
                    .params("userBalance", jine)
                    .execute(new OkGoCallBack(this, true) {
                        @Override
                        protected void _onNext(String json) {
                            System.out.println(json);
                            BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                            if (baseDo.getResult() != 1) {
                                AppUtils.showToast(context, baseDo.getDescription());
                            }

                        }

                    });

        }


       // StatusBarCompat.setStatusBarColor(this, Color.parseColor("#0983fe"));

        url = getIntent().getStringExtra("url");


        loadData();


    }

    private BigDecimal qipaiYueChessAmount = new BigDecimal("0");

    private void loadData() {

//        OkGo.post(ApiConstant.API_DOMAIN + "/chess/getChessAmount.json")
//                .tag(this)
//                .params("token", UserUtil.getToken(context))
//                .params("uid", UserUtil.getUserID(context))
//                .execute(new OkGoCallBack(this, false) {
//                    @Override
//                    protected void _onNext(String json) {
//                        System.out.println(json);
//
//                        QipaiYue qipaiYue = GsonUtil.GsonToBean(json, QipaiYue.class);
//
//
//                        qipaiYueChessAmount = qipaiYue.getChessAmount();
//
//
//                        if (isFinishing()) {
//                            return;
//                        }
//
//                        if (qipaiYueChessAmount.compareTo(new BigDecimal("0")) == 0) {
//
//                            new ZhuceDialog(QiPaiWebAppActivity2.this, "温馨提示", "立即转换", "继续进入", "您的棋牌游戏额度不足\n\n请转换额度再进行游戏\n") {
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
//                    }
//                });


        statelayout.showProgressView();


        OkGo.post(ApiConstant.API_DOMAIN + "/chess/getChessUrl2.json")
                .tag(this)
                .params("clientType", "Android")
                .params("KindID", Integer.parseInt(url))
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        Qipai qipai = GsonUtil.GsonToBean(json, Qipai.class);
                        if (qipai.getResult() == 1) {

                            String url = qipai.getLoginUrl();

                            android.widget.LinearLayout linearLayout = (android.widget.LinearLayout) findViewById(R.id.LinearLayout);
                            mAgentWeb = AgentWeb.with(QiPaiWebAppActivity.this)
                                    .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
                                    .useDefaultIndicator()// 使用默认进度条
                                    .defaultProgressBarColor() // 使用默认进度条颜色
                                    .createAgentWeb()//
                                    .ready()
                                    .go(url);

                            webView = mAgentWeb.getWebCreator().get();

//                            webView.setBackgroundColor(ContextCompat.getColor(QiPaiWebAppActivity2.this,android.R.color.transparent));
//                            webView.setBackgroundResource(R.color.black);
                            webView.setWebViewClient(new WebViewClient() {
                                @Override
                                public void onPageFinished(WebView view, String url) {
                                    super.onPageFinished(view, url);
//                                    Toast.makeText(MainActivity.this,"加载完毕",Toast.LENGTH_SHORT).show();
                                    statelayout.showContentView();


                                }

                                @Override
                                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                    super.onPageStarted(view, url, favicon);
//                                    Toast.makeText(MainActivity.this,"开始加载",Toast.LENGTH_SHORT).show();
                                }
                            });


                           // mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(mAgentWeb, QiPaiWebAppActivity2.this));

                        } else {
                            statelayout.setErrorAction(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    loadData();
                                }
                            });
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        statelayout.showErrorView();
                        statelayout.setErrorAction(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                loadData();
                            }
                        });
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
