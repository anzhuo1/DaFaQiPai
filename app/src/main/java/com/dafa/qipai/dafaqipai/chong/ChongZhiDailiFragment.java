package com.dafa.qipai.dafaqipai.chong;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongzhiDailiAdapter;
import com.dafa.qipai.dafaqipai.adapter.HomeAdapter;
import com.dafa.qipai.dafaqipai.bean.DoWeixin;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.glide.GlideUtil;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChongZhiDailiFragment extends LazyLoadFragment {


    @BindView(R.id.listView)
    RecyclerView listView;
    Unbinder unbinder;

    @Override
    public int getLayout() {
        return R.layout.fragment_chongzhi_daili;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


       // listView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        listView.setLayoutManager(gridLayoutManager);


        OkGo.post(ApiConstant.API_DOMAIN + "member/getPayOnline3.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("type", 1)
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("生产", json);
                        DoWeixin doWeixin = GsonUtil.GsonToBean(json, DoWeixin.class);
                        try {

                            List<DoWeixin.SkInfoListBean> skInfoList = doWeixin.getSkInfoList();

                            ChongzhiDailiAdapter adapter = new ChongzhiDailiAdapter(getActivity(), skInfoList, R.layout.item_daili);
                            listView.setAdapter(adapter);

                            adapter.setOnItemClickListener(new ChongzhiDailiAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view) {

                                    int position = listView.getChildAdapterPosition(view);

                                    //Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onItemLongClick(View view) {

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });




    }

    @Override
    public void loadData() {


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
