package com.dafa.qipai.dafaqipai.tixian;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.DoGetBankcard;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.BankUtil;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.MD5Utils;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.wihget.Select;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dafa.qipai.dafaqipai.R2.style.dialog;

public class TiXianFragment extends LazyLoadFragment {


    @BindView(R.id.money)
    EditText money;
    @BindView(R.id.yhk_img)
    ImageView yhk_img;
    @BindView(R.id.yhk)
    TextView yhk;
    @BindView(R.id.yhk_name)
    TextView yhk_name;
    @BindView(R.id.xzyh)
    LinearLayout xzyh;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    private int id;
    private List<DoGetBankcard.UserBankCardListBean> cardList;
    private boolean isneedWithdrawPasswd;
    // private AppConfig appConfig;
    //private PasswdDialog pwddialog;
//
//    private PayPwdEditText pwd;
    private TextView passwd_no;
    private TextView passwd_ok;
    private String pwdStr;


    @Override
    public int getLayout() {
        return R.layout.fragment_tixian;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);

    }

    @Override
    public void loadData() {


        OkGo.post(ApiConstant.API_DOMAIN + "/member/getUserBankCardList.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        DoGetBankcard doGetBankcard = GsonUtil.GsonToBean(json, DoGetBankcard.class);
                        cardList = doGetBankcard.getUserBankCardList();
                        if (cardList == null || cardList.size() == 0) {
                            yhk.setText("尚未添加银行卡,前往添加");
                        } else {
                            for (int i = 0; i < cardList.size(); i++) {
                                if (cardList.get(i).isDefaultX()) {
                                    yhk_img.setVisibility(View.VISIBLE);
                                    yhk_img.setBackgroundResource((BankUtil.Bank.getResIdByName(cardList.get(i).getBankName())));
                                    yhk_name.setVisibility(View.VISIBLE);
                                    yhk_name.setText(cardList.get(i).getBankName());
                                    yhk.setText(cardList.get(i).getBankAccount().substring(0, 4) + " **** **** " + cardList.get(i).getBankAccount().substring(cardList.get(i).getBankAccount().length() - 4, cardList.get(i).getBankAccount().length()));
                                    id = cardList.get(i).getId();

                                }
                            }
                        }


                    }
                });


    }


    private void tijiao() {

        if (TextUtils.isEmpty(money.getText().toString())) {
            AppUtils.showToast(getActivity().getApplicationContext(), "金额不能为空");
            return;
        }
//                if (TextUtils.isEmpty(pwdStr)) {
//                    AppUtils.showToast(getActivity().getApplicationContext(), "取款密码不能为空");
//                    return;
//                }

        if (id == 0) {
            AppUtils.showToast(getActivity().getApplicationContext(), "选择银行卡");
            return;
        }


        OkGo.post(ApiConstant.API_DOMAIN + "/member/submitWithdraw.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .params("money", money.getText().toString())
                .params("drawPassword", MD5Utils.small32md5(pwdStr))
                .params("id", id)
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {

                        BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);

                        if (baseDo.getResult() == 1) {
                            //AppUtils.showToast(getApplicationContext(), "成功");
//                            DialogUtil.showSimpleDialog(null, "您的提款申请已提交，请耐心等候审核", getActivity(), (dialogInterface, i) -> {
//                                Intent intent = new Intent(getActivity(), TiXianActivity.class);
//                                intent.putExtra("select", true);
//                                startActivity(intent);

//                            });
                        } else {
                            if (baseDo.getDescription() != null && !baseDo.getDescription().equals("")) {
                                AppUtils.showToast(context, baseDo.getDescription());
                            }
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

    @OnClick({R.id.yhk, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yhk:
                yhkM();
                break;
            case R.id.ok:
                tijiao();
                break;
        }
    }

    private void yhkM() {

        ArrayList<String> list = new ArrayList<>();
        if (cardList == null || cardList.size() == 0) {
            AppUtils.showToast(getActivity(), "请先绑卡");
            return;
        }

        for (DoGetBankcard.UserBankCardListBean card : cardList) {
            yhk_img.setVisibility(View.VISIBLE);
            yhk_img.setBackgroundResource(BankUtil.Bank.getResIdByName(card.getBankName()));
            yhk_name.setVisibility(View.VISIBLE);
            yhk_name.setText(card.getBankName());
            list.add(card.getBankName()+" "+card.getBankAccount().substring(0, 4) + " **** " + card.getBankAccount().substring(card.getBankAccount().length() - 4, card.getBankAccount().length()));
        }
        if (list.size() != 0) {
            Select.selectOption(getActivity(), "选择银行卡", list, (options1, options2, options3, v) -> {
                if (cardList != null) {
                    yhk_img.setVisibility(View.VISIBLE);
                    yhk_img.setBackgroundResource(BankUtil.Bank.getResIdByName(cardList.get(options1).getBankName()));
                    yhk.setText( cardList.get(options1).getBankAccount().substring(0, 4) + " **** **** " + cardList.get(options1).getBankAccount().substring(cardList.get(options1).getBankAccount().length() - 4, cardList.get(options1).getBankAccount().length()));
                    id = cardList.get(options1).getId();
                    yhk_name.setVisibility(View.VISIBLE);
                    yhk_name.setText(cardList.get(options1).getBankName());
                }
            });


        }

    }
}
