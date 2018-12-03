package com.dafa.qipai.dafaqipai.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoLogin;
import com.dafa.qipai.dafaqipai.chong.WeiXin4BackActivity;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ACache;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.MD5Utils;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.kongzue.dialog.v2.MessageDialog;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.miss)
    TextView miss;
    @BindView(R.id.login)
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AutoUtils.auto(this);



    }


    @OnClick({R.id.close, R.id.miss, R.id.login})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.miss:
                gotoActivity(KefuActivity.class);
                break;
            case R.id.login:
                login();
                break;
        }
    }

    private void login() {

        OkGo.post(ApiConstant.API_DOMAIN + "/member/login.json")
                .tag(this)
                .params("account", name.getText().toString())
                .params("password", MD5Utils.small32md5(pass.getText().toString()))
                .params("ip", "192.0.0.0")
                .params("loginType", 4)
                .execute(new OkGoCallBack(LoginActivity.this, true) {
                    @Override
                    protected void _onNext(String json) {
                        Log.e("xi", "_onNext: " + json);
                        DoLogin doLogin = GsonUtil.GsonToBean(json, DoLogin.class);
                        if (doLogin.getResult() == 1) {

                            ACache.get(context).put("token", doLogin.getToken());
                            ACache.get(context).put("account", name.getText().toString());
                            ACache.get(context).put("passwd", MD5Utils.small32md5(pass.getText().toString()));
                            ACache.get(context).put("userId", doLogin.getUserId() + "");

                            showInfo(context, "登录成功");
                            finish();
                            //gotoActivity(MainActivity.class);
                        } else {
                            showError(context, doLogin.getDescription());
                        }
                    }
                });
    }



}
