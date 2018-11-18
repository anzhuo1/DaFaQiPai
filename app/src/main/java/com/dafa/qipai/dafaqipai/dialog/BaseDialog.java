package com.dafa.qipai.dafaqipai.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;


public abstract class BaseDialog extends Dialog {

    Activity context;
    String contant;
    String title;
    String btnStr;
    boolean b;

    public BaseDialog (Activity context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.base_dialog);

    }

    public abstract void mustDoThing(Dialog mDialog);

    public abstract void mustDoThingOnCancle(Dialog mDialog);
}