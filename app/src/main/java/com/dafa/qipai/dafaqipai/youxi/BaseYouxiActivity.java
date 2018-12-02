package com.dafa.qipai.dafaqipai.youxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.BackgroundMusic;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseActivity;
import com.lzy.okgo.OkGo;

import butterknife.BindView;

public class BaseYouxiActivity extends BaseActivity {

    @BindView(R.id.forum_context)
    com.tencent.smtt.sdk.WebView forumContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.stopBgMuisc(this);

        MyApp.type = 0;

        BackgroundMusic.getInstance(getApplicationContext()).pauseBackgroundMusic();

    }

    @Override
    protected void onPause() {
        super.onPause();

//        try {
//            AppUtils.playBgMuisc(this);
//            forumContext.destroy();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        5.游戏退出返回到我们app界面的时候调用该接口 需要登录
///chess/autotWithdrawIndex.json
//        参数 clientType:客户端类型 （1: PC,2: Mobile,3: Android,4: IOS）
//        参数 type  1.开元棋牌 2.BBIN  3.AG  4.体育


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            AppUtils.playBgMuisc(this);
            forumContext.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //        参数 type  1.开元棋牌 2.BBIN  3.AG  4.体育

    @Override
    protected void onResume() {
        super.onResume();

        BackgroundMusic.getInstance(getApplicationContext()).pauseBackgroundMusic();
    }


}
