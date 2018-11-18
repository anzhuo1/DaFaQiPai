package com.dafa.qipai.dafaqipai.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoShemm;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BaoxianxActivity2 extends BaseActivity {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.mm)
    EditText mm;
    @BindView(R.id.miss)
    TextView miss;
    @BindView(R.id.ok)
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_baoxianxiang2);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


    }

    @OnClick({R.id.close, R.id.miss, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.miss:
                gotoActivity(KefuActivity.class);
                break;
            case R.id.ok:

                if (mm.getText().toString().length() < 6) {
                    AppUtils.showToast(context, "密码不能小于6位");
                    return;
                }


                OkGo.post(ApiConstant.API_DOMAIN + "/safeu/login.json")
                        .params("token", UserUtil.getToken(this))
                        .params("uid", UserUtil.getUserID(this))
                        .params("password", mm.getText().toString())
                        .execute(new OkGoCallBack(this, true) {
                            @Override
                            protected void _onNext(String json) {

                                DoShemm doShemm = GsonUtil.GsonToBean(json, DoShemm.class);
                                if (doShemm.getResult() == 1) {

                                    gotoActivity(BaoxianxActivity3.class, true);

                                } else {
                                    AppUtils.showToast(context, doShemm.getDescription());
                                }

                            }
                        });

                break;
        }
    }


}
