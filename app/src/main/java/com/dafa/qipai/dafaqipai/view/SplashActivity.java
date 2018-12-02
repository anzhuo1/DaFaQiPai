package com.dafa.qipai.dafaqipai.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.HuodongAdapter;
import com.dafa.qipai.dafaqipai.bean.DoBxx;
import com.dafa.qipai.dafaqipai.bean.DoGetPromotion;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.SProgress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;


public class SplashActivity extends BaseActivity {


    @BindView(R.id.progress)
    SProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        AutoUtils.auto(this);






        progress.setProgress(0)
                .setTextSize(0)
                .animatorToProgress(100f);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                gotoActivity(MainActivity.class, true);

            }
        }, 3000);


        OkGo.post(ApiConstant.API_DOMAIN + "/promotion/getPromotion.json\n")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .cacheKey("promotion/getPromotion.json")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(3600 * 1000 * 12)
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {


                    }
                });
    }


}