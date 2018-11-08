package com.dafa.qipai.dafaqipai.bean;

import java.io.Serializable;

/**
 * 作者:hyb4600
 * 日期: 2017/11/13.
 * 功能描述:
 */

public class ZhuCeDo implements Serializable{
    private int result;
    private boolean canGetRegCaijin;
    private int regCaijinMoney;
    private int regCaijinNeedDepositMoney;
    private int regCaijinLiushuiBeishu;
    private String description;



    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isCanGetRegCaijin() {
        return canGetRegCaijin;
    }

    public void setCanGetRegCaijin(boolean canGetRegCaijin) {
        this.canGetRegCaijin = canGetRegCaijin;
    }

    public int getRegCaijinMoney() {
        return regCaijinMoney;
    }

    public void setRegCaijinMoney(int regCaijinMoney) {
        this.regCaijinMoney = regCaijinMoney;
    }

    public int getRegCaijinNeedDepositMoney() {
        return regCaijinNeedDepositMoney;
    }

    public void setRegCaijinNeedDepositMoney(int regCaijinNeedDepositMoney) {
        this.regCaijinNeedDepositMoney = regCaijinNeedDepositMoney;
    }

    public int getRegCaijinLiushuiBeishu() {
        return regCaijinLiushuiBeishu;
    }

    public void setRegCaijinLiushuiBeishu(int regCaijinLiushuiBeishu) {
        this.regCaijinLiushuiBeishu = regCaijinLiushuiBeishu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
