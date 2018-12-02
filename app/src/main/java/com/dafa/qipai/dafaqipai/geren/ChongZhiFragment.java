package com.dafa.qipai.dafaqipai.geren;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongZhiAdapter;
import com.dafa.qipai.dafaqipai.adapter.TiXianAdapter;
import com.dafa.qipai.dafaqipai.bean.ChongZHiJiLuDo;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ApiUtils;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.Select;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChongZhiFragment extends LazyLoadFragment {


    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ac_shang)
    TextView acShang;
    @BindView(R.id.yema)
    TextView yema;
    @BindView(R.id.ac_xia)
    TextView acXia;
    @BindView(R.id.kaishi)
    TextView kaishi;
    @BindView(R.id.jieshu)
    TextView jieshu;
    @BindView(R.id.fangshi)
    TextView fangshi;
    @BindView(R.id.zhuangtai)
    TextView zhuangtai;


    private Date startTime;
    private Date endTime;

    private int pageNum = 1;


    private int total;
    private int indexPage;


    private Integer status = 0;  //状态
    private int mType = 0;    //方式


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


        // 默认本月
        if (startTime == null) {
            startTime = DateUtil.getMonthStart();

            kaishi.setText(DateUtil.format5(startTime));
        }

        if (endTime == null) {
            endTime = DateUtil.getMonthEnd();

            jieshu.setText(DateUtil.format5(endTime));
        }

        loadChongZhi();

    }


//    private void loadTiXian() {
//
//        // 默认本月
//        if (startTime == null) {
//            startTime = DateUtil.getMonthStart();
//        }
//
//        if (endTime == null) {
//            endTime = DateUtil.getMonthEnd();
//        }
//
//        OkGo.post(ApiConstant.API_DOMAIN + "/member/getWithdrawList.json")
//                .tag(this)
//                .params("token", UserUtil.getToken(getActivity()))
//                .params("uid", UserUtil.getUserID(getActivity()))
//                .params("startTime", formatDate(startTime, "yyyy-MM-dd HH:mm:ss"))
//                .params("endTime", formatDate(endTime, "yyyy-MM-dd HH:mm:ss"))
//                .params("pageSize", 100)
//                .params("pageIndex", pageNum)
//                .params("status", "" + (null == status ? "" : status.intValue()))
//                .execute(new OkGoCallBack(getActivity(), false) {
//                    @Override
//                    protected void _onNext(String json) {
//                        try {
//                            TiXIanJiLuDo baseDo = GsonUtil.GsonToBean(json, TiXIanJiLuDo.class);
//
//                            if (baseDo.getResult() == 1) {
//                                List<TiXIanJiLuDo.UserWithdrawListBean> userWithdrawList = baseDo.getUserWithdrawList();
//
//                                recyclerview.setLayoutManager(new LinearLayoutManager(context));
//                                TiXianAdapter adapter = new TiXianAdapter(context, userWithdrawList, R.layout.item_chongti);
//                                recyclerview.setAdapter(adapter);
//
//                                adapter.notifyDataSetChanged();
//
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//
//                        }
//                    }
//                });
//
//    }

    private void loadChongZhi() {


        ApiUtils.getInstance().getDepositList(
                UserUtil.getUserID(getActivity()),
                UserUtil.getToken(getActivity()),
                startTime,
                endTime,
                pageNum,
                4,
                status,
                mType,
                new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        System.out.println(json);

                        ChongZHiJiLuDo baseDo = GsonUtil.GsonToBean(json, ChongZHiJiLuDo.class);
                        if (baseDo.getResult() == 1) {
                            List<ChongZHiJiLuDo.UserDepositListBean> userDepositList = baseDo.getUserDepositList();
                            recyclerview.setLayoutManager(new LinearLayoutManager(context));
                            ChongZhiAdapter adapter = new ChongZhiAdapter(context, userDepositList, R.layout.item_chongti);
                            recyclerview.setAdapter(adapter);


                            int total2 = baseDo.getTotal();
                            indexPage = baseDo.getPageNum();
                            total = 1 ;
                            yema.setText(indexPage + "/" + total);

                            adapter.notifyDataSetChanged();

                        }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//
//                        }
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

    @OnClick({R.id.kaishi, R.id.jieshu, R.id.fangshi, R.id.zhuangtai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kaishi:
                Select.selectYmdHm(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                        String format5 = DateUtil.format3(date);

                        startTime = date;
                        kaishi.setText(format5 + " 00:00");

                        pageNum = 1;
                        loadChongZhi();

                    }
                });

                break;
            case R.id.jieshu:

                Select.selectYmdHm(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                        String format5 = DateUtil.format3(date);

                        endTime = date;
                        jieshu.setText(format5 + " 00:00");

                        pageNum = 1;
                        loadChongZhi();

                    }
                });

                break;
            case R.id.fangshi:

                ArrayList<String> mData = new ArrayList<>();

                mData.add("任意");
                mData.add("银行转账");
                mData.add("支付宝转账");
                mData.add("微信转账");
                mData.add("财付通转账");
                mData.add("在线支付");

                Select.selectOption(getActivity(), "选择方式", mData, (options1, options2, options3, v) -> {
                    fangshi.setText(mData.get(options1));

                    mType = options1;

                    pageNum = 1;
                    loadChongZhi();


                });


                break;
            case R.id.zhuangtai:


                ArrayList<String> list = new ArrayList<>();

                list.add("任意");
                list.add("正在入款");
                list.add("充值成功");
                list.add("充值失败");


                Select.selectOption(getActivity(), "选择状态", list, (options1, options2, options3, v) -> {
                    zhuangtai.setText(list.get(options1));

                    status = options1;

                    if (options1 == 0) {
                        status = null;
                    }
                    if (options1 == 1) {
                        status = 0;
                    }
                    if (options1 == 2) {
                        status = 1;
                    }
                    if (options1 == 3) {
                        status = 2;
                    }

                    pageNum = 1;
                    loadChongZhi();

                });
                break;
        }
    }

    @OnClick({R.id.ac_shang, R.id.ac_xia})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.ac_shang:

                if (indexPage == 0 || indexPage == 1) {
                    AppUtils.showToast(context, "已是第一页");
                    return;
                }

                pageNum -= 1;

                loadChongZhi();
                break;
            case R.id.ac_xia:

                if (indexPage == total) {
                    AppUtils.showToast(context, "已是最后一页");
                    return;
                }

                pageNum += 1;

                loadChongZhi();
                break;
        }
    }


}
