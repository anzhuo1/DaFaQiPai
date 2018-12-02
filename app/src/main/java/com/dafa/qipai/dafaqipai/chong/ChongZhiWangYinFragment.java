package com.dafa.qipai.dafaqipai.chong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongZhiTongDaoAdapter;
import com.dafa.qipai.dafaqipai.adapter.WangYinAdapter;
import com.dafa.qipai.dafaqipai.bean.DOgetAppCzInfoResult;
import com.dafa.qipai.dafaqipai.bean.DoSysCard;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChongZhiWangYinFragment extends LazyLoadFragment {


    @BindView(R.id.rv_tongdao)
    RecyclerView rvTongdao;
    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    private ChongZhiTongDaoAdapter adapter;

    private int positon = 0;

    private String moneys;
    private List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.fragment_chongzhi_wangyin;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

    }

    @Override
    public void loadData() {

        loadWangYin();

        try {

            rvTongdao.setLayoutManager(new StaggeredGridLayoutManager(1,
                    StaggeredGridLayoutManager.HORIZONTAL));

//            czList = MyApp.czList;
//
//            czList.clear();

            DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean = new DOgetAppCzInfoResult.CzTypeListBean.CzListBean();
            bean.setPayType(4);

            czList.add(bean);

            adapter = new ChongZhiTongDaoAdapter(context, czList, R.layout.item_tongdao);

            rvTongdao.setAdapter(adapter);


            czList.get(0).setCheck(true);

            adapter.notifyDataSetChanged();


        } catch (Exception e) {
            e.printStackTrace();
        }

//        adapter.setOnItemClickListener(new ChongZhiTongDaoAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view) {
//                int position = rvTongdao.getChildAdapterPosition(view);
//
//                // djsj(czList, position);
//                ChongZhiWangYinFragment.this.positon = position;
//
//                for (DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean : czList) {
//                    bean.setCheck(false);
//                }
//                DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean = czList.get(position);
//                bean.setCheck(true);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onItemLongClick(View view) {
//
//            }
//        });


    }

    private void loadWangYin() {


        OkGo.post(ApiConstant.API_DOMAIN + "/member/getSystemBankCard.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {

                        try {
                            DoSysCard doSysCard = GsonUtil.GsonToBean(json, DoSysCard.class);
                            List<DoSysCard.BankcardListBean> bankcardList = doSysCard.getBankcardList();


                            recyclerview.setLayoutManager(new LinearLayoutManager(context));
                            WangYinAdapter adapter = new WangYinAdapter(context, bankcardList, R.layout.item_wangyin);
                            recyclerview.setAdapter(adapter);

                            adapter.setOnItemClickListener(new WangYinAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view) {

                                    int position = recyclerview.getChildAdapterPosition(view);

                                   // if (czList.get(i).getType() == 1) {//转账
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("position", position);
//                                        bundle.putString("type", czList.get(i).getPayType() + "");
                                        gotoActivity(YinHangKaZhuanZhangActivity.class, false, bundle);
                                   // }
//                                    if (czList.get(i).getType() == 2) {//支付
//                                        zhifu(i, czList);
//                                    }
                                }

                                @Override
                                public void onItemLongClick(View view) {

                                    AppUtils.copyToClipboard(getActivity(),bankcardList.get(0).getBankAccount());

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


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

//        try {
//            for (DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean : czList) {
//                bean.setCheck(false);
//            }
//            czList.get(0).setCheck(true);
//            adapter.notifyDataSetChanged();
//            positon = 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return super.getUserVisibleHint();


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

//        try {
//            for (DOgetAppCzInfoResult.CzTypeListBean.CzListBean bean : czList) {
//                bean.setCheck(false);
//            }
//            czList.get(0).setCheck(true);
//            adapter.notifyDataSetChanged();
//            positon = 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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


}
