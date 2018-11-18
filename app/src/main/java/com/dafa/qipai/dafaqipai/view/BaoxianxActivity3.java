package com.dafa.qipai.dafaqipai.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.baoxianxiaong.CunRuFragment;
import com.dafa.qipai.dafaqipai.tixian.BangKaFragment;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BaoxianxActivity3 extends BaseActivity {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.quchu)
    RadioButton quchu;
    @BindView(R.id.qunru)
    RadioButton qunru;
    @BindView(R.id.mingxi)
    RadioButton mingxi;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;

    CunRuFragment cunRuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_baoxianxiang3);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

        if (cunRuFragment == null) {
            cunRuFragment = new CunRuFragment();
            transaction2.add(R.id.framelayout, cunRuFragment);
        }
        hideFragment(transaction2);
        transaction2.show(cunRuFragment);
        transaction2.commit();


    }


    @OnClick(R.id.close)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.quchu, R.id.qunru, R.id.mingxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quchu:
                break;
            case R.id.qunru:
                break;
            case R.id.mingxi:
                break;
        }
    }



    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (cunRuFragment != null) {
            transaction.hide(cunRuFragment);
        }
//        if (tiXianFragment != null) {
//            transaction.hide(tiXianFragment);
//        }
//
//        if (tiyuFragment != null) {
//            transaction.hide(tiyuFragment);
//        }
//
//        if (dianZiFragment != null) {
//            transaction.hide(dianZiFragment);
//        }


    }
}
