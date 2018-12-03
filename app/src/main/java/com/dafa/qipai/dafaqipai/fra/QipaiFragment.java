package com.dafa.qipai.dafaqipai.fra;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.HomeAdapter;
import com.dafa.qipai.dafaqipai.bean.Qipai;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.LoginActivity;
import com.dafa.qipai.dafaqipai.youxi.QiPaiWebAppActivity;
import com.kongzue.dialog.v2.WaitDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

public class QipaiFragment extends LazyLoadFragment {
    @BindView(R.id.listView)
    RecyclerView listView;
    Unbinder unbinder;
    private List<HomeItemDto> dtos;


    private boolean canGoto = true;

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

        dtos.add(new HomeItemDto(620, R.mipmap.dezhou));
        dtos.add(new HomeItemDto(880, R.mipmap.hlhb));      //

        dtos.add(new HomeItemDto(720, R.mipmap.gang28));
        dtos.add(new HomeItemDto(870, R.mipmap.tbniuniu));

        dtos.add(new HomeItemDto(830, R.mipmap.qznn));
        dtos.add(new HomeItemDto(630, R.mipmap.sss));      //

        dtos.add(new HomeItemDto(220, R.mipmap.zhajinhua));
        dtos.add(new HomeItemDto(860, R.mipmap.sangong));      //

        dtos.add(new HomeItemDto(610, R.mipmap.ddz));
        dtos.add(new HomeItemDto(730, R.mipmap.qzpai9));      //


        dtos.add(new HomeItemDto(600, R.mipmap.esyd));      //
        dtos.add(new HomeItemDto(900, R.mipmap.yzlh));      //


        dtos.add(new HomeItemDto(230, R.mipmap.jszjh));
        dtos.add(new HomeItemDto(390, R.mipmap.shelm));      //


        dtos.add(new HomeItemDto(380, R.mipmap.xingyun5z));      //

        dtos.add(new HomeItemDto(910, R.mipmap.baijiale));


        HomeAdapter adapter = new HomeAdapter(getActivity(), dtos, R.layout.item_home);
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {

                if (!UserUtil.isLoginApp(context)) {
                    gotoActivity(LoginActivity.class);
                    return;
                }


                int position = listView.getChildAdapterPosition(view);

                OkGo.post(ApiConstant.API_DOMAIN + "/chess/getChessUrl2.json")
                        .tag(this)
                        .params("clientType", "Android")
                        .params("KindID", dtos.get(position).getId())
                        .params("token", UserUtil.getToken(context))
                        .params("uid", UserUtil.getUserID(context))
                        .execute(new OkGoCallBack(getActivity(), true) {


                            @Override
                            protected void _onNext(String json) {

                                System.out.println("_onNext");
                                Qipai qipai = GsonUtil.GsonToBean(json, Qipai.class);
                                if (qipai.getResult() == 1) {

                                    System.out.println("1");

                                    String url = qipai.getLoginUrl();


                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", url);
                                    gotoActivity(QiPaiWebAppActivity.class, false, bundle);


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

                            @Override
                            public void onAfter(@Nullable String s, @Nullable Exception e) {
                                super.onAfter(s, e);

                                System.out.println("onAfter");
                            }
                        });


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
