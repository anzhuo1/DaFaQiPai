package com.dafa.qipai.dafaqipai.geren;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.UserBaseSession;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.tixian.TiXianActivity;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GerenZiliFragment extends LazyLoadFragment {


    Unbinder unbinder;
    @BindView(R.id.id_nun)
    TextView idNun;
    @BindView(R.id.nicheng)
    TextView nicheng;
    @BindView(R.id.edit_nicheng)
    ImageView editNicheng;
    @BindView(R.id.b_zfb)
    TextView bZfb;
    @BindView(R.id.b_yhk)
    TextView bYhk;
    @BindView(R.id.nan)
    CheckBox nan;
    @BindView(R.id.nv)
    CheckBox nv;

    @Override
    public int getLayout() {
        return R.layout.fragment_gerenziliao;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


        nan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                nv.setChecked(false);
            } else {
                nv.setChecked(true);
            }
        });

        nv.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                nan.setChecked(false);
            } else {
                nan.setChecked(true);
            }
        });

    }

    @Override
    public void loadData() {

        OkGo.post(ApiConstant.API_DOMAIN + "member/getUserBaseInfo.json")
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("token", UserUtil.getToken(getActivity()))
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {

                        UserBaseSession doSessionInfo = GsonUtil.GsonToBean(json, UserBaseSession.class);
                        if (doSessionInfo.getResult() == 1) {

                            idNun.setText( doSessionInfo.getId()+"");
                            nicheng.setText(doSessionInfo.getNickname());

                            int sex = doSessionInfo.getSex();
                            if (sex == 1) {
                                nan.setChecked(true);
                                nv.setChecked(false);
                            } else {
                                nv.setChecked(true);
                                nan.setChecked(false);
                            }

                        }
                    }

                    @Override
                    protected void _onError(String error) {
                        super._onError(error);

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

    @OnClick({R.id.edit_nicheng, R.id.b_zfb, R.id.b_yhk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_nicheng:
                break;
            case R.id.b_zfb:
                gotoActivity(TiXianActivity.class);
                break;
            case R.id.b_yhk:
                gotoActivity(TiXianActivity.class);
                break;
        }
    }


}
