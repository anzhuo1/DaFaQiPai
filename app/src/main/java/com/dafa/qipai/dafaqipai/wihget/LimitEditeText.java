package com.dafa.qipai.dafaqipai.wihget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 只能输入中文的editText
 * <p>
 * Created by shn on 2017/6/23.
 */
@SuppressLint("AppCompatCustomView")
public class LimitEditeText extends EditText implements TextWatcher {
    public LimitEditeText(Context context) {
        super(context);
    }

    public LimitEditeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        String str = this.getText().toString();

        if (str.length() > 0) {
            if (str.contains(" ")) {
                this.setText("");
            }

            // 只能输入汉字
            if (!str.toString().matches("[\u4e00-\u9fa5]+")) {
                this.setText("");
            }

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
