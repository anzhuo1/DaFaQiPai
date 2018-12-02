package com.dafa.qipai.dafaqipai.baoxianxiaong;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoShemm;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseActivity;
import com.lzy.okgo.OkGo;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BaoxianxActivity extends BaseActivity {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.mm1)
    EditText mm1;
    @BindView(R.id.mm2)
    EditText mm2;
    @BindView(R.id.ok)
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_baoxianxiang);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


    }


    @OnClick({R.id.close, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.ok:

                if (mm1.getText().toString().length() < 6) {
                    AppUtils.showToast(context, "密码不能小于6位");
                    return;
                }

                if (!mm1.getText().toString().equals(mm2.getText().toString())) {
                    AppUtils.showToast(context, "两次输入密码不一致");
                    return;
                }

                OkGo.post(ApiConstant.API_DOMAIN + "/safeu/setUserPassword.json")
                        .params("token", UserUtil.getToken(this))
                        .params("uid", UserUtil.getUserID(this))
                        .params("password", mm1.getText().toString())
                        .execute(new OkGoCallBack(this, true) {
                            @Override
                            protected void _onNext(String json) {

                                DoShemm doShemm = GsonUtil.GsonToBean(json, DoShemm.class);
                                if (doShemm.getResult() == 1) {

                                    try {

                                        MyApp.baoxianxiang = mm1.getText().toString();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    gotoActivity(BaoxianxActivity3.class, false);
                                    finish();

                                } else {
                                    AppUtils.showToast(context, doShemm.getDescription());
                                }

                            }
                        });


                break;
        }
    }
}
