package com.dafa.qipai.dafaqipai.bean;

/**
 * Created by shn on 2017/3/12.
 */
public class DoBindStatus {

    /**
     * result : 1
     * description : null
     * isBindEmail : false
     * isBindTelephone : false
     * isBindBankcard : false
     */

    private int result;
    private Object description;
    private boolean isBindEmail;
    private boolean isBindTelephone;
    private boolean isBindBankcard;

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

    public boolean isIsBindEmail() {
        return isBindEmail;
    }

    public void setIsBindEmail(boolean isBindEmail) {
        this.isBindEmail = isBindEmail;
    }

    public boolean isIsBindTelephone() {
        return isBindTelephone;
    }

    public void setIsBindTelephone(boolean isBindTelephone) {
        this.isBindTelephone = isBindTelephone;
    }

    public boolean isIsBindBankcard() {
        return isBindBankcard;
    }

    public void setIsBindBankcard(boolean isBindBankcard) {
        this.isBindBankcard = isBindBankcard;
    }
}
