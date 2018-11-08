package com.dafa.qipai.dafaqipai.geren;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongZhiAdapter;
import com.dafa.qipai.dafaqipai.adapter.TiXianAdapter;
import com.dafa.qipai.dafaqipai.bean.ChongZHiJiLuDo;
import com.dafa.qipai.dafaqipai.bean.TiXIanJiLuDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ApiUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChongTiFragment extends LazyLoadFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.leixing)
    TextView leixing;
    @BindView(R.id.zhuangtai)
    TextView zhuangtai;
    Unbinder unbinder;


    //0充值  1提现
    private int mLeixing = 0;


    private String[] mZhuangtai = {"任意", "正在入款", "充值成功", "充值失败"};


    private int pageNum = 1;
    private Date startTime;
    private Date endTime;
    private int CHAXUN_FLAG = 2;    //查询的标记
    private Integer status;
    private int total;
    private Long mType;


    private TiXianAdapter chongTiAdapteradapter;


    @Override
    public int getLayout() {
        return R.layout.fragment_chongti;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


    }

    @Override
    public void loadData() {


        loadChongZhi();

    }


    private void loadTiXian() {

        // 默认本月
        if (startTime == null) {
            startTime = DateUtil.getMonthStart();
        }

        if (endTime == null) {
            endTime = DateUtil.getMonthEnd();
        }

        OkGo.post(ApiConstant.API_DOMAIN + "/member/getWithdrawList.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("startTime", formatDate(startTime, "yyyy-MM-dd HH:mm:ss"))
                .params("endTime", formatDate(endTime, "yyyy-MM-dd HH:mm:ss"))
                .params("pageSize", 100)
                .params("pageIndex", pageNum)
                .params("status", "" + (null == status ? "" : status.intValue()))
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        try {
                            TiXIanJiLuDo baseDo = GsonUtil.GsonToBean(json, TiXIanJiLuDo.class);

                            if (baseDo.getResult() == 1) {
                                List<TiXIanJiLuDo.UserWithdrawListBean> userWithdrawList = baseDo.getUserWithdrawList();

                                recyclerview.setLayoutManager(new LinearLayoutManager(context));
                                TiXianAdapter adapter = new TiXianAdapter(context, userWithdrawList, R.layout.item_chongti);
                                recyclerview.setAdapter(adapter);

                                adapter.notifyDataSetChanged();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                });

    }

    private void loadChongZhi() {


        // 默认本月
        if (startTime == null) {
            startTime = DateUtil.getMonthStart();
        }

        if (endTime == null) {
            endTime = DateUtil.getMonthEnd();
        }

        ApiUtils.getInstance().getDepositList(
                UserUtil.getUserID(getActivity()),
                UserUtil.getToken(getActivity()),
                startTime,
                endTime,
                pageNum,
                100,
                status,
                mType,
                new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        try {
                            ChongZHiJiLuDo baseDo = GsonUtil.GsonToBean(json, ChongZHiJiLuDo.class);
                            if (baseDo.getResult() == 1) {
                                List<ChongZHiJiLuDo.UserDepositListBean> userDepositList = baseDo.getUserDepositList();
                                recyclerview.setLayoutManager(new LinearLayoutManager(context));
                                ChongZhiAdapter adapter = new ChongZhiAdapter(context, userDepositList, R.layout.item_chongti);
                                recyclerview.setAdapter(adapter);

                                adapter.notifyDataSetChanged();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }
        );

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


    private String formatDate(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }

    @OnClick({R.id.leixing, R.id.zhuangtai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leixing:

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                //builder.setIcon(R.drawable.ic_launcher);
                //builder.setTitle("选择一个城市");
                String[] cities = {"充值记录", "提现记录"};

                builder.setItems(cities, (dialog, which) -> {
                    if (which == 0) {
                        mLeixing = 0;
                        mZhuangtai = new String[]{"任意", "正在入款", "充值成功", "充值失败"};

                        loadChongZhi();
                    } else {
                        loadTiXian();
                        mLeixing = 1;
                        mZhuangtai = new String[]{"任意", "正在出款", "出款成功", "出款拒绝"};
                    }

                    status = null;
                    zhuangtai.setText("任意");
                    leixing.setText(cities[which]);
                });


                builder.show();


                break;
            case R.id.zhuangtai:

                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                //builder.setIcon(R.drawable.ic_launcher);
                //builder.setTitle("选择一个城市");

                //2失败   1 成功
                builder2.setItems(mZhuangtai, (dialog, which) -> {
                    if (mLeixing == 0) {

                        if (which == 0) {
                            status = null;
                        }
                        if (which == 1) {
                            status = 0;
                        }
                        if (which == 2) {
                            status = 1;
                        }
                        if (which == 3) {
                            status = 2;
                        }

                        loadChongZhi();
                    } else {

                        if (which == 0) {
                            status = null;
                        }
                        if (which == 1) {
                            status = 0;
                        }
                        if (which == 2) {
                            status = 1;
                        }
                        if (which == 3) {
                            status = 2;
                        }

                        loadTiXian();

                    }
                    zhuangtai.setText(mZhuangtai[which]);
                });


                builder2.show();
                break;
        }
    }
}
