package com.dafa.qipai.dafaqipai.util;


import android.support.annotation.NonNull;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.YinHangKaDo;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/4/17.
 */

public class BankUtil {
    public static List<String> createBankList() {
        List<String> statusData = new ArrayList<>();
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
        return statusData;
    }

    public static List<YinHangKaDo.UserBankCardListBean> sortMyBank(@NonNull List<YinHangKaDo.UserBankCardListBean> list) {
        if (list.isEmpty() || list.size() < 2 || list.get(0).getDefaultX()) {
            return list;
        }
        List<YinHangKaDo.UserBankCardListBean> tmpList = new ArrayList<>();
        for (YinHangKaDo.UserBankCardListBean bean : list) {
            if (bean.getDefaultX()) {
                tmpList.add(bean);
                list.remove(bean);
                tmpList.addAll(list);
                return tmpList;
            }
        }
        return list;
    }

    public enum Bank {
        GSYH("工商银行", R.mipmap.b_gs),
        NYYH("农业银行", R.mipmap.b_ny),
        ZGYH("中国银行", R.mipmap.b_zg),
        JTYH("交通银行", R.mipmap.b_jt),
        ZSYH("招商银行", R.mipmap.b_zs),
        XYYH("兴业银行", R.mipmap.b_xy),
        PFYH("浦发银行", R.mipmap.b_pf),
        HXYH("华夏银行", R.mipmap.b_hx),
        ZXYH("中信银行", R.mipmap.b_zx),
        GDYH("光大银行", R.mipmap.b_gd),
        GFYH("广发银行", R.mipmap.b_gf),
        YZYH("邮政储蓄银行", R.mipmap.b_yz),
        PAYH("平安银行", R.mipmap.b_pa),
        SHYH("上海银行", R.mipmap.b_sh),
        JSYH("建设银行", R.mipmap.b_js);


        private final String bankName;
        private final int resId;

        Bank(String s, int resId) {
            this.bankName = s;
            this.resId = resId;
        }

        public String getBankName() {
            return bankName;
        }

        public int getResId() {
            return resId;
        }

        public static int getResIdByName(String bankName) {
            for (Bank bank : Bank.values()) {
                if (bankName.equals(bank.getBankName())) {
                    return bank.resId;
                }
            }
            return R.mipmap.b_other;
        }
    }

    public enum Bank1 {
        GGYH("中国银行", R.mipmap.b_zg),
        JTYH("交通银行", R.mipmap.b_jt),
        ZSYH("招商银行", R.mipmap.b_zs),
        JSYH("建设银行", R.mipmap.b_js),
        GSYH("工商银行", R.mipmap.b_gs),
        NYYH("农业银行", R.mipmap.b_ny),
        NSYH("民生银行", R.mipmap.b_ns),
        XYYH("兴业银行", R.mipmap.b_xy),
        PFYH("浦发银行", R.mipmap.b_pf),
        GDYH("光大银行", R.mipmap.b_gd),
        SZYH("深圳发展银行", R.mipmap.b_sz),
        SHYH("上海银行", R.mipmap.b_sh),
        YZYH("中国邮政储蓄", R.mipmap.b_yz),
        HXYH("华夏银行", R.mipmap.b_hx),
        GFYH("广发银行", R.mipmap.b_zg),
        ZXYH("中信银行", R.mipmap.b_zx),
        PAYH("平安银行", R.mipmap.b_pa),
        BJYH("北京银行", R.mipmap.b_bj),
        NBYH("宁波银行", R.mipmap.b_nb),
        QTYH("其他银行", R.mipmap.b_other);

        private final String bankName;
        private final int resId;

        Bank1(String s, int resId) {
            this.bankName = s;
            this.resId = resId;
        }

        public String getBankName() {
            return bankName;
        }

        public int getResId() {
            return resId;
        }

//        public static int getResIdByName(String bankName) {
//            for (Bank1 Bank : Bank1.values()) {
//                if (bankName.equals(Bank.getBankName())) {
//                    return Bank.resId;
//                }
//            }
//            return R.mipmap.head1;
//        }


        public static String getBlankName(String bankName)
        {

            String name=null;
            switch (bankName)
            {
                case "中国银行":
                    name= "中国银行";
                    break;
                case "交通银行":
                    name= "交通银行";
                    break;
                case "招商银行":
                    name= "招商银行";

                    break;
                case "建设银行":
                    name= "建设银行";
                    break;
                case "工商银行":
                    name= "工商银行";
                    break;
                case "农业银行":
                    name= "农业银行";
                    break;
                case "民生银行":
                    name= "民生银行";
                    break;
                case "兴业银行":
                    name= "兴业银行";
                    break;
                case "浦发银行":
                    name= "浦发银行";
                    break;
                case "光大银行":
                    name= "光大银行";
                    break;
                case "深圳发展银行":
                    name= "深圳发展银行";
                    break;
                case "上海银行":
                    name= "上海银行";
                    break;
                case "中国邮政储蓄":
                    name= "中国邮政储蓄";
                    break;
                case "华夏银行":
                    name= "华夏银行";
                    break;
                case "广发银行":
                    name= "广发银行";
                    break;
                case "中信银行":
                    name= "中信银行";
                    break;
                case "平安银行":
                    name= "平安银行";
                    break;
                case "北京银行":
                    name= "北京银行";
                    break;
                case "宁波银行":
                    name= "宁波银行";
                    break;
                case "其他银行":
                    name= "其他银行";
                    break;

            }
            return name;
        }
    }

}
