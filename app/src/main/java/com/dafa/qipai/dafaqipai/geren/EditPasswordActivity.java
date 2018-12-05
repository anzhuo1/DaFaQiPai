package com.dafa.qipai.dafaqipai.geren;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.MD5Utils;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseActivity;
import com.dafa.qipai.dafaqipai.view.LoginActivity;
import com.dafa.qipai.dafaqipai.view.MainActivity;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPasswordActivity extends BaseActivity {


    @BindView(R.id.old_pwd)
    EditText oldPwd;
    @BindView(R.id.new_pwd)
    EditText newPwd;
    @BindView(R.id.again_new_pwd)
    EditText againNewPwd;
    @BindView(R.id.loginout)
    Button loginout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        ButterKnife.bind(this);
//        centertitle.setText("修改密码");
//
//        spantextview.append("密码由");
//        spantextview.spanedable("6-12").color(Color.RED).type(Typeface.NORMAL).commit();
//        spantextview.append("个字符字母和数字组合");
//        spantextview.spanedable("区分大小写").color(Color.RED).type(Typeface.NORMAL).commit();
//        spantextview.append("为了提升您的安全性，建议使用");
//        spantextview.spanedable("英文加数字").color(Color.RED).type(Typeface.NORMAL).commit();
//        spantextview.append("或者");
//        spantextview.spanedable("混合组合").color(Color.RED).type(Typeface.NORMAL).commit();
//        spantextview.append("密码");

    }

    @OnClick({R.id.close, R.id.loginout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.loginout:

                comm();

                break;
        }
    }

    private void comm() {

        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{7,14}$";

        String password = newPwd.getText().toString();

        boolean matches = password.matches(regex);


        if (!matches) {
            Toast.makeText(context, "密码输入格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!newPwd.getText().toString().equals(againNewPwd.getText().toString())) {
            Toast.makeText(context, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.post(ApiConstant.API_DOMAIN + "/member/resetPassword.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("oldPassword", MD5Utils.small32md5(oldPwd.getText().toString()))
                .params("newPassword", MD5Utils.small32md5(newPwd.getText().toString()))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                        if (baseDo.getResult() == 1) {

                            new BaseDialog(EditPasswordActivity.this, "" +
                                    "修改密码成功\n请重新登录", "取消", "确定") {
                                @Override
                                public void btn1DoThing(Dialog mDialog) {

                                    UserUtil.loginOut(context);

                                    finish();
                                    ActivityContainer.getInstance().finishAllActivity();

//                            Intent intent = new Intent(EditPasswordActivity.this, MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);

                                    gotoActivity(MainActivity.class);


                                    // gotoActivity(LoginActivity.class);
                                }

                                @Override
                                public void btn2DoThing(Dialog mDialog) {

                                    UserUtil.loginOut(context);

                                    finish();
                                    ActivityContainer.getInstance().finishAllActivity();

//                            Intent intent = new Intent(EditPasswordActivity.this, MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);

                                    gotoActivity(MainActivity.class);


                                    // gotoActivity(LoginActivity.class);

                                }
                            }.show();




                        } else {
                            Toast.makeText(EditPasswordActivity.this, baseDo.getDescription(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
