package com.dafa.qipai.dafaqipai.geren;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoBindStatus;
import com.dafa.qipai.dafaqipai.bean.UserBaseSession;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BandPhoneDialog;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.KefuActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GerenZiliFragment extends LazyLoadFragment {


   // Unbinder unbinder;
    @BindView(R.id.edit_password)
    TextView editPassword;
    @BindView(R.id.id_nun)
    TextView idNun;
    @BindView(R.id.copy_id)
    TextView copyId;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.band_phone)
    TextView bandPhone;
    @BindView(R.id.vip)
    TextView vip;
    @BindView(R.id.go_vip)
    TextView goVip;
    @BindView(R.id.dengl_time)
    TextView denglTime;
    @BindView(R.id.zhuce_time)
    TextView zhuceTime;
    @BindView(R.id.yinhangka)
    TextView yinhangka;
    //    @BindView(R.id.id_nun)
//    TextView idNun;
//    @BindView(R.id.nicheng)
//    TextView nicheng;
//    @BindView(R.id.edit_nicheng)
//    ImageView editNicheng;
//    @BindView(R.id.b_zfb)
//    TextView bZfb;
//    @BindView(R.id.b_yhk)
//    TextView bYhk;
//    @BindView(R.id.nan)
//    CheckBox nan;
//    @BindView(R.id.nv)
//    CheckBox nv;
    private UserBaseSession doSessionInfo;
    private boolean isBindTelephone;

    @Override
    public int getLayout() {
        return R.layout.fragment_gerenziliao;
    }


    private String sex;

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


    }

    @Override
    public void loadData() {


        OkGo.post(ApiConstant.API_DOMAIN + "member/getUserBaseInfo.json")
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("token", UserUtil.getToken(getActivity()))
                .execute(new OkGoCallBack(getActivity(), true) {
                    @Override
                    protected void _onNext(String json) {

                        try {
                            doSessionInfo = GsonUtil.GsonToBean(json, UserBaseSession.class);
                            if (doSessionInfo.getResult() == 1) {

                                idNun.setText(doSessionInfo.getId() + "");
                                name.setText(doSessionInfo.getNickname());
                                vip.setText(doSessionInfo.getLayerName());

                                denglTime.setText(DateUtil.format(new Date(doSessionInfo.getLastLoginTime())));
                                zhuceTime.setText(DateUtil.format(new Date(doSessionInfo.getRegisterTime())));
                                if (TextUtils.isEmpty(doSessionInfo.getTelephone())) {
                                    phone.setText("请绑定");
                                }else {
                                    phone.setText(doSessionInfo.getTelephone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));

                                }



                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onError(String error) {
                        super._onError(error);

                    }
                });


        OkGo.post(ApiConstant.API_DOMAIN + "/member/getBindStatus.json")
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("token", UserUtil.getToken(getActivity()))
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("个人资料", json);
                        DoBindStatus doBindStatus = GsonUtil.GsonToBean(json, DoBindStatus.class);
                        if (doBindStatus.getResult() == 1) {
                            isBindTelephone = doBindStatus.isIsBindTelephone();
                            if (isBindTelephone) {
                                bandPhone.setVisibility(View.GONE);
                            } else {
                                phone.setText("请绑定");
                            }

                        }
                    }

                });


    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @OnClick({R.id.edit_password, R.id.copy_id, R.id.band_phone, R.id.go_vip, R.id.yinhangka})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_password:

                gotoActivity(EditPasswordActivity.class);

                break;
            case R.id.copy_id:
                AppUtils.copyToClipboard(getActivity(), idNun.getText().toString());
                break;
            case R.id.band_phone:

//                /sphone/insertUserPhone.json
                new BandPhoneDialog(getActivity(), "", "", "") {

                    @Override
                    public void btn1DoThing(Dialog mDialog) {

                    }

                    @Override
                    public void btn2DoThing(Dialog mDialog) {

                        mDialog.dismiss();
                        loadData();

                    }
                }.show();

                break;
            case R.id.go_vip:
                AppUtils.showToast(context, "暂未开启");
                break;
            case R.id.yinhangka:
                gotoActivity(KefuActivity.class);
                break;
        }
    }


}
