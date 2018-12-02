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
import com.dafa.qipai.dafaqipai.adapter.TiXianAdapter;
import com.dafa.qipai.dafaqipai.adapter.YouxiAdapter;
import com.dafa.qipai.dafaqipai.bean.DoYuxoi;
import com.dafa.qipai.dafaqipai.bean.TiXIanJiLuDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.Select;
import com.lzy.okgo.OkGo;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class YouXiJiluFragment extends LazyLoadFragment {


    Unbinder unbinder;
    @BindView(R.id.kaishi)
    TextView kaishi;
    @BindView(R.id.jieshu)
    TextView jieshu;
    @BindView(R.id.leixing)
    TextView leixing;
    @BindView(R.id.zhuangtai)
    TextView zhuangtai;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ac_shang)
    TextView acShang;
    @BindView(R.id.yema)
    TextView yema;
    @BindView(R.id.ac_xia)
    TextView acXia;


    private Date startTime;
    private Date endTime;

    private int pageNum = 1;


    private int total;
    // private int indexPage;


    private int gameType = 1;    //方式 游戏类别(1-开元棋牌 2-bbin 3-ag 4-乐游)


    @Override
    public int getLayout() {
        return R.layout.fragment_youxijilu;
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


    @OnClick({R.id.kaishi, R.id.jieshu, R.id.leixing, R.id.ac_shang, R.id.ac_xia})
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
            case R.id.leixing:

                ArrayList<String> mData = new ArrayList<>();

                mData.add("开元棋牌");
                mData.add("bbin视讯");
                mData.add("ag视讯");
                mData.add("乐游棋牌");


                Select.selectOption(getActivity(), "选择方式", mData, (options1, options2, options3, v) -> {
                    leixing.setText(mData.get(options1));

                    gameType = options1 + 1;

                    pageNum = 1;
                    loadChongZhi();


                });

                break;
            case R.id.ac_shang:

                if (pageNum == 0 || pageNum == 1) {
                    AppUtils.showToast(context, "已是第一页");
                    return;
                }

                pageNum -= 1;

                loadChongZhi();

                break;
            case R.id.ac_xia:


                if (pageNum == total || total == 1 || total == 0) {
                    AppUtils.showToast(context, "已是最后一页");
                    return;
                }

                pageNum += 1;


                loadChongZhi();
                break;

        }
    }


    private void loadChongZhi() {


        OkGo.post(ApiConstant.API_DOMAIN + "/chess/chessCpzdRefactoring.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("startTime", formatDate(startTime, "yyyy-MM-dd HH:mm:ss"))
                .params("endTime", formatDate(endTime, "yyyy-MM-dd HH:mm:ss"))
                .params("pageSize", 4)
                .params("pageIndex", pageNum)
                .params("gameName", "")
                .params("number", "")
                .params("gameType", gameType)
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        try {
                            DoYuxoi baseDo = GsonUtil.GsonToBean(json, DoYuxoi.class);

                            if (baseDo.getResult() == 1) {
                                List<DoYuxoi.ListBean> beans = baseDo.getList();
                                if (beans == null) {
                                    return;
                                }

                                recyclerview.setLayoutManager(new LinearLayoutManager(context));
                                YouxiAdapter adapter = new YouxiAdapter(context, beans, R.layout.item_youxi);
                                recyclerview.setAdapter(adapter);


                                //indexPage = baseDo.getCNumber();
                                total = baseDo.getCPage();
                                yema.setText(pageNum + "/" + total);

                                adapter.notifyDataSetChanged();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                });


    }

    private String formatDate(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }
}
