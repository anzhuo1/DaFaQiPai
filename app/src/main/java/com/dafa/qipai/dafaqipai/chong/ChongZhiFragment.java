package com.dafa.qipai.dafaqipai.chong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongZhiTongDaoAdapter;
import com.dafa.qipai.dafaqipai.bean.DOgetAppCzInfoResult;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.kongzue.dialog.v2.Notification;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChongZhiFragment extends LazyLoadFragment {


    @BindView(R.id.r1)
    RadioButton r1;
    @BindView(R.id.r2)
    RadioButton r2;
    @BindView(R.id.r3)
    RadioButton r3;
    @BindView(R.id.r4)
    RadioButton r4;
    @BindView(R.id.rv_tongdao)
    RecyclerView rvTongdao;
    Unbinder unbinder;
    @BindView(R.id.money)
    EditText money;
    @BindView(R.id.qingchu)
    TextView qingchu;
    @BindView(R.id.tijiao)
    TextView tijiao;
    private ChongZhiTongDaoAdapter adapter;

    private int positon = 0;

    private String moneys;
    private List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList;

    @Override
    public int getLayout() {
        return R.layout.fragment_chongzhi;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

    }

    @Override
    public void loadData() {

        rvTongdao.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));

        czList = MyApp.czList;

        adapter = new ChongZhiTongDaoAdapter(context, czList, R.layout.item_tongdao);

        rvTongdao.setAdapter(adapter);


        czList.get(0).setCheck(true);

        adapter.setOnItemClickListener(new ChongZhiTongDaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                int position = rvTongdao.getChildAdapterPosition(view);

                // djsj(czList, position);
                ChongZhiFragment.this.positon = position;

                for (DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean : czList) {
                    bean.setCheck(false);
                }
                DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean = czList.get(position);
                bean.setCheck(true);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });


    }

    private void djsj(List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList, int i) {

        moneys = money.getText().toString();

        if (moneys.isEmpty() || moneys.equals("0")) {
            Toast.makeText(context, "充值金额不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        double minMoney = czList.get(i).getMinMoney();
        double maxMoney = czList.get(i).getMaxMoney();

        Double aDouble = Double.valueOf(moneys);

        if (maxMoney != 0) {
            if (aDouble < minMoney || aDouble > maxMoney) {

                Notification.show(getActivity(), 0, "",
                        "该充值方式\n支持最小充值金额为" + minMoney + "元\n支持最大充值金额为" +
                                maxMoney + "元\n请更换其他充值方式", Notification.SHOW_TIME_SHORT, Notification.TYPE_ERROR);

                return;
            }
        }


        if (czList.get(i).getPayType() == 8) {  //微信转扫码
//                    Intent intent = new Intent();
//                    intent.setClass(context, WxSaoMaActivity.class);
//                    intent.putExtra("name", czList.get(i).getName());
//                    intent.putExtra("type", czList.get(i).getPayType());
//                    startActivity(intent);

        }


        if (czList.get(i).getPayType() == 9) {  //微信转银行卡
            Intent intent = new Intent();
            intent.setClass(context, WeiXin4BackActivity.class);
            intent.putExtra("name", czList.get(i).getName());
            intent.putExtra("money", moneys);
            intent.putExtra("payType", czList.get(i).getPayType());
            startActivity(intent);

        }

        if (czList.get(i).getPayType() == 11) {  //支付宝转银行卡
            Intent intent = new Intent();
            intent.setClass(context, WeiXin4BackActivity.class);
            intent.putExtra("name", czList.get(i).getName());
            intent.putExtra("money", moneys);
            intent.putExtra("payType", czList.get(i).getPayType());
            startActivity(intent);

        }


        if (czList.get(i).getPayType() == 1) {  //微信

            if (czList.get(i).getType() == 1) {//转账
                Bundle bundle = new Bundle();
                bundle.putString("name", czList.get(i).getName());
                bundle.putString("paytype", czList.get(i).getPayType() + "");
                bundle.putString("money", moneys);
                // gotoActivity(WeiXinRechargeActivity.class, false, bundle);
            }
            if (czList.get(i).getType() == 2) {//支付
                zhifu(i, czList);
            }


        }


        if (czList.get(i).getPayType() == 3) {  //qq

            if (czList.get(i).getType() == 1) {//转账
                Bundle bundle = new Bundle();
                bundle.putString("name", czList.get(i).getName());
                bundle.putString("paytype", czList.get(i).getPayType() + "");
                bundle.putString("money", moneys);
                //gotoActivity(QqRechargeActivity.class, false, bundle);
            }
            if (czList.get(i).getType() == 2) {//支付
                zhifu(i, czList);
            }


        }

        if (czList.get(i).getPayType() == 2) {  //支付宝

            if (czList.get(i).getType() == 1) {//转账
                Bundle bundle = new Bundle();
                bundle.putString("name", czList.get(i).getName());
                bundle.putString("paytype", czList.get(i).getPayType() + "");
                bundle.putString("money", moneys);
                //  gotoActivity(ZhiFuBaoRechargeActivity.class, false, bundle);
            }
            if (czList.get(i).getType() == 2) {//支付
                zhifu(i, czList);
            }

        }

        if (czList.get(i).getPayType() == 4) {  //银行卡

            if (czList.get(i).getType() == 1) {//转账
                Bundle bundle = new Bundle();
                bundle.putString("name", czList.get(i).getName());
                bundle.putString("type", czList.get(i).getPayType() + "");
                gotoActivity(YinHangKaZhuanZhangActivity.class, false, bundle);
            }
            if (czList.get(i).getType() == 2) {//支付
                zhifu(i, czList);
            }

        }

        if (czList.get(i).getPayType() == 5) {  //jd

            if (czList.get(i).getType() == 1) {//转账
                Bundle bundle = new Bundle();
                bundle.putString("name", czList.get(i).getName());
                bundle.putString("paytype", czList.get(i).getPayType() + "");
                bundle.putString("money", moneys);
                //gotoActivity(JdRechargeActivity.class, false, bundle);
            }
            if (czList.get(i).getType() == 2) {//支付
                zhifu(i, czList);
            }

        }
        if (czList.get(i).getPayType() == 6) {  //百度

            if (czList.get(i).getType() == 1) {//转账
                Bundle bundle = new Bundle();
                bundle.putString("name", czList.get(i).getName());
                bundle.putString("paytype", czList.get(i).getPayType() + "");
                bundle.putString("money", moneys);
                //gotoActivity(BaiDuRechargeActivity.class, false, bundle);
            }
            if (czList.get(i).getType() == 2) {//支付
                zhifu(i, czList);
            }

        }

    }

    /**
     * zhifui
     *
     * @param i
     * @param czList
     */
    private void zhifu(int i, List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList) {
        Bundle bundle = new Bundle();
        bundle.putInt("payOnlineId", czList.get(i).getPayOnlineId());
        bundle.putInt("appMode", czList.get(i).getAppMode());
        bundle.putDouble("minMoney", czList.get(i).getMinMoney());
        bundle.putDouble("maxMoney", czList.get(i).getMaxMoney());
        bundle.putString("url", czList.get(i).getUrl());
        bundle.putString("name", czList.get(i).getName());
        bundle.putString("money", moneys);
//        gotoActivity(WxZhifuActivity.class, false, bundle);

        goZhifu(czList.get(i).getUrl(), czList.get(i).getPayOnlineId() + "", moneys);
    }


    private void goZhifu(String url, String payOnlineId, String money) {

        String urlstr = url + "?uid=" + UserUtil.getUserID(context) +
                "&payOnlineId=" + payOnlineId + "&money=" + money;

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(urlstr);
        intent.setData(content_url);
        startActivity(intent);

    }


    @Override
    public boolean getUserVisibleHint() {

        try {
            for (DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean : czList) {
                bean.setCheck(false);
            }
            czList.get(0).setCheck(true);
            adapter.notifyDataSetChanged();
            positon=0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.getUserVisibleHint();


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        try {
            for (DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean : czList) {
                bean.setCheck(false);
            }
            czList.get(0).setCheck(true);
            adapter.notifyDataSetChanged();
            positon=0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.qingchu, R.id.r1, R.id.r2, R.id.r3, R.id.r4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qingchu:
                money.setText("");
                moneys = "";
                break;
            case R.id.r1:
                money.setText("6");
                moneys = "6";
                break;
            case R.id.r2:
                money.setText("100");
                moneys = "";
                break;
            case R.id.r3:
                money.setText("500");
                moneys = "500";
                break;
            case R.id.r4:
                money.setText("1000");
                moneys = "1000";
                break;
        }
    }

    @OnClick(R.id.tijiao)
    public void onViewClicked() {
        djsj(czList, positon);
    }
}
