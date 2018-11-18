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
import com.dafa.qipai.dafaqipai.view.BaoxianxActivity3;
import com.dafa.qipai.dafaqipai.wihget.NoIndex0EditeText;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.lzy.okgo.OkGo;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class CunRuFragment extends LazyLoadFragment {


    @BindView(R.id.qianbao)
    EditText qianbao;
    @BindView(R.id.baoxianx)
    EditText baoxianx;
    @BindView(R.id.money)
    NoIndex0EditeText money;
    @BindView(R.id.qingchu)
    TextView qingchu;
    @BindView(R.id.seek)
    SeekBar seek;
    @BindView(R.id.zuida)
    TextView zuida;
    @BindView(R.id.ok)
    Button ok;
    Unbinder unbinder;

    private boolean flag = false;//标记edittext不会死循环
    private boolean flag2 = false;//标记edittext不会死循环


    @Override
    public int getLayout() {
        return R.layout.fragment_cunru;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


//        RxTextView.textChangeEvents(money)
//                .debounce(1000, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<TextViewTextChangeEvent>() {
//                    @Override
//                    public void onCompleted() {
//
//                        System.out.println("onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("Throwable");
//                    }
//
//                    @Override
//                    public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
//                        String string = onTextChangeEvent.text().toString();
//
//                        System.out.println(string);
//
//                            if (flag) {
//                                return;
//                            }
//                            flag = true;
//
//
//                            if (TextUtils.isEmpty(string)) {
//
//                                seek.setProgress(0);
//
//                            } else {
//
//
//                                BigDecimal b1 = new BigDecimal(string.toString());
//                                BigDecimal b2 = new BigDecimal(qianbao.getText().toString());
//
////                        if (b1.compareTo(b2) > 0) {
//                                //b1 = new BigDecimal(qianbao.getText().toString());
//                                // money.setText(qianbao.getText().toString());
//
//                                System.out.println(b1.compareTo(b2));
//
//                                //}
//
//                                BigDecimal divide = b1.divide(b2, 3, ROUND_HALF_UP);
//                                String multiply = AppBigDecimal.multiply(divide.toPlainString(), "1000", 3);
//
//                                System.out.println("multiply       " + multiply);
//                                seek.setProgress(Integer.parseInt(multiply));
//
//
//                            }
//
//
//                            flag = false;
//
//
//                    }
//                });


        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {


                if (flag) {
                    return;
                }
                flag = true;


                String s1 = s.toString();
                if (TextUtils.isEmpty(s1)) {

                    seek.setProgress(0);

                } else {


                    BigDecimal b1 = new BigDecimal(s1.toString());
                    BigDecimal b2 = new BigDecimal(qianbao.getText().toString());

//                        if (b1.compareTo(b2) > 0) {
                    //b1 = new BigDecimal(qianbao.getText().toString());
                    // money.setText(qianbao.getText().toString());



                    //}

                    BigDecimal divide = b1.divide(b2, 3, ROUND_HALF_UP);
                    String multiply = AppBigDecimal.multiply(divide.toPlainString(), "1000", 3);

                    System.out.println("multiply       " + multiply);
                    seek.setProgress(Integer.parseInt(AppUtils.subZeroAndDot(multiply)));


                }


                flag = false;



            }
        });


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                if (flag2) {
                    return;
                }
                flag2 = true;

                    String s = qianbao.getText().toString();

                    BigDecimal b1 = new BigDecimal(progress);
                    b1.divide(new BigDecimal("1000"));

                    BigDecimal b2 = new BigDecimal(qianbao.getText().toString());


                    String multiply = AppBigDecimal.multiply(b1.toPlainString(), b2.toPlainString(), 3);


                    BigDecimal divide = new BigDecimal(multiply).divide(new BigDecimal(1000), 3, ROUND_HALF_UP);
                    money.setText(divide.toPlainString());


                    flag2 = false;


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


        zhuanru("0");


    }

    private void zhuanru(String money) {

        OkGo.post(ApiConstant.API_DOMAIN + "/safeu/getDeposit.json")
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("type", "deposit")
                //.params("type", "out")
                .params("money", money)
                .execute(new OkGoCallBack(getActivity(), true) {
                    @Override
                    protected void _onNext(String json) {

                        DoZHuanhuan doZHuanhuan = GsonUtil.GsonToBean(json, DoZHuanhuan.class);
                        if (doZHuanhuan.getResult() == 1) {
                            try {
                                BigDecimal wallet = doZHuanhuan.getWallet();

                                BigDecimal money = doZHuanhuan.getList().get(0).getMoney();

                                qianbao.setText(wallet + "");
                                baoxianx.setText(money + "");
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
                money.setText("");
                seek.setProgress(0);
                break;
            case R.id.zuida:
                break;
            case R.id.ok:

                zhuanru(money.getText().toString());

                break;
        }
    }
}
