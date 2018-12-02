package com.dafa.qipai.dafaqipai.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.BackgroundMusic;
import com.dafa.qipai.dafaqipai.util.SPUtil;
import com.dafa.qipai.dafaqipai.util.SoundPoolUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.kongzue.dialog.v2.SelectDialog;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.math.BigDecimal.ROUND_HALF_UP;


public class SheZhiActivity extends BaseActivity {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.nicheng)
    TextView nicheng;
    @BindView(R.id.id_nun)
    TextView idNun;
    @BindView(R.id.qiehuan)
    TextView qiehuan;
    @BindView(R.id.seek)
    SeekBar seek;
    @BindView(R.id.seek2)
    SeekBar seek2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_shezhi);
        ButterKnife.bind(this);
        AutoUtils.auto(this);

        nicheng.setText(MyApp.NICHENG);
        idNun.setText(MyApp.ID);


        float y1 = (float) SPUtil.get(this, "y1", 0.5f);
        float y2 = (float) SPUtil.get(this, "y2", 0.5f);


        BigDecimal multiply = new BigDecimal(y1).multiply(new BigDecimal(10));

        seek.setProgress(multiply.intValue());


        BigDecimal multiply2 = new BigDecimal(y2).multiply(new BigDecimal(10));

        seek2.setProgress(multiply2.intValue());


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                BigDecimal bigDecimal1 = new BigDecimal(progress);
                BigDecimal bigDecimal2 = new BigDecimal(10);

                BigDecimal divide = bigDecimal1.divide(bigDecimal2, 1, ROUND_HALF_UP);

                float v = divide.floatValue();

                BackgroundMusic.getInstance(SheZhiActivity.this).setBackgroundVolume(v);

                SPUtil.put(SheZhiActivity.this, "y1", v);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                BigDecimal bigDecimal1 = new BigDecimal(progress);
                BigDecimal bigDecimal2 = new BigDecimal(10);

                BigDecimal divide = bigDecimal1.divide(bigDecimal2, 1, ROUND_HALF_UP);

                float v = divide.floatValue();


                SPUtil.put(SheZhiActivity.this, "y2", v);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    @OnClick({R.id.close, R.id.qiehuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.qiehuan:

                tuichu();

                break;
        }
    }


    private void tuichu() {

//        SelectDialog.show(SheZhiActivity.this, "提示", "确定退出吗", (dialog, which) -> {
//
//
//        });


        new BaseDialog(this, "确定退出吗？", "取消", "确定") {
            @Override
            public void btn1DoThing(Dialog mDialog) {
                mDialog.dismiss();
            }

            @Override
            public void btn2DoThing(Dialog mDialog) {


                mDialog.dismiss();
                SheZhiActivity.this.finish();
                UserUtil.loginOut(SheZhiActivity.this);


                ActivityContainer.getInstance().finishAllActivity();

                Intent intent = new Intent(SheZhiActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        }.show();


    }

}
