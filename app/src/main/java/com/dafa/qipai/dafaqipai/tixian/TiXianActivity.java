package com.dafa.qipai.dafaqipai.tixian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoGetBankcard;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.geren.GerenActivity;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.rx.rxbus.RxBus;
import com.dafa.qipai.dafaqipai.rx.rxbus.RxBusConfig;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseFragmentActivity;
import com.lzy.okgo.OkGo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;


public class TiXianActivity extends BaseFragmentActivity {


    @BindView(R.id.bg)
    RelativeLayout bg;

    @BindView(R.id.qipai)
    RadioButton qipai;
    @BindView(R.id.zhenren)
    RadioButton zhenren;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.idnum)
    TextView idnum;
    @BindView(R.id.fuzhi)
    TextView fuzhi;
    @BindView(R.id.chongti)
    TextView chongti;
    @BindView(R.id.finish)
    TextView finish;


    BangKaFragment bangKaFragment;

    TiXianFragment tiXianFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        ActivityContainer.getInstance().addActivity(this);

        idnum.setText(MyApp.ID);

        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();

        if (tiXianFragment == null) {
            tiXianFragment = new TiXianFragment();
            transaction3.add(R.id.framelayout, tiXianFragment);
        }
        hideFragment(transaction3);
        transaction3.show(tiXianFragment);
        transaction3.commit();


        RxBus.getDefault().toObserverable(String.class).subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                        if (s.equals(RxBusConfig.YINHANGKA)) {

                            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();

                            if (tiXianFragment == null) {
                                tiXianFragment = new TiXianFragment();
                                transaction3.add(R.id.framelayout, tiXianFragment);
                            }
                            hideFragment(transaction3);
                            transaction3.show(tiXianFragment);
                            transaction3.commit();

                            qipai.setVisibility(View.GONE);

                            zhenren.setChecked(true);
                        }


                    }
                }


        );


        RxBus.getDefault().toObserverable(String.class).subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                        if (s.equals(RxBusConfig.YINHANGKA2)) {

                            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                            if (bangKaFragment == null) {
                                bangKaFragment = new BangKaFragment();
                                transaction2.add(R.id.framelayout, bangKaFragment);
                            }
                            hideFragment(transaction2);
                            transaction2.show(bangKaFragment);
                            transaction2.commit();

                            qipai.setChecked(true);


                        }


                    }
                }


        );


        OkGo.post(ApiConstant.API_DOMAIN + "/member/getUserBankCardList.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, true) {
                    @Override
                    protected void _onNext(String json) {
                        DoGetBankcard doGetBankcard = GsonUtil.GsonToBean(json, DoGetBankcard.class);
                        List<DoGetBankcard.UserBankCardListBean> cardList = doGetBankcard.getUserBankCardList();
                        if (cardList == null || cardList.size() == 0) {

                            qipai.setVisibility(View.VISIBLE);
                        } else {

                            qipai.setVisibility(View.GONE);
                        }


                    }
                });


    }


    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (bangKaFragment != null) {
            transaction.hide(bangKaFragment);
        }
        if (tiXianFragment != null) {
            transaction.hide(tiXianFragment);
        }
//
//        if (tiyuFragment != null) {
//            transaction.hide(tiyuFragment);
//        }
//
//        if (dianZiFragment != null) {
//            transaction.hide(dianZiFragment);
//        }


    }

    @OnClick({R.id.qipai, R.id.zhenren, R.id.fuzhi, R.id.chongti, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qipai:

                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                if (bangKaFragment == null) {
                    bangKaFragment = new BangKaFragment();
                    transaction2.add(R.id.framelayout, bangKaFragment);
                }
                hideFragment(transaction2);
                transaction2.show(bangKaFragment);
                transaction2.commit();

                break;
            case R.id.zhenren:

                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();

                if (tiXianFragment == null) {
                    tiXianFragment = new TiXianFragment();
                    transaction3.add(R.id.framelayout, tiXianFragment);
                }
                hideFragment(transaction3);
                transaction3.show(tiXianFragment);
                transaction3.commit();

                break;
            case R.id.fuzhi:
                AppUtils.copyToClipboard(this, MyApp.ID);
                break;
            case R.id.chongti:

                Intent intent = new Intent(this, GerenActivity.class);
                intent.putExtra("key", "tx");
                startActivity(intent);
                break;
            case R.id.finish:
                finish();
                break;
        }
    }
}
