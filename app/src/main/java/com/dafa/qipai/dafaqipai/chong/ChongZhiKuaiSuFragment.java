package com.dafa.qipai.dafaqipai.chong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoKuaiChong;
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

public class ChongZhiKuaiSuFragment extends LazyLoadFragment {


    //   @BindView(R.id.listView)
    //    RecyclerView listView;
    Unbinder unbinder;
    @BindView(R.id.ok)
    Button ok;
    private String qkszkcqd;
    //    @BindView(R.id.tousu)
    //    View tousu;

    @Override
    public int getLayout() {
        return R.layout.fragment_chongzhi_kuaisu;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


        // listView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));


    }

    @Override
    public void loadData() {

        OkGo.post(ApiConstant.API_DOMAIN + "/member/getAppdepositChannel.json")
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("token", UserUtil.getToken(getActivity()))
                .tag(this)
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {

                        DoKuaiChong doKuaiChong = GsonUtil.GsonToBean(json, DoKuaiChong.class);
                        if (doKuaiChong.getResult() == 1) {
                            qkszkcqd = doKuaiChong.getQkszkcqd();

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


    @OnClick(R.id.ok)
    public void onViewClicked() {

        if (TextUtils.isEmpty(qkszkcqd)) {

            AppUtils.showToast(context, "快速充值暂不可用");

            return;
        }

        String urlstr = qkszkcqd;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(urlstr);
        intent.setData(content_url);
        startActivity(intent);

    }
}
