package com.dafa.qipai.dafaqipai.geren;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongZhiLeftAdapter;
import com.dafa.qipai.dafaqipai.bean.DOgetAppCzInfoResult;
import com.dafa.qipai.dafaqipai.chong.ChongZhiDailiFragment;
import com.dafa.qipai.dafaqipai.chong.ChongZhiFragment;
import com.dafa.qipai.dafaqipai.fra.DianZiFragment;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GerenActivity extends FragmentActivity {


    GerenZiliFragment gerenZiliFragment;

    ChongTiFragment chongTiFragment;


    @BindView(R.id.r1)
    RadioButton r1;
    @BindView(R.id.r2)
    RadioButton r2;
    @BindView(R.id.r3)
    RadioButton r3;
    @BindView(R.id.r4)
    RadioButton r4;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.bg)
    RelativeLayout bg;

    private List<DOgetAppCzInfoResult.CzTypeListBean> czTypeList;
    private ChongZhiLeftAdapter leftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_geren);
        ButterKnife.bind(this);
        AutoUtils.auto(this);

        ActivityContainer.getInstance().addActivity(this);


        FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();

        if (gerenZiliFragment == null) {
            gerenZiliFragment = new GerenZiliFragment();
            transaction4.add(R.id.framelayout, gerenZiliFragment);
        }
        hideFragment(transaction4);
        transaction4.show(gerenZiliFragment);
        transaction4.commit();

    }


    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (gerenZiliFragment != null) {
            transaction.hide(gerenZiliFragment);
        }
        if (chongTiFragment != null) {
            transaction.hide(chongTiFragment);
        }

//        if (tiyuFragment != null) {
//            transaction.hide(tiyuFragment);
//        }
//
//        if (dianZiFragment != null) {
//            transaction.hide(dianZiFragment);
//        }


    }

    @OnClick({R.id.r1, R.id.r2, R.id.r3, R.id.r4, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.r1:

                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();

                if (gerenZiliFragment == null) {
                    gerenZiliFragment = new GerenZiliFragment();
                    transaction4.add(R.id.framelayout, gerenZiliFragment);
                }
                hideFragment(transaction4);
                transaction4.show(gerenZiliFragment);
                transaction4.commit();

                break;
            case R.id.r2:
                break;
            case R.id.r3:

                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();

                if (chongTiFragment == null) {
                    chongTiFragment = new ChongTiFragment();
                    transaction1.add(R.id.framelayout, chongTiFragment);
                }
                hideFragment(transaction1);
                transaction1.show(chongTiFragment);
                transaction1.commit();
                break;
            case R.id.r4:
                break;
            case R.id.close:
                finish();
                break;
        }
    }
}
