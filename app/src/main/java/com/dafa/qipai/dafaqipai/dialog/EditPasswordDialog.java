package com.dafa.qipai.dafaqipai.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;


public abstract class EditPasswordDialog extends Dialog {

    Activity context;
    private String msg;

    String btnStr1;
    String btnStr2;


    public EditPasswordDialog(Activity context, String msg, String btnStr1, String btnStr2) {
        super(context, R.style.MyDialog6);
        this.context = context;
        this.msg = msg;
        this.btnStr1 = btnStr1;
        this.btnStr2 = btnStr2;

    }

    public EditPasswordDialog(Activity context, String msg) {
        super(context, R.style.MyDialog6);
        this.context = context;
        this.msg = msg;
        this.btnStr1 = btnStr1;
        this.btnStr2 = btnStr2;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.phone_dialog);

        EditText tv = findViewById(R.id.text);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);


        if (msg != null) {
            tv.setText(msg);
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPasswordDialog.this.dismiss();
                btn1DoThing(EditPasswordDialog.this);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tv.getText().toString().length() < 11) {

                    AppUtils.showToast(context, "请输入正确手机号");
                    return;
                }


                OkGo.post(ApiConstant.API_DOMAIN + "/sphone/insertUserPhone.json")
                        .params("uid", UserUtil.getUserID(context))
                        .params("token", UserUtil.getToken(context))
                        .params("phone", tv.getText().toString())
                        .execute(new OkGoCallBack(context, false) {
                            @Override
                            protected void _onNext(String json) {

                                BaseDo doSessionInfo = GsonUtil.GsonToBean(json, BaseDo.class);
                                if (doSessionInfo.getResult() == 1) {
                                    btn2DoThing(EditPasswordDialog.this);

                                }


                            }

                            @Override
                            protected void _onError(String error) {
                                super._onError(error);

                            }
                        });


            }
        });

        this.setCancelable(false);


    }

    public abstract void btn1DoThing(Dialog mDialog);

    public abstract void btn2DoThing(Dialog mDialog);


}