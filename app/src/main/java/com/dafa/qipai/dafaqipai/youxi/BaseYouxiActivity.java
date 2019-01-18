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



        BackgroundMusic.getInstance(getApplicationContext()).pauseBackgroundMusic();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {

            forumContext.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        BackgroundMusic.getInstance(getApplicationContext()).pauseBackgroundMusic();
    }


}
