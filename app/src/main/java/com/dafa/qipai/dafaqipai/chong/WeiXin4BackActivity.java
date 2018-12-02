package com.dafa.qipai.dafaqipai.chong;

import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dafa.qipai.dafaqipai.MyApp;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.DoSysCard;
import com.dafa.qipai.dafaqipai.bean.DyParam;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.geren.GerenActivity;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.BaseActivity;
import com.dafa.qipai.dafaqipai.wihget.NoIndex0EditeText;
import com.dafa.qipai.dafaqipai.wihget.Select;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.kongzue.dialog.v2.MessageDialog;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ezy.ui.view.RoundButton;


/**
 * 微信转银行卡
 */
public class WeiXin4BackActivity extends BaseActivity {


    @BindView(R.id.zhuanzhang)
    TextView zhuanzhang;
    @BindView(R.id.zifu)
    TextView zifu;
    @BindView(R.id.input_jine)
    NoIndex0EditeText input_jine;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.yinhang)
    TextView yinhang;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.bank_num)
    TextView bank_num;
    @BindView(R.id.num_copy)
    TextView numCopy;
    @BindView(R.id.kaihuren)
    TextView kaihuren;
    @BindView(R.id.kaihuren_copy)
    TextView kaihurenCopy;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.address_copy)
    TextView addressCopy;
    @BindView(R.id.open_wx)
    RoundButton openWx;
    @BindView(R.id.zhuanzhang_ll)
    LinearLayout zhuanzhang_ll;
    @BindView(R.id.chongzhi_jine)
    NoIndex0EditeText chongzhiJine;
    @BindView(R.id.rukuan_time)
    TextView rukuanTime;
    @BindView(R.id.time)
    TextView time;

    @BindView(R.id.zhongzhi_name)
    TextView zhongzhiName;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.button)
    RoundButton button;
    @BindView(R.id.zhifu_ll)
    LinearLayout zhifu_ll;
    @BindView(R.id.idnum)
    TextView idnum;
    @BindView(R.id.fuzhi)
    TextView fuzhi;
    @BindView(R.id.chongti)
    TextView chongti;
    @BindView(R.id.finish)
    TextView finish;


    private List<DoSysCard.BankcardListBean> bankcardList;
    private int bankCardId;
    private String dyParam;
    private int payType;
    private int bankDepositType;

    private boolean flag = false;//标记edittext不会死循环

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutoUtils.setSize(this, false, 1920, 1080);

        setContentView(R.layout.activity_wei_xin4_back);
        ButterKnife.bind(this);
        AutoUtils.auto(this);

        idnum.setText(MyApp.ID);

        input_jine.setText(getIntent().getStringExtra("money"));
        chongzhiJine.setText(getIntent().getStringExtra("money"));

        payType = getIntent().getIntExtra("payType", 9);
        if (payType == 9) {
            openWx.setText("打开微信");
        } else if (payType == 11) {
            openWx.setText("打开支付宝");
        }


        chongzhiJine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                input_jine.setText(charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (flag) {
                    return;
                }
                flag = true;
                /**
                 *
                 * 这部分是业务逻辑的代码块
                 */

            }

            @Override
            public void afterTextChanged(Editable editable) {
                chongzhiJine.setSelection(chongzhiJine.length());
                input_jine.setSelection(input_jine.length());
            }
        });


        getDy();
        blankInfo();
        input_jine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                chongzhiJine.setText(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));

                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#696969"));

                chongzhiJine.setSelection(chongzhiJine.length());
                input_jine.setSelection(input_jine.length());

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        String format = Select.format2(new Date());
        time.setText(format);

    }

    public void getDy() {

        if (payType == 9) {
            OkGo.post(ApiConstant.API_DOMAIN + "member/getDyParam.json")
                    .params("uid", UserUtil.getUserID(this))
                    .params("token", UserUtil.getToken(this))
                    .tag(this)
                    .execute(new OkGoCallBack(this, false) {
                        @Override
                        protected void _onNext(String json) {
                            Log.v("getDyParam", json);
                            DyParam dy = GsonUtil.GsonToBean(json, DyParam.class);
                            if (dy.getResult() == 1) {
                                dyParam = dy.getDyParam();
                                zhongzhiName.setText(dyParam + "：");
                                name.setHint(dy.getDyParamTip());
                            }
                        }
                    });
        } else if (payType == 11) {
            OkGo.post(ApiConstant.API_DOMAIN + "member/getDyParam4AliPay.json")
                    .params("uid", UserUtil.getUserID(this))
                    .params("token", UserUtil.getToken(this))
                    .tag(this)
                    .execute(new OkGoCallBack(this, false) {
                        @Override
                        protected void _onNext(String json) {
                            Log.v("getDyParam", json);
                            DyParam dy = GsonUtil.GsonToBean(json, DyParam.class);
                            if (dy.getResult() == 1) {
                                dyParam = dy.getDyParam();
                                zhongzhiName.setText(dyParam + "：");
                                name.setHint(dy.getDyParamTip());
                            }
                        }
                    });
        }


    }


    public void blankInfo() {
        if (payType == 9) {
            bankDepositType = 2;
        } else if (payType == 11) {
            bankDepositType = 4;
        }

        OkGo.post(ApiConstant.API_DOMAIN + "/member/getSystemBankCard.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("bankDepositType", bankDepositType)
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("getSystemBankCard", json);

                        try {
                            DoSysCard doSysCard = GsonUtil.GsonToBean(json, DoSysCard.class);
                            bankcardList = doSysCard.getBankcardList();
                            bankCardId = bankcardList.get(0).getId();
                            yinhang.setText(bankcardList.get(0).getBankName());
                            address.setText("" + bankcardList.get(0).getSubBankName());
                            kaihuren.setText("" + bankcardList.get(0).getUserName());
                            bank_num.setText("" + bankcardList.get(0).getBankAccount());

                            selectAcont();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private void selectAcont() {
        ArrayList<String> list = new ArrayList<>();

        for (DoSysCard.BankcardListBean card : bankcardList) {
            list.add(card.getBankName() + " ****" + card.getBankAccount().substring(card.getBankAccount().length() - 4, card.getBankAccount().length()));
        }


//        BottomMenu.show(WeiXin4BackActivity.this, list, new OnMenuItemClickListener() {
//            @Override
//            public void onClick(String text, int position) {
//                bankCardId = bankcardList.get(position).getId();
//                yinhang.setText(bankcardList.get(position).getBankName());
//                address.setText("" + bankcardList.get(position).getSubBankName());
//                kaihuren.setText("" + bankcardList.get(position).getUserName());
//                bank_num.setText(bankcardList.get(position).getBankAccount() + "");
//
//            }
//        }, true).setTitle("银行卡");


        Select.selectOption(WeiXin4BackActivity.this, "银行卡", list, (options1, options2, options3, v) -> {
            bankCardId = bankcardList.get(options1).getId();
            yinhang.setText(bankcardList.get(options1).getBankName());
            address.setText("" + bankcardList.get(options1).getSubBankName());
            kaihuren.setText("" + bankcardList.get(options1).getUserName());
            bank_num.setText(bankcardList.get(options1).getBankAccount() + "");
        });


    }

    public void submit() {

        String url = null;

        if (payType == 9) {
            url = "submitWeChat2Bank.json";
        } else if (payType == 11) {
            url = "submitAliPay2Bank.json";
        }


//        long  times=DateUtil.dateToTimestamp(time.getText().toString().trim());
        Long testTime = new Date().getTime();
        OkGo.post(ApiConstant.API_DOMAIN + "member/" + url)
                .params("uid", UserUtil.getUserID(this))
                .params("token", UserUtil.getToken(this))
                .params("money", chongzhiJine.getText().toString())
                .params("submitTime", time.getText().toString().trim())
                .params("unParam", name.getText().toString())
                .params("bankCardId", bankCardId)
                .tag(this)
                .execute(new OkGoCallBack(this, true) {
                    @Override
                    protected void _onNext(String json) {

                        BaseDo baseDo = new BaseDo();
                        String description = baseDo.getDescription();

                        MessageDialog.show(WeiXin4BackActivity.this, "消息提示", description, "知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                // Intent intent = new Intent(YinHangKaRechargeActivity.this, ChongZhiActivity.class);
                                // intent.putExtra("select", true);
                                // startActivity(intent);
                            }
                        });


                    }
                });

    }

    @OnClick({ /*R.id.rightTv,*/ R.id.more, R.id.zhuanzhang, R.id.zifu, R.id.num_copy, R.id.kaihuren_copy, R.id.address_copy, R.id.open_wx, R.id.zhuanzhang_ll, R.id.time, R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {


//            case R.id.rightTv:
//                if (payType == 9) {//微信
//                    Intent intent = new Intent();
//                    intent.setClass(this, WeinXin4BankDialog.class);
//                    startActivity(intent);
//                } else if (payType == 11) {//支付宝ZhifubaoDialog
//                    Intent intent = new Intent();
//                    intent.setClass(this, Zhifubao4BankDialog.class);
//                    startActivity(intent);
//                }

            //break;
            case R.id.more:
                selectAcont();
                break;
            case R.id.zhuanzhang:
                zhuanzhang.setTextColor(Color.parseColor("#fa6200"));
                zifu.setTextColor(Color.parseColor("#696969"));
                zhuanzhang_ll.setVisibility(View.VISIBLE);
                zhifu_ll.setVisibility(View.GONE);
                break;
            case R.id.zifu:
//                if (TextUtils.isEmpty(input_jine.getText().toString())) {
//                    Toast.makeText(this, "充值金额不能为空", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                zifu.setTextColor(Color.parseColor("#fa6200"));
                zhuanzhang.setTextColor(Color.parseColor("#696969"));
                zhuanzhang_ll.setVisibility(View.GONE);
                zhifu_ll.setVisibility(View.VISIBLE);
                break;
            case R.id.num_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(bank_num.getText());
                Toast.makeText(WeiXin4BackActivity.this, "复制成功!", Toast.LENGTH_LONG).show();
                break;
            case R.id.kaihuren_copy:
                ClipboardManager cm1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm1.setText(kaihuren.getText());
                Toast.makeText(WeiXin4BackActivity.this, "复制成功!", Toast.LENGTH_LONG).show();
                break;
            case R.id.address_copy:
                ClipboardManager cm2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm2.setText(address.getText());
                Toast.makeText(WeiXin4BackActivity.this, "复制成功!", Toast.LENGTH_LONG).show();
                break;
            case R.id.open_wx:

                if (TextUtils.isEmpty(input_jine.getText().toString())) {
                    Toast.makeText(this, "充值金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (payType == 9) {


                    MessageDialog.show(WeiXin4BackActivity.this, "温馨提示", "请转账完成后切换至该页面提交订单立即转账", "开打微信", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            zifu.setTextColor(Color.parseColor("#fa6200"));
                            zhuanzhang.setTextColor(Color.parseColor("#696969"));
                            zhuanzhang_ll.setVisibility(View.GONE);
                            zhifu_ll.setVisibility(View.VISIBLE);
                            openWx();
                        }
                    });


                } else if (payType == 11) {

                    MessageDialog.show(WeiXin4BackActivity.this, "温馨提示", "请转账完成后切换至该页面提交订单立即转账", "开打支付宝", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            zifu.setTextColor(Color.parseColor("#fa6200"));
                            zhuanzhang.setTextColor(Color.parseColor("#696969"));
                            zhuanzhang_ll.setVisibility(View.GONE);
                            zhifu_ll.setVisibility(View.VISIBLE);
                            openZfb();
                        }
                    });


                }


                break;
            case R.id.zhuanzhang_ll:
                break;
            case R.id.time:
                Select.selectYmdHm(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String format = Select.format2(date);
                        time.setText(format);
                    }
                });
                break;
            case R.id.tv1:
                closeKeyBord();
                input_jine.setText("50");
                tv1.setTextColor(Color.parseColor("#FFFFFF"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#696969"));

                tv1.setBackgroundColor(Color.parseColor("#fa6200"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));

                break;
            case R.id.tv2:
                closeKeyBord();
                input_jine.setText("100");
                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#FFFFFF"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#696969"));

                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#fa6200"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));
                break;
            case R.id.tv3:
                closeKeyBord();
                closeKeyBord();
                input_jine.setText("200");
                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#FFFFFF"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#696969"));

                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#fa6200"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));
                break;
            case R.id.tv4:
                closeKeyBord();
                input_jine.setText("500");
                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#FFFFFF"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#696969"));

                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#fa6200"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));
                break;
            case R.id.tv5:
                closeKeyBord();
                input_jine.setText("1000");
                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#FFFFFF"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#696969"));

                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#fa6200"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));
                break;
            case R.id.tv6:
                closeKeyBord();
                input_jine.setText("2000");
                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#FFFFFF"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#696969"));

                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#fa6200"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));
                break;
            case R.id.tv7:
                closeKeyBord();
                input_jine.setText("5000");
                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#FFFFFF"));
                tv8.setTextColor(Color.parseColor("#696969"));


                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#fa6200"));
                tv8.setBackgroundColor(Color.parseColor("#efeff4"));
                break;
            case R.id.tv8:
                closeKeyBord();
                input_jine.setText("10000");
                tv1.setTextColor(Color.parseColor("#696969"));
                tv2.setTextColor(Color.parseColor("#696969"));
                tv3.setTextColor(Color.parseColor("#696969"));
                tv4.setTextColor(Color.parseColor("#696969"));
                tv5.setTextColor(Color.parseColor("#696969"));
                tv6.setTextColor(Color.parseColor("#696969"));
                tv7.setTextColor(Color.parseColor("#696969"));
                tv8.setTextColor(Color.parseColor("#FFFFFF"));

                tv1.setBackgroundColor(Color.parseColor("#efeff4"));
                tv2.setBackgroundColor(Color.parseColor("#efeff4"));
                tv3.setBackgroundColor(Color.parseColor("#efeff4"));
                tv4.setBackgroundColor(Color.parseColor("#efeff4"));
                tv5.setBackgroundColor(Color.parseColor("#efeff4"));
                tv6.setBackgroundColor(Color.parseColor("#efeff4"));
                tv7.setBackgroundColor(Color.parseColor("#efeff4"));
                tv8.setBackgroundColor(Color.parseColor("#fa6200"));
                break;
            case R.id.button:
                if (TextUtils.isEmpty(chongzhiJine.getText().toString())) {
                    Toast.makeText(WeiXin4BackActivity.this, "请输入金额", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(time.getText().toString())) {
                    Toast.makeText(WeiXin4BackActivity.this, "请选择时间", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(WeiXin4BackActivity.this, "请输入" + dyParam, Toast.LENGTH_LONG).show();
                    return;
                }

                submit();
                break;
        }
    }

    /**
     * 打开微信
     */
    private void openWx() {
        boolean avilible = AppUtils.isWeixinAvilible(context);
        if (avilible) {

            Intent intent = new Intent();
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);

        } else {
            Toast.makeText(context, "没有安装微信", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * QQ
     */
    private void openQQ() {
        boolean avilible = AppUtils.isQQAvilible(context);
        if (avilible) {

            try {
                //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=453453446";//uin是发送过去的qq号码
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception e) {
                e.printStackTrace();

            }


        } else {
            Toast.makeText(context, "没有安装QQ", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 打开支付宝
     */
    private void openZfb() {
        boolean avilible = AppUtils.isZfbAvilible(context);
        if (avilible) {
            PackageManager packageManager = context.getPackageManager();
            Intent it = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone");
            startActivity(it);


        } else {
            Toast.makeText(context, "没有安装支付宝", Toast.LENGTH_LONG).show();
        }

    }


    private void closeKeyBord() {
        /*隐藏软键盘*/
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    @OnClick({R.id.fuzhi, R.id.chongti, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fuzhi:
                AppUtils.copyToClipboard(this, MyApp.ID);
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
