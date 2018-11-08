package com.dafa.qipai.dafaqipai.bean;

/**
 * Created by shn on 2017/7/17.
 */
public class DoLayer {


    /**
     * result : 1
     * description : null
     * canDeposit : false
     * canWithdraw : false
     * canEditInfo : false
     * canEditUserBank : false
     * canEditPassword : false
     * canAgent : false
     * canSafeinfo : null
     */

    private int result;
    private Object description;
    private boolean canDeposit;
    private boolean canWithdraw;
    private boolean canEditInfo;
    private boolean canEditUserBank;
    private boolean canEditPassword;
    private boolean canAgent;
    private Object canSafeinfo;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public boolean isCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public boolean isCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public boolean isCanEditInfo() {
        return canEditInfo;
    }

    public void setCanEditInfo(boolean canEditInfo) {
        this.canEditInfo = canEditInfo;
    }

    public boolean isCanEditUserBank() {
        return canEditUserBank;
    }

    public void setCanEditUserBank(boolean canEditUserBank) {
        this.canEditUserBank = canEditUserBank;
    }

    public boolean isCanEditPassword() {
        return canEditPassword;
    }

    public void setCanEditPassword(boolean canEditPassword) {
        this.canEditPassword = canEditPassword;
    }

    public boolean isCanAgent() {
        return canAgent;
    }

    public void setCanAgent(boolean canAgent) {
        this.canAgent = canAgent;
    }

    public Object getCanSafeinfo() {
        return canSafeinfo;
    }

    public void setCanSafeinfo(Object canSafeinfo) {
        this.canSafeinfo = canSafeinfo;
    }
}
