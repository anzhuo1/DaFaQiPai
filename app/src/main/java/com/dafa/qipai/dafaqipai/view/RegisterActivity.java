package com.dafa.qipai.dafaqipai.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoLogin;
import com.dafa.qipai.dafaqipai.bean.ZhuCeDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ACache;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.CodeUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.MD5Utils;
import com.dafa.qipai.dafaqipai.util.SystemTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseActivity {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.again_pass)
    EditText againPass;
    @BindView(R.id.tuijian)
    EditText tuijian;
    @BindView(R.id.yanzhengma)
    EditText yanzhengma;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.bg)
    RelativeLayout bg;

    String realCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        img.setImageBitmap(CodeUtils.getInstance().createBitmap());
        realCode = CodeUtils.getInstance().getCode().toLowerCase();


    }


    @OnClick({R.id.img, R.id.login, R.id.close})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.img:
                img.setImageBitmap(CodeUtils.getInstance().createBitmap());
                realCode = CodeUtils.getInstance().getCode().toLowerCase();
                break;
            case R.id.login:
                rigister();
                break;
            case R.id.close:
                finish();
                break;
        }
    }

    private void rigister() {


        if (TextUtils.isEmpty(name.getText().toString())) {
            showError(RegisterActivity.this, "请输入用户名!");
            return;
        }
        String userName = name.getText().toString();

        if (userName.length() < 7) {
            Toast.makeText(RegisterActivity.this, "用户名应大于7位!", Toast.LENGTH_LONG).show();
        }


        if (TextUtils.isEmpty(pass.getText().toString())) {
            showError(RegisterActivity.this, "请输入密码!");
            return;
        }
        if (TextUtils.isEmpty(againPass.getText().toString())) {
            showError(RegisterActivity.this, "请确认密码!");
            return;
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{7,14}$";

        String password = pass.getText().toString();

        boolean matches = password.matches(regex);


        if (!matches) {
            showError(context, "密码为7-14位字母加数字");
            return;
        }
        if (TextUtils.isEmpty(yanzhengma.getText().toString())) {
            showError(RegisterActivity.this, "请输入验证码!");
            return;
        }
        if (!realCode.equals(yanzhengma.getText().toString().toLowerCase())) {
            showError(RegisterActivity.this, "验证码不正确,请重新输入!");
            return;
        }


        String phoneIMEI = SystemTool.getPhoneIMEI(RegisterActivity.this);
        PostRequest request = OkGo.post(ApiConstant.API_DOMAIN + "/member/register.json")
                .tag(this);

        request.params("account", name.getText().toString());
        request.params("password", MD5Utils.small32md5(pass.getText().toString()));
        request.params("name", "");
        request.params("ip", "");
        request.params("url", "");
        request.params("agentId", tuijian.getText().toString());
        request.params("deviceNo", phoneIMEI);
        request.params("clientType", 3);
        request.execute(new OkGoCallBack(RegisterActivity.this, true) {
            @Override
            protected void _onNext(String json) {

                ZhuCeDo baseDo = GsonUtil.GsonToBean(json, ZhuCeDo.class);
                if (!TextUtils.isEmpty(baseDo.getDescription())) {
                    showError(RegisterActivity.this, baseDo.getDescription());
                }

                if (baseDo.getResult() == 1) {

                    OkGo.post(ApiConstant.API_DOMAIN + "/member/login.json")
                            .tag(this)
                            .params("account", name.getText().toString())
                            .params("password", MD5Utils.small32md5(pass.getText().toString()))
                            .params("ip", AppUtils.getHostIP())
                            .params("loginType", 4)
                            .execute(new OkGoCallBack(RegisterActivity.this, true) {
                                @Override
                                protected void _onNext(String json) {
                                    DoLogin doLogin = GsonUtil.GsonToBean(json, DoLogin.class);
                                    if (doLogin.getResult() == 1) {
                                        //App.isSHIWAN = false;
                                        ACache.get(context).put("token", doLogin.getToken());
                                        ACache.get(context).put("account", name.getText().toString());
                                        ACache.get(context).put("passwd", MD5Utils.small32md5(pass.getText().toString()));
                                        ACache.get(context).put("userId", doLogin.getUserId() + "");
//                                        if (baseDo.isCanGetRegCaijin()) {
//                                            Intent intent = new Intent();
//                                            intent.setClass(RegisterActivity.this, RegisterSucceedActivity.class);
//                                            intent.putExtra("baseDo", baseDo);
//                                            startActivity(intent);
//                                            finish();
//                                        } else {
                                        showInfo(context, "注册成功");
                                        finish();
//                                        }

                                    } else {
                                        showError(context, doLogin.getDescription());
                                    }
                                }
                            });


                } else {
                    showError(RegisterActivity.this, baseDo.getDescription());
                }
            }
        });


    }
}
