package com.dafa.qipai.dafaqipai.chong;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.DoSSS;
import com.dafa.qipai.dafaqipai.bean.DoSysCard;
import com.dafa.qipai.dafaqipai.bean.DoYinshangs;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.core.CommonConstant;
import com.dafa.qipai.dafaqipai.geren.GerenActivity;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseActivity;
import com.dafa.qipai.dafaqipai.wihget.LimitEditeText;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.kongzue.dialog.v2.MessageDialog;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ezy.ui.view.RoundButton;

//import com.dou361.dialogui.DialogUIUtils;
//import com.dou361.dialogui.listener.DialogUIItemListener;

/**
 * 作者:hyb4600
 * 日期: 2017/12/20.
 * 功能描述:银行卡充值
 */

public class YinHangKaZhuanZhangActivity extends BaseActivity {


    @BindView(R.id.shoukuan_bank)
    TextView shoukuan_bank;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.khy_tv)
    TextView khyTv;
    @BindView(R.id.khy)
    TextView khy;
    @BindView(R.id.khyh_copy)
    TextView khyhCopy;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.khwd_tv)
    TextView khwdTv;
    @BindView(R.id.khwd)
    TextView khwd;
    @BindView(R.id.yhwd_copy)
    TextView yhwdCopy;
    @BindView(R.id.khxm_tv)
    TextView khxmTv;
    @BindView(R.id.khxm)
    TextView khxm;
    @BindView(R.id.yhxm_copy)
    TextView yhxmCopy;
    @BindView(R.id.kahao_tv)
    TextView kahaoTv;
    @BindView(R.id.kahao)
    TextView kahao;
    @BindView(R.id.kahao_copy)
    TextView kahaoCopy;
    @BindView(R.id.zhuanchu)
    TextView zhuanchu;
    @BindView(R.id.zhuanchu_rl)
    RelativeLayout zhuanchuRl;
    @BindView(R.id.chuankuan_type)
    TextView chuankuan_type;
    @BindView(R.id.cunkuan_type_rl)
    RelativeLayout cunkuanTypeRl;
    @BindView(R.id.money)
    EditText money;
    @BindView(R.id.bank_num)
    EditText bank_num;
    @BindView(R.id.bank_name)
    LimitEditeText bank_name;
    @BindView(R.id.commit)
    RoundButton commit;
    @BindView(R.id.idnum)
    TextView idnum;
    @BindView(R.id.fuzhi)
    TextView fuzhi;
    @BindView(R.id.chongti)
    TextView chongti;
    @BindView(R.id.finish)
    TextView finish;


    private List<DoSysCard.BankcardListBean> bankcardList;

    private List<DoSSS.DepositChannelListBean> depositChannelList;

    private int depositChannelId;

    private int bankCardId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_yinhangka);
        ButterKnife.bind(this);
        AutoUtils.auto(this);
        // centertitle.setText("银行汇款");
        //rightTv.setVisibility(View.GONE);
        money.clearFocus();
        money.setFocusable(false);
        // bank_num.clearFocus();
        //bank_num.setFocusable(false);

        idnum.setText(MyApp.ID);

    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
        //getInfo();

        loadYinhang();
    }


    private void load() {

            OkGo.post(ApiConstant.API_DOMAIN + "/member/getSystemBankCard.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {

                        try {
                            DoSysCard doSysCard = GsonUtil.GsonToBean(json, DoSysCard.class);

                            bankcardList = doSysCard.getBankcardList();

                            shoukuan_bank.setText(bankcardList.get(0).getBankName());
                            khy.setText("" + bankcardList.get(0).getBankName());
                            khwd.setText("" + bankcardList.get(0).getSubBankName());
                            khxm.setText("" + bankcardList.get(0).getUserName());
                            kahao.setText("" + bankcardList.get(0).getBankAccount());
                            bankCardId = bankcardList.get(0).getId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


        OkGo.post(ApiConstant.API_DOMAIN + "/member/getDepositChannel.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("type", 1)
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        DoSSS doSSS = GsonUtil.GsonToBean(json, DoSSS.class);
                        depositChannelList = doSSS.getDepositChannelList();
                    }
                });


    }

    @OnClick({R.id.shoukuan_bank, R.id.khyh_copy, R.id.yhwd_copy, R.id.yhxm_copy, R.id.kahao_copy, R.id.zhuanchu_rl, R.id.cunkuan_type_rl, R.id.commit, R.id.money, R.id.bank_num})
    public void onClick(View view) {
        ClipboardManager cm2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        switch (view.getId()) {

            case R.id.shoukuan_bank:
                break;
            case R.id.khyh_copy:
                // 将文本内容放到系统剪贴板里。
                cm2.setText(khy.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.yhwd_copy:
                // 将文本内容放到系统剪贴板里。
                cm2.setText(khwd.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.yhxm_copy:
                cm2.setText(khwd.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.kahao_copy:
                cm2.setText(kahao.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.zhuanchu_rl:

                BottomMenu.show(YinHangKaZhuanZhangActivity.this, lists, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {

                        zhuanchu.setText(text);
                    }
                }, true).setTitle("选择银行");


                break;
            case R.id.cunkuan_type_rl://存款方式
                List<String> strings = new ArrayList<>();

                for (DoSSS.DepositChannelListBean s : depositChannelList) {
                    strings.add(s.getName());
                }


                BottomMenu.show(YinHangKaZhuanZhangActivity.this, strings, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        chuankuan_type.setText(text);
                        depositChannelId = depositChannelList.get(index).getId();

                    }
                }, true).setTitle("存款方式");


                break;
            case R.id.commit:
                submit();

                break;
            case R.id.money:
                money.setFocusable(true);
                money.setFocusableInTouchMode(true);
                money.requestFocus();
                break;
            case R.id.bank_num:
                bank_num.setFocusable(true);
                bank_num.setFocusableInTouchMode(true);
                bank_num.requestFocus();
                break;

        }
    }


    private void submit() {
        if (TextUtils.isEmpty(money.getText().toString())) {
            showError(context, "请输入充值金额");
            return;
        }
        if (TextUtils.isEmpty(bank_num.getText().toString())) {
            showError(context, "请输入银行卡后四位");
            return;
        }
        if (bank_num.getText().toString().length() < 4) {
            showError(context, "请输入银行卡后四位");
            return;
        }
        if (TextUtils.isEmpty(bank_name.getText().toString())) {
            showError(context, "请输入汇款人姓名");
            return;
        }
        OkGo.post(ApiConstant.API_DOMAIN + "/member/submitYhzz.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("money", money.getText().toString())
                .params("depositChannelId", depositChannelId)
                .params("bankCardId", bankCardId)
                .params("bankAccount", bank_num.getText().toString())
                .params("userBankName", zhuanchu.getText().toString())
                .params("name", bank_name.getText().toString())
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("银行卡", json);
                        BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);

                        if (baseDo.getResult() == CommonConstant.SUCCESS_CODE) {

                            MessageDialog.show(YinHangKaZhuanZhangActivity.this, "消息提示", "您的存款申请已提交，请耐心等候审核", "知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    //                                    Intent intent = new Intent(YinHangKaRechargeActivity.this, ChongZhiActivity.class);
//                                    intent.putExtra("select", true);
//                                    startActivity(intent);
                                }
                            });
                        } else {

                            //TipDialog.show(YinHangKaZhuanZhangActivity.this, baseDo.getDescription(), TipDialog.SHOW_TIME_LONG, TipDialog.TYPE_ERROR);

                            showError(YinHangKaZhuanZhangActivity.this, baseDo.getDescription());
                        }


                    }
                });
    }


    List<String> lists;

    private void loadYinhang() {


        OkGo.post(ApiConstant.API_DOMAIN + "/app/getEnums.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("选择银行", json);

                        DoYinshangs doYinshangs = GsonUtil.GsonToBean(json, DoYinshangs.class);

                        List<DoYinshangs.BankListBean> bankList = doYinshangs.getBankList();
                        if (bankList == null) {
                            return;
                        }
                        lists = new ArrayList<String>();
                        for (DoYinshangs.BankListBean item : bankList) {
                            if (item.getName().equals("中国银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("交通银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("招商银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("建设银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("工商银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("农业银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("民生银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("兴业银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("浦发银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("光大银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("深圳发展银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("上海银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("中国邮政储蓄")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("华夏银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("广发银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("中信银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("平安银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("北京银行")) {
                                lists.add(item.getName());
                            }
                            if (item.getName().equals("宁波银行")) {
                                lists.add(item.getName());
                            }

                        }
                        DoYinshangs.BankListBean bean = new DoYinshangs.BankListBean();
                        bean.setId(99);
                        bean.setName("其他银行");
                        //lists.add(bean);

                    }
                });

    }


    @OnClick({R.id.fuzhi, R.id.chongti, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fuzhi:
                AppUtils.copyToClipboard(context, MyApp.ID);
                break;
            case R.id.chongti:
                startActivity(new Intent(this, GerenActivity.class));
                break;
            case R.id.finish:
                finish();
                break;
        }
    }
}
