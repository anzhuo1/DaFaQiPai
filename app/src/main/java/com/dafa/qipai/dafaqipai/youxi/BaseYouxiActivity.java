package com.dafa.qipai.dafaqipai.youxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseActivity;
import com.lzy.okgo.OkGo;

public class BaseYouxiActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.stopBgMuisc(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        AppUtils.playBgMuisc(this);


//        5.游戏退出返回到我们app界面的时候调用该接口 需要登录
///chess/autotWithdrawIndex.json
//        参数 clientType:客户端类型 （1: PC,2: Mobile,3: Android,4: IOS）
//        参数 type  1.开元棋牌 2.BBIN  3.AG  4.体育


        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                .params("clientType", "Android")
                .params("type", 1)
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {


                    }
                });

        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                .params("clientType", "Android")
                .params("type", 2)
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {


                    }
                });

        OkGo.post(ApiConstant.API_DOMAIN + "/chess/autotWithdrawIndex.json")
                .params("clientType", "Android")
                .params("type", 3)
                .params("token", UserUtil.getToken(context))
                .params("uid", UserUtil.getUserID(context))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {


                    }
                });


    }

    //        参数 type  1.开元棋牌 2.BBIN  3.AG  4.体育

    @Override
    protected void onResume() {
        super.onResume();


    }





}
