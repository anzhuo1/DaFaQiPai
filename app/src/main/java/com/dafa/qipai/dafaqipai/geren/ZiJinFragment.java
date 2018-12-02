package com.dafa.qipai.dafaqipai.geren;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongZhiAdapter;
import com.dafa.qipai.dafaqipai.adapter.TiXianAdapter;
import com.dafa.qipai.dafaqipai.adapter.ZiJinAdapter;
import com.dafa.qipai.dafaqipai.bean.ChongZHiJiLuDo;
import com.dafa.qipai.dafaqipai.bean.CoinOperEnumBean;
import com.dafa.qipai.dafaqipai.bean.ZiJinJiLuDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ApiUtils;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.Select;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ZiJinFragment extends LazyLoadFragment {


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

    private int totalPageNum;

    private String mType = "";    //方式
    private List<CoinOperEnumBean.CoinOperEntity> entityList;


    @Override
    public int getLayout() {

        return R.layout.fragment_zijin;
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


        OkGo.get(ApiConstant.API_DOMAIN + "/app/getCoinOperEnumList.json")
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("tyep", json);

                        CoinOperEnumBean bean = GsonUtil.GsonToBean(json, CoinOperEnumBean.class);
                        if (bean.getResult() == 1) {
                            entityList = bean.getEntityList();

                            loadChongZhi();

                        }
                    }
                });

    }


    private void loadChongZhi() {

        OkGo.post(ApiConstant.API_DOMAIN + "/member/getLogUserCoinList.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("startTime", formatDate(startTime, "yyyy-MM-dd HH:mm:ss"))
                .params("endTime", formatDate(endTime, "yyyy-MM-dd HH:mm:ss"))
                .params("pageSize", 4)
                .params("pageIndex", pageNum)
                .params("type", mType)
                .execute(new OkGoCallBack(getActivity(), false) {

                    @Override
                    protected void _onNext(String json) {
                        System.out.println(json);
                        ZiJinJiLuDo baseDo = GsonUtil.GsonToBean(json, ZiJinJiLuDo.class);
                        if (baseDo.getResult() == 1) {

                            List<ZiJinJiLuDo.LogUserCoinListBean> coinList = baseDo.getLogUserCoinList();

                            recyclerview.setLayoutManager(new LinearLayoutManager(context));
                            ZiJinAdapter adapter = new ZiJinAdapter(entityList, context, coinList, R.layout.item_zijin);
                            recyclerview.setAdapter(adapter);


                            int total = baseDo.getTotal();
                            //indexPage = baseDo.getPageNum();

                            totalPageNum = (total + 4 - 1) / 4;


                            yema.setText(pageNum + "/" + totalPageNum);

                            adapter.notifyDataSetChanged();

                        }

                    }
                });


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
                Select.selectYmdHm(getActivity(), (date, v) -> {

                    String format5 = DateUtil.format3(date);

                    startTime = date;
                    kaishi.setText(format5 + " 00:00");

                    pageNum = 1;
                    loadChongZhi();

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

                if (entityList == null) {
                    return;
                }

                for (CoinOperEnumBean.CoinOperEntity coinOperEntity : entityList) {
                    //if(!coinOperEntity.getName().contains("转")){
                    mData.add(coinOperEntity.getName());

                    //}
                }

                mData.add(0, "任意");


                Select.selectOption(getActivity(), "选择方式", mData, (options1, options2, options3, v) -> {

                    fangshi.setText(mData.get(options1));

                    if (options1 == 0) {
                        mType = null;
                    } else {

                        mType = entityList.get(options1 - 1).getId() + "";

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

                if (pageNum == 0 || pageNum == 1) {
                    AppUtils.showToast(context, "已是第一页");
                    return;
                }

                pageNum -= 1;

                loadChongZhi();
                break;
            case R.id.ac_xia:

                if (pageNum == totalPageNum || totalPageNum == 1 || totalPageNum == 0) {
                    AppUtils.showToast(context, "已是最后一页");
                    return;
                }

                pageNum += 1;

                loadChongZhi();
                break;
        }
    }


}
