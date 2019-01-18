package com.dafa.qipai.dafaqipai.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.baoxianxiaong.BaoxianxActivity;
import com.dafa.qipai.dafaqipai.baoxianxiaong.BaoxianxActivity2;
import com.dafa.qipai.dafaqipai.bean.Apkinfo;
import com.dafa.qipai.dafaqipai.bean.DoBxx;
import com.dafa.qipai.dafaqipai.bean.DoLayer;
import com.dafa.qipai.dafaqipai.bean.PublicMsgDo;
import com.dafa.qipai.dafaqipai.bean.UserBaseSession;
import com.dafa.qipai.dafaqipai.bean.ZhanNeiXinDo;
import com.dafa.qipai.dafaqipai.chong.ChongzhiActivity;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.core.CommonConstant;
import com.dafa.qipai.dafaqipai.fra.DianZiFragment;
import com.dafa.qipai.dafaqipai.fra.LeYouFragment;
import com.dafa.qipai.dafaqipai.fra.QipaiFragment;
import com.dafa.qipai.dafaqipai.fra.ZhenrenFragment;
import com.dafa.qipai.dafaqipai.geren.EditPasswordActivity;
import com.dafa.qipai.dafaqipai.geren.GerenActivity;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.tixian.TiXianActivity;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.BackgroundMusic;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.SPUtil;
import com.dafa.qipai.dafaqipai.util.SocketUtils;
import com.dafa.qipai.dafaqipai.util.SoundPoolUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.websocket.WebSocketService;
import com.dafa.qipai.dafaqipai.websocket.WebSocketSetting;
import com.dafa.qipai.dafaqipai.websocket.core.AppResponseDispatcher;
import com.dafa.qipai.dafaqipai.wihget.MarqueeTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;

import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;


public class MainActivity extends BaseFragmentActivity {


    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dengji)
    TextView dengji;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.jine)
    TextView jine;
    @BindView(R.id.qipai)
    RadioButton qipai;
    @BindView(R.id.zhenren)
    RadioButton zhenren;
    @BindView(R.id.tiyu)
    RadioButton tiyu;
    @BindView(R.id.dianzi)
    RadioButton dianzi;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.huodong)
    TextView huodong;
    @BindView(R.id.tuiguang)
    TextView tuiguang;
    @BindView(R.id.kefu)
    TextView kefu;
    @BindView(R.id.baoxian)
    TextView baoxian;
    @BindView(R.id.shezhi)
    TextView shezhi;
    @BindView(R.id.tixian)
    TextView tixian;
    @BindView(R.id.chongzhi)
    TextView chongzhi;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.gonggao)
    MarqueeTextView gonggao;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.denglu)
    TextView denglu;
    @BindView(R.id.ll_dz)
    LinearLayout llDz;
    @BindView(R.id.dojine)
    TextView dojine;
    @BindView(R.id.xinxi1)
    TextView xinxi1;
    @BindView(R.id.xinxi2)
    TextView xinxi2;
    @BindView(R.id.shuaxin)
    TextView shuaxin;

    private SoundPoolUtil soundPoolUtil;


    QipaiFragment qipaiFragment;
    ZhenrenFragment zhenrenFragment;
    // TiyuFragment tiyuFragment;
    DianZiFragment dianZiFragment;
    LeYouFragment leYouFragment;
    private boolean canWithdraw;

    private int loginType;


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("android:support:fragments", null);
//        AutoUtils.setSize(this, false, 1920, 1080);
//        AutoUtils.auto(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (!isTaskRoot()) {
//            startActivity(new Intent(this, SplashActivity.class));
//            finish();
//        }

        //gotoActivity(GengXinActivity.class);

        SocketUtils.connectServerWithTCPSocket();


        requstQuanXian();

        ActivityContainer.getInstance().addActivity(this);

        loadData4THIS();

        loadGongao();


        checkUpdata();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        AutoUtils.setSize(this, false, 1920, 1080);
        AutoUtils.auto(this);


        // AppUtils.playBgMuisc(this);

        soundPoolUtil = SoundPoolUtil.getInstance(this);


        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。

                System.out.println(arg0);
            }

            @Override
            public void onCoreInitFinished() {

                System.out.println("aa");

            }
        };

        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);


        bg.setBackgroundResource(R.mipmap.bg_qipai);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (qipaiFragment == null) {
            qipaiFragment = new QipaiFragment();
            transaction.add(R.id.framelayout, qipaiFragment);
        }
        hideFragment(transaction);
        transaction.show(qipaiFragment);
        transaction.commit();


    }

    private void checkUpdata() {

        OkGo.post(ApiConstant.API_DOMAIN + "app/getAppGenxin.json")
                .tag(this)
                .params("version", ApiConstant.VERSION)
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {


                        Apkinfo apkinfo = GsonUtil.GsonToBean(json, Apkinfo.class);
                        if (apkinfo.getResult() == CommonConstant.SUCCESS_CODE) {
                            String version = apkinfo.getVersion();
                            String remarks = apkinfo.getRemarks();
                            String url = apkinfo.getUrl();
                            boolean force = apkinfo.isForce();

                            if (force) {
                                Bundle bundle = new Bundle();
                                bundle.putString("url", url);
                                bundle.putString("remarks", remarks);
                                gotoActivity(GengXinActivity.class, false, bundle);
                            }

                        } else {

                        }
                    }

                });

    }

    private void loadQuanXian() {

        OkGo.post(ApiConstant.API_DOMAIN + "/member/getLayer.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {

                        DoLayer doLayer = GsonUtil.GsonToBean(json, DoLayer.class);

                        canWithdraw = doLayer.isCanWithdraw();

                    }
                });


    }


    private void loadGongao() {

        OkGo.post(ApiConstant.API_DOMAIN + "/notice/getPopupNoticeList.json")
                .tag(this)
                .cacheKey("getPopupNoticeList")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(3600 * 1000 * 12)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        try {
                            PublicMsgDo baseDo = GsonUtil.GsonToBean(s, PublicMsgDo.class);
                            List<PublicMsgDo.WebNoticeListBean> noticeList = baseDo.getWebNoticeList();
                            if (noticeList != null && noticeList.size() > 0) {
                                String notice = noticeList.get(0).getContent();
                                gonggao.setText(notice);
                                gonggao.setFocusable(true);
                                gonggao.setFocusableInTouchMode(true);
                                gonggao.requestFocus();
                            } else {
                                gonggao.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onNext(String json) {
                        try {
                            PublicMsgDo baseDo = GsonUtil.GsonToBean(json, PublicMsgDo.class);
                            List<PublicMsgDo.WebNoticeListBean> noticeList = baseDo.getWebNoticeList();
                            if (noticeList != null && noticeList.size() > 0) {
                                String notice = noticeList.get(0).getContent();
                                gonggao.setText(notice);
                                gonggao.setFocusable(true);
                                gonggao.setFocusableInTouchMode(true);
                                gonggao.requestFocus();

                            } else {
                                gonggao.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void loadData4THIS() {

        OkGo.post(ApiConstant.API_DOMAIN + "member/getUserBaseInfo.json")
                .params("uid", UserUtil.getUserID(this))
                .params("token", UserUtil.getToken(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {

                        UserBaseSession doSessionInfo = GsonUtil.GsonToBean(json, UserBaseSession.class);
                        if (doSessionInfo.getResult() == 1) {
                            name.setText(doSessionInfo.getAccount());
                            id.setText("ID：" + doSessionInfo.getId() + "");
                            jine.setText(doSessionInfo.getBalance() + "");
                            dengji.setText(doSessionInfo.getLayerName());

                            MyApp.ID = doSessionInfo.getId() + "";
                            MyApp.NICHENG = doSessionInfo.getAccount();
                            MyApp.jine = doSessionInfo.getBalance() + "";
                        }
                    }

                    @Override
                    protected void _onError(String error) {
                        super._onError(error);

                    }
                });

    }


    @OnClick({R.id.qipai, R.id.zhenren, R.id.tiyu, R.id.dianzi})
    public void onViewClicked(View view) {

        float y2 = (float) SPUtil.get(this, "y2", 0.5f);
        soundPoolUtil.play(1, this, y2);

        switch (view.getId()) {
            case R.id.qipai:

                bg.setBackgroundResource(R.mipmap.bg_qipai);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if (qipaiFragment == null) {
                    qipaiFragment = new QipaiFragment();
                    transaction.add(R.id.framelayout, qipaiFragment);
                }
                hideFragment(transaction);
                transaction.show(qipaiFragment);
                transaction.commit();

                break;
            case R.id.zhenren:


                bg.setBackgroundResource(R.mipmap.bg_qipai);

                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

                if (zhenrenFragment == null) {
                    zhenrenFragment = new ZhenrenFragment();
                    transaction2.add(R.id.framelayout, zhenrenFragment);
                }
                hideFragment(transaction2);
                transaction2.show(zhenrenFragment);
                transaction2.commit();
                break;
            case R.id.tiyu:


                bg.setBackgroundResource(R.mipmap.bg_qipai);


                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();

                if (leYouFragment == null) {
                    leYouFragment = new LeYouFragment();
                    transaction3.add(R.id.framelayout, leYouFragment);
                }
                hideFragment(transaction3);
                transaction3.show(leYouFragment);
                transaction3.commit();
                break;
            case R.id.dianzi:


                bg.setBackgroundResource(R.mipmap.bg_dianzi);


                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();

                if (dianZiFragment == null) {
                    dianZiFragment = new DianZiFragment();
                    transaction4.add(R.id.framelayout, dianZiFragment);
                }
                hideFragment(transaction4);
                transaction4.show(dianZiFragment);
                transaction4.commit();
                break;
        }


    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {

        if (qipaiFragment != null) {
            transaction.hide(qipaiFragment);
        }
        if (zhenrenFragment != null) {
            transaction.hide(zhenrenFragment);
        }

        if (leYouFragment != null) {
            transaction.hide(leYouFragment);
        }

        if (dianZiFragment != null) {
            transaction.hide(dianZiFragment);
        }


    }

    @OnClick({R.id.xinxi1, R.id.xinxi2, R.id.dojine, R.id.head, R.id.id, R.id.name, R.id.huodong, R.id.tuiguang, R.id.kefu, R.id.baoxian, R.id.shezhi, R.id.tixian, R.id.chongzhi})
    public void onViewClicked2(View view) {
        float y2 = (float) SPUtil.get(this, "y2", 0.5f);
        soundPoolUtil.play(0, this, y2);

        if (view.getId() == R.id.huodong) {
            startActivity(new Intent(this, HuoDongActivity.class));
            return;
        }

        if (view.getId() == R.id.kefu) {
            startActivity(new Intent(this, KefuActivity.class));
            return;
        }

        if (!UserUtil.isLoginApp(MainActivity.this)) {
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }


        switch (view.getId()) {

            case R.id.xinxi1:
            case R.id.xinxi2:
                startActivity(new Intent(this, XiaoXiActivity.class));
                break;

            case R.id.huodong:
                startActivity(new Intent(this, HuoDongActivity.class));
                break;
            case R.id.tuiguang:
                startActivity(new Intent(this, DaiLiActivity.class));

                break;
            case R.id.kefu:
                startActivity(new Intent(this, KefuActivity.class));
                break;
            case R.id.baoxian:

                if (loginType == 1) {
                    startActivity(new Intent(this, BaoxianxActivity.class));

                } else {
                    startActivity(new Intent(this, BaoxianxActivity2.class));
                }

                break;
            case R.id.shezhi:
                // startActivity(new Intent(this, SheZhiActivity.class));

                Intent intent = new Intent(MainActivity.this, SheZhiActivity.class);
                // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tixian:

                startActivity(new Intent(this, TiXianActivity.class));
                break;
            case R.id.chongzhi:
            case R.id.dojine:

                startActivity(new Intent(this, ChongzhiActivity.class));
                break;
            case R.id.head:
            case R.id.id:
            case R.id.name:
                startActivity(new Intent(this, GerenActivity.class));

                break;
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    private void exitBy2Click() {


        ActivityContainer.getInstance().finishAllActivity();
        Process.killProcess(Process.myPid()); //获取PID
        System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出

        //ActivityContainer.getInstance().finishAllActivity();
        //AppManager.getAppManager().AppExit(this);

        BackgroundMusic.getInstance(this).stopBackgroundMusic();


    }

    @Override
    protected void onResume() {
        super.onResume();


        AppUtils.playBgMuisc(context);

        if (MyApp.type == 0) {

            int[] ints = AppUtils.randomArray(1, 10, 1);

            if (ints[0] % 2 == 0) {

                float y2 = (float) SPUtil.get(this, "y2", 0.5f);
                soundPoolUtil.play(2, this, y2);
            } else {
                float y2 = (float) SPUtil.get(this, "y2", 0.5f);
                soundPoolUtil.play(3, this, y2);
            }

            System.out.println(MyApp.type);


            MyApp.type = 1;

        }


        loadData4THIS();


        huishou();

        loadBaoXianXiaong();


        loadXinxi();


        if (UserUtil.isLoginApp(context)) {

            llDz.setVisibility(View.GONE);
            id.setVisibility(View.VISIBLE);

            dengji.setVisibility(View.VISIBLE);

        } else {

            id.setVisibility(View.GONE);
            llDz.setVisibility(View.VISIBLE);

            dengji.setVisibility(View.GONE);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                        .params("token", UserUtil.getToken(context))
                        .params("uid", UserUtil.getUserID(context))
                        .execute(new OkGoCallBack(MainActivity.this, false) {
                            @Override
                            protected void _onNext(String json) {

                                loadData4THIS();

                            }
                        });


            }
        }, 2000);


        if (UserUtil.isLoginApp(this)) {

            //配置 WebSocket，必须在 WebSocket 服务启动前设置
            String connectUrl = ApiConstant.WS_DOMAIN + "ws?uid="
                    + UserUtil.getUserID(this) + "&token="
                    + UserUtil.getToken(this) +
                    "&clientType=Android&companyShortName=" + ApiConstant.COMPANY_SHORT_NAME + "&appVersion=1";

            WebSocketSetting.setConnectUrl(connectUrl);//必选

            WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
            WebSocketSetting.setReconnectWithNetworkChanged(true);

            //启动 WebSocket 服务
            startService(new Intent(this, WebSocketService.class));

        }


    }

    private void loadXinxi() {
        OkGo.post(ApiConstant.API_DOMAIN + "/member/getUserInboxList.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("pageIndex", 1)
                .params("pageSize", 200)
                .execute(new OkGoCallBack(this, false) {


                    @Override
                    protected void _onNext(String json) {
                        try {
                            ZhanNeiXinDo baseDo = GsonUtil.GsonToBean(json, ZhanNeiXinDo.class);
                            if (baseDo.getResult() == 1) {

                                List<ZhanNeiXinDo.UserInboxBean> userInboxList = baseDo.getUserInboxList();

                                int i = 0;
                                for (ZhanNeiXinDo.UserInboxBean bean : userInboxList) {
                                    if (!bean.getHasRead()) {
                                        i++;
                                    }
                                }

                                xinxi2.setText(i + "");

                            }
                        } catch (Exception e) {

                        }
                    }
                });

    }

    private void huishou() {

//        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
//                .params("token", UserUtil.getToken(this))
//                .params("uid", UserUtil.getUserID(this))
//                .execute(new OkGoCallBack(this, false) {
//                    @Override
//                    protected void _onNext(String json) {
//
//
//                    }
//                });


        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {


                    }
                });


    }


    private void requstQuanXian() {
        // Must be done during an initialization phase like onCreate
        new RxPermissions(MainActivity.this)
                .request(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe(granted -> {

                    if (granted) { // Always true pre-M


//                        Observable.timer(3, TimeUnit.SECONDS)
//                                .subscribe(new Observer<Long>() {
//                                    @Override
//                                    public void onCompleted() {
//
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//
//                                    @Override
//                                    public void onNext(Long aLong) {
//
//
//                                    }
//                                });

                    } else {

                        AppUtils.showToast(context, "拒绝权限可能会引起APP问题，请在设置中打开");

                    }
                });

    }


    private void loadBaoXianXiaong() {

        OkGo.post(ApiConstant.API_DOMAIN + "/safeu/getUserType.json")
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        DoBxx doBxx = GsonUtil.GsonToBean(json, DoBxx.class);
                        if (doBxx.getResult() == 1) {
                            loginType = doBxx.getLoginType();

                        }

                    }
                });

    }


    /**
     * 判断是否是快速点击
     */
    private static long lastClickTime;

    @OnClick({R.id.zhuce, R.id.denglu})
    public void onViewClicked3(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                gotoActivity(RegisterActivity.class);
                break;
            case R.id.denglu:
                gotoActivity(LoginActivity.class);
                break;
        }
    }

    @OnClick(R.id.shuaxin)
    public void onViewClicked() {

        if (!UserUtil.isLoginApp(MainActivity.this)) {
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }

//        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
//                .params("token", UserUtil.getToken(context))
//                .params("uid", UserUtil.getUserID(context))
//                .execute(new OkGoCallBack(MainActivity.this, true) {
//                    @Override
//                    protected void _onNext(String json) {
//
//                        loadData4THIS();
//
//                    }
//                });


        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, true) {
                    @Override
                    protected void _onNext(String json) {


                        loadData4THIS();


                    }
                });


    }

    /**
     * 判断触摸时间派发间隔
     */


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            if (isFastDoubleClick()) {
//                return true;
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    public static boolean isFastDoubleClick() {
//
//        long time = System.currentTimeMillis();
//        long timeD = time - lastClickTime;
//        if (0 < timeD && timeD < 1000) {
//
//            return true;
//        }
//        lastClickTime = time;
//        return false;
//
//    }


}
