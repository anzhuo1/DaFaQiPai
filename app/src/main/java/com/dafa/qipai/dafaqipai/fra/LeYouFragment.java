package com.dafa.qipai.dafaqipai.fra;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.HomeAdapter;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.Doleyou;
import com.dafa.qipai.dafaqipai.bean.LeYouLei;
import com.dafa.qipai.dafaqipai.bean.Qipai;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.LoginActivity;
import com.dafa.qipai.dafaqipai.youxi.LeYouWebAppActivity;
import com.dafa.qipai.dafaqipai.youxi.QiPaiWebAppActivity;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LeYouFragment extends LazyLoadFragment {
    @BindView(R.id.listView)
    RecyclerView listView;
    Unbinder unbinder;
    private List<HomeItemDto> dtos;


    @Override
    public int getLayout() {
        return R.layout.fragment_qipai;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

//        qipai.setBackgroundResource(R.mipmap.qipaigame_press);

        listView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.HORIZONTAL));

        dtos = new ArrayList<>();

        dtos.add(new HomeItemDto(6502, R.mipmap.ly_3g3x));           //三公
        dtos.add(new HomeItemDto(6011, R.mipmap.ly_13shui3x));       //13水
        dtos.add(new HomeItemDto(6012, R.mipmap.ly_28gang3x));      // 28缸
        dtos.add(new HomeItemDto(6666, R.mipmap.ly_dzpk3x));         //  欢乐德州
        dtos.add(new HomeItemDto(6014, R.mipmap.ly_jxzjh3x));        //  急速扎金花

        dtos.add(new HomeItemDto(6501, R.mipmap.ly_qann3x));         //   抢庄牛牛
        dtos.add(new HomeItemDto(6013, R.mipmap.ly_qzp93x));             //抢庄牌九
        dtos.add(new HomeItemDto(6005, R.mipmap.ly_shaibao3x));      // 晒宝
        dtos.add(new HomeItemDto(6504, R.mipmap.ly_tbnn3));         //   同比牛牛
        dtos.add(new HomeItemDto(6302, R.mipmap.ly_zjh3));           //  经典炸金花

        dtos.add(new HomeItemDto(6303, R.mipmap.ly_suoha));        //  梭哈
        dtos.add(new HomeItemDto(6002, R.mipmap.ly_hjgd));           //  皇家宫殿
        dtos.add(new HomeItemDto(6004, R.mipmap.ly_hjdz));           //  皇家德州
        dtos.add(new HomeItemDto(6009, R.mipmap.ly_dznz));        //  德州牛仔
        dtos.add(new HomeItemDto(6301, R.mipmap.ly_ddz));            //  斗地主
        dtos.add(new HomeItemDto(6003, R.mipmap.ly_brnn));        //  百人牛牛
        dtos.add(new HomeItemDto(6006, R.mipmap.ly_bjl));         //  百家乐


        HomeAdapter adapter = new HomeAdapter(getActivity(), dtos, R.layout.item_home);
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {

                if (!UserUtil.isLoginApp(context)) {
                    gotoActivity(LoginActivity.class);
                    return;
                }


                OkGo.post(ApiConstant.API_DOMAIN + "/wallet/walletToLy.json")
                        .params("token", UserUtil.getToken(context))
                        .params("uid", UserUtil.getUserID(context))
                        //.params("money",10)
                        .execute(new OkGoCallBack(getActivity(), true) {
                            @Override
                            protected void _onNext(String s) {
                                BaseDo baseDo = GsonUtil.GsonToBean(s, BaseDo.class);
                                if (baseDo.getResult() == 1) {


                                } else {

//                                    new BaseDialog(getActivity(), baseDo.getDescription(), "取消", "确定") {
//                                        @Override
//                                        public void btn1DoThing(Dialog mDialog) {
//
//                                        }
//
//                                        @Override
//                                        public void btn2DoThing(Dialog mDialog) {
//
//                                        }
//                                    }.show();

                                }
                            }
                        });


                int position = listView.getChildAdapterPosition(view);


                OkGo.post(ApiConstant.API_DOMAIN + "/leyou/loginLeyouGame.json")
                        .tag(this)
                        // .params("clientType", "Android")
                        .params("serviceId", dtos.get(position).getId())
                        .params("return_url", "http://www.niubi.com")
                        .params("token", UserUtil.getToken(context))
                        .params("uid", UserUtil.getUserID(context))
                        .execute(new OkGoCallBack(getActivity(), true) {
                            @Override
                            protected void _onNext(String json) {
                                Doleyou qipai = GsonUtil.GsonToBean(json, Doleyou.class);
                                if (qipai.getResult() == 1) {


                                    String url = qipai.getGame_url();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", url);

                                    gotoActivity(LeYouWebAppActivity.class, false, bundle);


                                } else {
                                    //todo

                                    // {"result":0,"description":"开元棋牌维护中!"}

                                    new BaseDialog(getActivity(), qipai.getDescription(), "取消", "确定") {
                                        @Override
                                        public void btn1DoThing(Dialog mDialog) {

                                        }

                                        @Override
                                        public void btn2DoThing(Dialog mDialog) {

                                        }
                                    }.show();


                                }

                            }


                        });


//                OkGo.post(ApiConstant.API_DOMAIN + "/leyou/getClassification.json")
//                        .params("token", UserUtil.getToken(context))
//                        .params("uid", UserUtil.getUserID(context))
//                        .params("leyouGt", "poker")
//                        .execute(new OkGoCallBack(getActivity(), true) {
//                            @Override
//                            protected void _onNext(String s) {
//
//                                LeYouLei leYouLei = GsonUtil.GsonToBean(s, LeYouLei.class);
//                                List<LeYouLei.GameListBean> gameList = leYouLei.getGameList();
//
//                                for (LeYouLei.GameListBean bean : gameList) {
//                                    if (Integer.parseInt(bean.getServiceId()) == dtos.get(position).getId()) {
//
//                                        String gameUrl = bean.getGameUrl();
//
//
//
//
//
//                                    }
//                                }
//
//                            }
//                        });


//                MyApp.type = 1;
//
//                int position = listView.getChildAdapterPosition(view);
//
//                String str = dtos.get(position).getId() + "";
//                Bundle bundle = new Bundle();
//                bundle.putString("url", str);
//                gotoActivity(QiPaiWebAppActivity.class, false, bundle);
            }

            @Override
            public void onItemLongClick(View view) {

//                if (!UserUtil.isLoginApp(getActivity())) {
//                    gotoActivity(CaiShuLoginActivity.class);
//                    return;
//                }


//                if (App.isSHIWAN) {
//
//                    Toast.makeText(context, "无权访问", Toast.LENGTH_SHORT).show();
//                    return;
//                }


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

    private void runLayoutAnimation(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        try {
            // final Context context = recyclerView.getContext();
            final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);

            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

}
