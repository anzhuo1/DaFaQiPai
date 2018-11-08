package com.dafa.qipai.dafaqipai.tixian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.geren.GerenActivity;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TiXianActivity extends FragmentActivity {


    @BindView(R.id.bg)
    RelativeLayout bg;

    @BindView(R.id.qipai)
    RadioButton qipai;
    @BindView(R.id.zhenren)
    RadioButton zhenren;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.idnum)
    TextView idnum;
    @BindView(R.id.fuzhi)
    TextView fuzhi;
    @BindView(R.id.chongti)
    TextView chongti;
    @BindView(R.id.finish)
    TextView finish;


    BangKaFragment bangKaFragment;

    TiXianFragment tiXianFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        ActivityContainer.getInstance().addActivity(this);

        idnum.setText(MyApp.ID);

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

        if (bangKaFragment == null) {
            bangKaFragment = new BangKaFragment();
            transaction2.add(R.id.framelayout, bangKaFragment);
        }
        hideFragment(transaction2);
        transaction2.show(bangKaFragment);
        transaction2.commit();


    }


    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (bangKaFragment != null) {
            transaction.hide(bangKaFragment);
        }
        if (tiXianFragment != null) {
            transaction.hide(tiXianFragment);
        }
//
//        if (tiyuFragment != null) {
//            transaction.hide(tiyuFragment);
//        }
//
//        if (dianZiFragment != null) {
//            transaction.hide(dianZiFragment);
//        }


    }

    @OnClick({R.id.qipai, R.id.zhenren, R.id.fuzhi, R.id.chongti, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qipai:

                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                if (bangKaFragment == null) {
                    bangKaFragment = new BangKaFragment();
                    transaction2.add(R.id.framelayout, bangKaFragment);
                }
                hideFragment(transaction2);
                transaction2.show(bangKaFragment);
                transaction2.commit();

                break;
            case R.id.zhenren:

                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();

                if (tiXianFragment == null) {
                    tiXianFragment = new TiXianFragment();
                    transaction3.add(R.id.framelayout, tiXianFragment);
                }
                hideFragment(transaction3);
                transaction3.show(tiXianFragment);
                transaction3.commit();

                break;
            case R.id.fuzhi:
                AppUtils.copyToClipboard(getApplication(), MyApp.ID);
                break;
            case R.id.chongti:
                startActivity(new Intent(this, GerenActivity.class));
                break;
            case R.id.finish:
                finish();
                break;
        }
    }
}
