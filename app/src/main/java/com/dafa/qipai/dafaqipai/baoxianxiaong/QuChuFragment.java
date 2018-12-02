package com.dafa.qipai.dafaqipai.baoxianxiaong;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoShemm;
import com.dafa.qipai.dafaqipai.bean.DoZHuanhuan;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppBigDecimal;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class QuChuFragment extends LazyLoadFragment {


    @BindView(R.id.qianbao)
    TextView qianbao;
    @BindView(R.id.baoxianx)
    TextView baoxianx;
    @BindView(R.id.money)
    EditText eTmoney;
    @BindView(R.id.qingchu)
    TextView qingchu;
    @BindView(R.id.seek)
    SeekBar seek;
    @BindView(R.id.zuida)
    TextView zuida;
    @BindView(R.id.ok)
    Button ok;
    Unbinder unbinder;
    @BindView(R.id.title)
    TextView title;

    private boolean flag = false;//标记edittext不会死循环
    private boolean flag2 = false;//标记edittext不会死循环

    private Boolean isInput = true;


    @Override
    public int getLayout() {
        return R.layout.fragment_cunru;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

        title.setText("取出金额");
        eTmoney.setHint("请输入您的取出金额");
        ok.setText("确认取出");

        eTmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                isInput = false;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (flag) {
                    return;
                }
                flag = true;


                String s1 = s.toString();
                if (TextUtils.isEmpty(s1)) {

                    seek.setProgress(0);

                } else {

                    try {
                        BigDecimal b1 = new BigDecimal(s1.toString());
                        BigDecimal b2 = new BigDecimal(baoxianx.getText().toString());

//                        if (b1.compareTo(b2) > 0) {
                        //b1 = new BigDecimal(qianbao.getText().toString());
                        // money.setText(qianbao.getText().toString());


                        //}


                        BigDecimal divide = b1.divide(b2, 3, ROUND_HALF_UP);
                        String multiply = AppBigDecimal.multiply(divide.toPlainString(), "1000", 3);

                        System.out.println("mm      " + multiply);

                        seek.setProgress(Integer.parseInt(AppUtils.subZeroAndDot(multiply)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


                flag = false;

            }

            @Override
            public void afterTextChanged(Editable s) {

                isInput = true;


            }
        });


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (isInput) {


                    if (flag2) {
                        return;
                    }
                    flag2 = true;


                    try {
                        BigDecimal b1 = new BigDecimal(progress);
                        b1.divide(new BigDecimal("1000"));

                        BigDecimal b2 = new BigDecimal(baoxianx.getText().toString());

                        String multiply = AppBigDecimal.multiply(b1.toPlainString(), b2.toPlainString(), 3);

                        BigDecimal divide = new BigDecimal(multiply).divide(new BigDecimal(1000), 3, ROUND_HALF_UP);
                        eTmoney.setText(divide.toPlainString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    flag2 = false;


                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void loadData() {


        OkGo.post(ApiConstant.API_DOMAIN + "/safeu/login.json")
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("password", MyApp.baoxianxiang)
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {

                        DoShemm doShemm = GsonUtil.GsonToBean(json, DoShemm.class);
                        if (doShemm.getResult() == 1) {
                            try {
                                BigDecimal wallet = doShemm.getWallet();
                                BigDecimal money = doShemm.getList().get(0).getMoney();
                                qianbao.setText(wallet + "");
                                baoxianx.setText(money + "");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }

                });


    }

    private void zhuanru(String money) {

        if (TextUtils.isEmpty(money)) {

            AppUtils.showToast(context, "金额不正确");

            return;
        }

        OkGo.post(ApiConstant.API_DOMAIN + "/safeu/getDeposit.json")
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("type", "out")
                //.params("type", "out")
                .params("money", money)
                .execute(new OkGoCallBack(getActivity(), true) {
                    @Override
                    protected void _onNext(String json) {

                        DoZHuanhuan doZHuanhuan = GsonUtil.GsonToBean(json, DoZHuanhuan.class);
                        if (doZHuanhuan.getResult() == 1) {
                            try {
                                BigDecimal wallet = doZHuanhuan.getWallet();

                                BigDecimal money11 = doZHuanhuan.getList().get(0).getMoney();

                                qianbao.setText(wallet + "");
                                baoxianx.setText(money11 + "");

                                eTmoney.setText("");
                                seek.setProgress(0);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {

                            AppUtils.showToast(context, doZHuanhuan.getDescription());

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

    @OnClick({R.id.qingchu, R.id.zuida, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qingchu:
                eTmoney.setText("");
                seek.setProgress(0);
                break;
            case R.id.zuida:
                seek.setProgress(1000);
                break;
            case R.id.ok:

                zhuanru(eTmoney.getText().toString());

                break;
        }
    }

    @Override
    public boolean getUserVisibleHint() {

        title.setText("取出金额");
        eTmoney.setHint("请输入您的取出金额");
        ok.setText("确认取出");
        loadData();
        return super.getUserVisibleHint();

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);


        title.setText("取出金额");
        eTmoney.setHint("请输入您的取出金额");
        ok.setText("确认取出");
        loadData();
        if (!hidden) {

        }
    }
}
