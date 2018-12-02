package com.dafa.qipai.dafaqipai.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;

import butterknife.BindView;
import butterknife.OnClick;


public abstract class BaseDialog extends Dialog {

    Activity context;
    private String msg;

    String btnStr1;
    String btnStr2;



    public BaseDialog(Activity context, String msg, String btnStr1, String btnStr2) {
        super(context, R.style.MyDialog6);
        this.context = context;
        this.msg = msg;
        this.btnStr1 = btnStr1;
        this.btnStr2 = btnStr2;

    }

    public BaseDialog(Activity context, String msg) {
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
        this.setContentView(R.layout.base_dialog);

        TextView tv = findViewById(R.id.text);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);


        if (msg != null) {
            tv.setText(msg);
        }


        if(!TextUtils.isEmpty(btnStr1)){
            btn1.setText(btnStr1);
        }
        if(!TextUtils.isEmpty(btnStr2)){
            btn2.setText(btnStr2);
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDialog.this.dismiss();
                btn1DoThing(BaseDialog.this);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDialog.this.dismiss();
                btn2DoThing(BaseDialog.this);
            }
        });

        this.setCancelable(false);



    }

    public abstract void btn1DoThing(Dialog mDialog);

    public abstract void btn2DoThing(Dialog mDialog);


}