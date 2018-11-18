package com.dafa.qipai.dafaqipai.fra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.HomeAdapter;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.youxi.TiyuWebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TiyuFragment extends LazyLoadFragment {
    @BindView(R.id.listView)
    RecyclerView listView;
    Unbinder unbinder;

    @Override
    public int getLayout() {
        return R.layout.fragment_qipai;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


//        qipai.setBackgroundResource(R.mipmap.qipaigame_press);


        listView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<HomeItemDto> dtos = new ArrayList<>();

        dtos.add(new HomeItemDto(1, R.mipmap.tizuqiu));   // bbin


        HomeAdapter adapter = new HomeAdapter(getActivity(), dtos, R.layout.item_tiyu);
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {

                AppUtils.showToast(context, "暂未开放");

                int position = listView.getChildAdapterPosition(view);

                Intent intent2 = new Intent(getActivity(), TiyuWebViewActivity.class);

                getActivity().startActivity(intent2);
            }

            @Override
            public void onItemLongClick(View view) {

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
    public boolean getUserVisibleHint() {
        runLayoutAnimation(listView);
        return super.getUserVisibleHint();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        runLayoutAnimation(listView);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
