package com.dafa.qipai.dafaqipai.wihget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 *
 *
 * Created by shn on 2017/6/23.
 */
@SuppressLint("AppCompatCustomView")
public class NoIndex0EditeText extends EditText implements TextWatcher {


    public NoIndex0EditeText(Context context) {
        super(context);

        this.setSelection(this.length());
    }

    public NoIndex0EditeText(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setSelection(this.length());

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        String str = this.getText().toString().trim();

        if (str.length() > 0) {

            char c = str.charAt(0);

            String s = String.valueOf(c);

            if (s.equals("0")) {

                this.setText("");
            }

        }

        this.setSelection(this.length());

    }

    @Override
    public void afterTextChanged(Editable s) {

//        String text = s.toString();
//        int len = s.toString().length();
//        if (len == 1 && text.equals("0")) {
//            s.clear();
//        }
//
//        System.out.println(s.toString());

    }
}
