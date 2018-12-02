package com.dafa.qipai.dafaqipai.chong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongZhiLeftAdapter;
import com.dafa.qipai.dafaqipai.bean.DOgetAppCzInfoResult;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.core.CommonConstant;
import com.dafa.qipai.dafaqipai.geren.GerenActivity;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseFragmentActivity;
import com.lzy.okgo.OkGo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChongzhiActivity extends BaseFragmentActivity {


    @BindView(R.id.left_select)
    RecyclerView leftSelect;
    @BindView(R.id.bg)
    RelativeLayout bg;

    ChongZhiDailiFragment dailiFragment;
    ChongZhiFragment chongZhiFragment;
    ChongZhiWangYinFragment wangYinFragment;
    ChongZhiKuaiSuFragment kuaiSuFragment;

    @BindView(R.id.idnum)
    TextView idnum;
    @BindView(R.id.fuzhi)
    TextView fuzhi;
    @BindView(R.id.chongti)
    TextView chongti;
    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;


    private List<DOgetAppCzInfoResult.CzTypeListBean> czTypeList;
    private ChongZhiLeftAdapter leftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_chongzhi);
        ButterKnife.bind(this);
        AutoUtils.auto(this);

        loadRechargeType();

        idnum.setText(MyApp.ID);

        ActivityContainer.getInstance().addActivity(this);

    }


    /*获取充值方式*/
    private void loadRechargeType() {


        OkGo.post(ApiConstant.API_DOMAIN + "/member/getAppCzInfo4.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("充值", json);

                        DOgetAppCzInfoResult dOgetAppCzInfoResult = GsonUtil.GsonToBean(json, DOgetAppCzInfoResult.class);
                        czTypeList = dOgetAppCzInfoResult.getCzTypeList();

                        if (dOgetAppCzInfoResult.getResult() != CommonConstant.SUCCESS_CODE) {
                            //Toast.makeText(RechargeActivity.this, "获取充值信息失败", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (czTypeList == null || czTypeList.size() == 0) {
                            // Toast.makeText(RechargeActivity.this, "获取充值信息失败", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        leftSelect.setLayoutManager(new LinearLayoutManager(ChongzhiActivity.this));

                        leftAdapter = new ChongZhiLeftAdapter(ChongzhiActivity.this, czTypeList, R.layout.item_left);

                        leftSelect.setAdapter(leftAdapter);

                        leftAdapter.setOnItemClickListener(new ChongZhiLeftAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view) {

                                int position = leftSelect.getChildAdapterPosition(view);

                                for (DOgetAppCzInfoResult.CzTypeListBean d : czTypeList) {
                                    d.setSelect(false);
                                }
                                czTypeList.get(position).setSelect(true);
                                leftAdapter.notifyDataSetChanged();


                                if (position == czTypeList.size() - 1) {

                                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

                                    if (dailiFragment == null) {
                                        dailiFragment = new ChongZhiDailiFragment();
                                        transaction2.add(R.id.framelayout, dailiFragment);
                                    }
                                    hideFragment(transaction2);
                                    transaction2.show(dailiFragment);
                                    transaction2.commit();


                                } else if (czTypeList.get(position).getName().contains("银行卡")) {


                                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

                                    if (wangYinFragment == null) {
                                        wangYinFragment = new ChongZhiWangYinFragment();
                                        transaction2.add(R.id.framelayout, wangYinFragment);
                                    }
                                    hideFragment(transaction2);
                                    transaction2.show(wangYinFragment);
                                    transaction2.commit();


                                } else if (czTypeList.get(position).getName().contains("快速")) {


                                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

                                    if (kuaiSuFragment == null) {
                                        kuaiSuFragment = new ChongZhiKuaiSuFragment();
                                        transaction2.add(R.id.framelayout, kuaiSuFragment);
                                    }
                                    hideFragment(transaction2);
                                    transaction2.show(kuaiSuFragment);
                                    transaction2.commit();


                                } else {

                                    List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList = czTypeList.get(position).getCzList();

                                    MyApp.czList.clear();
                                    MyApp.czList.addAll(czList);

                                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

                                    if (chongZhiFragment == null) {
                                        chongZhiFragment = new ChongZhiFragment();
                                        transaction2.add(R.id.framelayout, chongZhiFragment);
                                    }
                                    hideFragment(transaction2);
                                    transaction2.show(chongZhiFragment);
                                    transaction2.commit();

                                }


                            }

                            @Override
                            public void onItemLongClick(View view) {

                            }
                        });


                        List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList = czTypeList.get(0).getCzList();


                        initTongdao(czList);


                    }
                });

    }

    private void initTongdao(List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList) {

        //int position = leftSelect.getChildAdapterPosition(view);

        for (DOgetAppCzInfoResult.CzTypeListBean d : czTypeList) {
            d.setSelect(false);
        }
        czTypeList.get(0).setSelect(true);
        leftAdapter.notifyDataSetChanged();

        try {
            MyApp.czList.clear();
            MyApp.czList.addAll(czList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (!isFinishing()) {
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

                if (kuaiSuFragment == null) {
                    kuaiSuFragment = new ChongZhiKuaiSuFragment();
                    transaction2.add(R.id.framelayout, kuaiSuFragment);
                }
                hideFragment(transaction2);
                transaction2.show(kuaiSuFragment);
                transaction2.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (dailiFragment != null) {
            transaction.hide(dailiFragment);
        }
        if (chongZhiFragment != null) {
            transaction.hide(chongZhiFragment);
        }

        if (wangYinFragment != null) {
            transaction.hide(wangYinFragment);
        }

        if (kuaiSuFragment != null) {
            transaction.hide(kuaiSuFragment);
        }


    }

    @OnClick({R.id.fuzhi, R.id.chongti, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fuzhi:
                AppUtils.copyToClipboard(this, MyApp.ID);
                break;
            case R.id.chongti:
                Intent intent = new Intent(this, GerenActivity.class);
                intent.putExtra("key", "cz");
                startActivity(intent);
                break;
            case R.id.finish:
                finish();
                break;
        }
    }
}
