package com.dafa.qipai.dafaqipai.fra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.dafa.qipai.dafaqipai.bean.DoZhenren;
import com.dafa.qipai.dafaqipai.bean.Qipai;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.EncodingUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.youxi.AGWebAppActivity;
import com.dafa.qipai.dafaqipai.youxi.BBINWebViewActivity;
import com.lzy.okgo.OkGo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

public class ZhenrenFragment extends LazyLoadFragment {
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


        listView.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));


        List<HomeItemDto> dtos = new ArrayList<>();

        dtos.add(new HomeItemDto(1, R.mipmap.bbin));   // bbin
        dtos.add(new HomeItemDto(1, R.mipmap.ag));     //ag


        HomeAdapter adapter = new HomeAdapter(getActivity(), dtos, R.layout.item_zhenren);
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {

                int position = listView.getChildAdapterPosition(view);

                if (position == 0) {

                    OkGo.post(ApiConstant.API_DOMAIN + "/chess/getBbinLoginUrl2.json")
                            .tag(this)
                            .params("clientType", 3)
                            .params("KindID", 1)
                            .params("token", UserUtil.getToken(getActivity()))
                            .params("uid", UserUtil.getUserID(getActivity()))
                            .execute(new OkGoCallBack(getActivity(), true) {
                                @Override
                                protected void _onNext(String json) {
                                    Qipai qipai = GsonUtil.GsonToBean(json, Qipai.class);
                                    String loginUrl = qipai.getLoginUrl();


                                    Intent intent2 = new Intent(context, BBINWebViewActivity.class);
                                    intent2.putExtra("url", loginUrl);
                                    intent2.putExtra("title", "BBIN视讯");
                                    startActivity(intent2);

                                }

                            });


                } else {

                    OkGo.post(ApiConstant.API_DOMAIN + "/chess/getAGLoginUrl2.json")
                            .tag(this)
                            .params("clientType", "Android")
                            .params("KindID", "")
                            .params("token", UserUtil.getToken(context))
                            .params("uid", UserUtil.getUserID(context))
                            .execute(new OkGoCallBack(getActivity(), true) {
                                @Override
                                protected void _onNext(String json) {
                                    DoZhenren qipai = GsonUtil.GsonToBean(json, DoZhenren.class);

                                    if (qipai.getResult() == 1) {

                                        String key = qipai.getKey();
                                        String params = qipai.getParams();
                                        String loginurl = qipai.getLoginUrl();




                                        Bundle bundle = new Bundle();
                                        bundle.putString("key", key);
                                        bundle.putString("params", params);
                                        bundle.putString("url", loginurl);
                                        gotoActivity(AGWebAppActivity.class, false, bundle);


                                    } else {


                                    }


                                }


                            });

                }


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
