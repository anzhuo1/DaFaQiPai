package com.dafa.qipai.dafaqipai.geren;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.UserBaseSession;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.tixian.TiXianActivity;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DengluRizhiFragment extends LazyLoadFragment {


    Unbinder unbinder;

    @BindView(R.id.shijian)
    TextView shijian;

    @Override
    public int getLayout() {
        return R.layout.fragment_denglurizhi;
    }


    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

    }

    @Override
    public void loadData() {

        loadMineInfo();
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


    private void loadMineInfo() {

        OkGo.post(ApiConstant.API_DOMAIN + "member/getUserBaseInfo.json")
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("token", UserUtil.getToken(getActivity()))
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("个人资料1", json);
                        UserBaseSession doSessionInfo = GsonUtil.GsonToBean(json, UserBaseSession.class);
                        if (doSessionInfo.getResult() == 1) {

                            shijian.setText("上次登录时间：" + DateUtil.getCurrentTimeSecond(doSessionInfo.getLastLoginTime()) + "");


                        } else {

                        }
                    }

                });
    }

}
