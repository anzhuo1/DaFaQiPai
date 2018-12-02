package com.dafa.qipai.dafaqipai.baoxianxiaong;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.CunQuAdapter;
import com.dafa.qipai.dafaqipai.bean.DoMingXi;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CQMXFragment extends LazyLoadFragment {


    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.shang)
    TextView shang;
    @BindView(R.id.yema)
    TextView yema;
    @BindView(R.id.xia)
    TextView xia;

    private int page = 1;
    private int total;
    private int indexPage;


    @Override
    public int getLayout() {
        return R.layout.fragment_mingxi;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

    }

    @Override
    public void loadData() {

        load(1);
    }

    private void load(int pageIndex) {

        OkGo.post(ApiConstant.API_DOMAIN + "/safed/detailSelet.json")
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("pageIndex", pageIndex)
                //.params("type", "out")
                .params("pageSize", 5)
                .execute(new OkGoCallBack(getActivity(), true) {
                    @Override
                    protected void _onNext(String json) {

                        DoMingXi doMingXi = GsonUtil.GsonToBean(json, DoMingXi.class);

                        if (doMingXi.getResult() == 1) {

                            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

                            recyclerview.setAdapter(new CunQuAdapter(getActivity(), doMingXi.getList(), R.layout.item_mingxi));

                            total = doMingXi.getTotal();
                            indexPage = doMingXi.getIndexPage();
                            yema.setText(indexPage + "/" + total);

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


    @OnClick({R.id.shang, R.id.xia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shang:

                if (indexPage == 0 || indexPage == 1) {
                    AppUtils.showToast(context, "已是第一页");
                    return;
                }

                page -= 1;

                load(page);

                break;
            case R.id.xia:

                if (indexPage == total) {
                    AppUtils.showToast(context, "已是最后一页");
                    return;
                }

                page += 1;

                load(page);

                break;
        }
    }


    @Override
    public boolean getUserVisibleHint() {

        page = 1;
        load(1);

        return super.getUserVisibleHint();

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
             page = 1;
            load(1);
        }
    }
}
