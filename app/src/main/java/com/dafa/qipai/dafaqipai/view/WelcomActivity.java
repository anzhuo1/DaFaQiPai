package com.dafa.qipai.dafaqipai.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.DoLogin;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ACache;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.kongzue.dialog.v2.MessageDialog;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WelcomActivity extends BaseActivity {


    @BindView(R.id.youke)
    TextView youke;
    @BindView(R.id.denglu)
    TextView denglu;
    @BindView(R.id.zhuce)
    TextView zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        OkGo.post(ApiConstant.API_DOMAIN + "member/checkOnline.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(null, false) {
                    @Override
                    protected void _onNext(String json) {
                        BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                        if (baseDo.getResult() == 1) {

                            gotoActivity(MainActivity.class);
                            finish();

                        } else {


                        }
                    }
                });


    }

    private void shiwan() {
        OkGo.post(ApiConstant.API_DOMAIN + "/member/shiwanLogin.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("ip", "")
                .params("url", "")
                .params("loginType", 4)
                .execute(new OkGoCallBack(this, true) {


                    @Override
                    protected void _onNext(String json) {

                        DoLogin doLogin = GsonUtil.GsonToBean(json, DoLogin.class);

                        if (doLogin.getResult() == 1) {
                            MyApp.isSHIWAN = true;

                            ACache.get(context).put("token", doLogin.getToken());
                            ACache.get(context).put("userId", doLogin.getUserId() + "");


                            MessageDialog.show(WelcomActivity.this, "消息提示", "游客盘口只供试玩，与正式会员盘口无关", "知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    gotoActivity(MainActivity.class);
                                }
                            });


                        } else {
                            showError(context, doLogin.getDescription());
                        }
                    }
                });

    }


    @OnClick({R.id.youke, R.id.denglu, R.id.zhuce})
    public void onViewClicked(View view) {
        playMusic(2);
        switch (view.getId()) {
            case R.id.youke:
                shiwan();
                break;
            case R.id.denglu:
                gotoActivity(LoginActivity.class);
                break;
            case R.id.zhuce:
                gotoActivity(RegisterActivity.class);
                break;
        }
    }
}
