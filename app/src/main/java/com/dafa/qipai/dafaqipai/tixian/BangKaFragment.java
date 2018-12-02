package com.dafa.qipai.dafaqipai.tixian;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.rx.rxbus.RxBus;
import com.dafa.qipai.dafaqipai.rx.rxbus.RxBusConfig;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.Select;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BangKaFragment extends LazyLoadFragment {


    @BindView(R.id.kaihuren)
    EditText kaihuren;
    @BindView(R.id.xzyh)
    TextView xzyh;
    @BindView(R.id.xuanze)
    TextView xuanze;
    @BindView(R.id.zhmc)
    EditText zhmc;
    @BindView(R.id.kahao)
    EditText kahao;
    @BindView(R.id.kaihudi)
    EditText kaihudi;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;

    @Override
    public int getLayout() {
        return R.layout.fragment_bangka;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

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


    private void submmit() {

        if (StringUtils.isEmpty(xzyh.getText().toString())) {
            Toast.makeText(context, "银行名称不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(zhmc.getText().toString())) {
            Toast.makeText(context, "支行不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(kahao.getText().toString())) {
            Toast.makeText(context, "卡号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(kaihudi.getText().toString())) {
            Toast.makeText(context, "开户地不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(kaihuren.getText().toString())) {
            Toast.makeText(context, "开户人不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }


        OkGo.post(ApiConstant.API_DOMAIN + "/member/addUserBank.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("bankName", xzyh.getText().toString())
                .params("subBankName", zhmc.getText().toString())
                .params("userName", kaihuren.getText().toString())
                .params("location", kaihudi.getText().toString())
                .params("bankAccount", kahao.getText().toString())
                .params("accountHolder", kaihuren.getText().toString())
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        try {
                            BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                            if (baseDo.getResult() == 1) {
                                Toast.makeText(context, "添加成功！", Toast.LENGTH_SHORT).show();

                                RxBus.getDefault().post(RxBusConfig.YINHANGKA);

                            } else {
                                Toast.makeText(context, baseDo.getDescription(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "未知错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void xzyhDialog() {
        ArrayList<String> statusData = new ArrayList<>();
        statusData.add("中国工商银行");
        statusData.add("中国农业银行");
        statusData.add("中国建设银行");
        statusData.add("中国银行");
        statusData.add("交通银行");
        statusData.add("招商银行");
        statusData.add("兴业银行");
        statusData.add("浦发银行");
        statusData.add("华夏银行");
        statusData.add("中信银行");
        statusData.add("中国光大银行");
        statusData.add("广发银行");
        statusData.add("中国邮政储蓄银行");
        statusData.add("平安银行");
        statusData.add("上海银行");
        statusData.add("其他银行");
        Select.selectOption(getActivity(), "选择银行", statusData, (options1, options2, options3, v) -> {
            xzyh.setText(statusData.get(options1));
        });
    }

    @OnClick({R.id.xuanze, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xuanze:

                xzyhDialog();

                break;
            case R.id.ok:
                submmit();
                break;
        }
    }
}
