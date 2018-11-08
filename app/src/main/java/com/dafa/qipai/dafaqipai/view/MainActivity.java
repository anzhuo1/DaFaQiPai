package com.dafa.qipai.dafaqipai.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoLayer;
import com.dafa.qipai.dafaqipai.bean.PublicMsgDo;
import com.dafa.qipai.dafaqipai.bean.UserBaseSession;
import com.dafa.qipai.dafaqipai.chong.ChongzhiActivity;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.DianZiFragment;
import com.dafa.qipai.dafaqipai.fra.QipaiFragment;
import com.dafa.qipai.dafaqipai.fra.TiyuFragment;
import com.dafa.qipai.dafaqipai.fra.ZhenrenFragment;
import com.dafa.qipai.dafaqipai.geren.GerenActivity;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.tixian.TiXianActivity;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.BackgroundMusic;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.SoundPoolUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.MarqueeTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class MainActivity extends FragmentActivity {


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

    private SoundPoolUtil soundPoolUtil;


    QipaiFragment qipaiFragment;
    ZhenrenFragment zhenrenFragment;
    TiyuFragment tiyuFragment;
    DianZiFragment dianZiFragment;
    private boolean canWithdraw;


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("android:support:fragments", null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityContainer.getInstance().addActivity(this);

        loadData4THIS();

        loadGongao();


        loadQuanXian();

        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AutoUtils.auto(this);

        AppUtils.playBgMuisc(this);

        soundPoolUtil = SoundPoolUtil.getInstance(this);

//        gonggao.setText("");
//        gonggao.setFocusable(true);
//        gonggao.setFocusableInTouchMode(true);
//        gonggao.requestFocus();


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
                    }

                    @Override
                    protected void _onNext(String json) {
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
        soundPoolUtil.play(2);

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


                bg.setBackgroundResource(R.mipmap.bg_tiyu);


                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();

                if (tiyuFragment == null) {
                    tiyuFragment = new TiyuFragment();
                    transaction3.add(R.id.framelayout, tiyuFragment);
                }
                hideFragment(transaction3);
                transaction3.show(tiyuFragment);
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

        if (tiyuFragment != null) {
            transaction.hide(tiyuFragment);
        }

        if (dianZiFragment != null) {
            transaction.hide(dianZiFragment);
        }


    }

    @OnClick({R.id.xinxi,R.id.dojine, R.id.head, R.id.huodong, R.id.tuiguang, R.id.kefu, R.id.baoxian, R.id.shezhi, R.id.tixian, R.id.chongzhi})
    public void onViewClicked2(View view) {
        soundPoolUtil.play(1);


        switch (view.getId()) {

            case R.id.xinxi:
                startActivity(new Intent(this, XiaoXiActivity.class));
                break;

            case R.id.huodong:
                startActivity(new Intent(this, HuoDongActivity.class));
                break;
            case R.id.tuiguang:
                AppUtils.showToast(this, "暂未开启");
                break;
            case R.id.kefu:
                startActivity(new Intent(this, KefuActivity.class));
                break;
            case R.id.baoxian:
                // startActivity(new Intent(this, BaoxianxActivity.class));
                AppUtils.showToast(this, "暂未开启");
                break;
            case R.id.shezhi:
                startActivity(new Intent(this, SheZhiActivity.class));
                break;
            case R.id.tixian:
                if (MyApp.isSHIWAN || !canWithdraw) {

                    AppUtils.showToast(MainActivity.this, "试玩用户无权访问");
                    return;
                }

                startActivity(new Intent(this, TiXianActivity.class));
                break;
            case R.id.chongzhi:
            case R.id.dojine:


                if (MyApp.isSHIWAN || !canWithdraw) {

                    AppUtils.showToast(MainActivity.this, "试玩用户无权访问");
                    return;
                }

                startActivity(new Intent(this, ChongzhiActivity.class));
                break;
            case R.id.head:
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

        Process.killProcess(Process.myPid()); //获取PID
        System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
        //ActivityContainer.getInstance().finishAllActivity();
        //ActivityContainer.getInstance().finishAllActivity();
        //AppManager.getAppManager().AppExit(this);

        BackgroundMusic.getInstance(this).stopBackgroundMusic();


    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData4THIS();
    }

}
