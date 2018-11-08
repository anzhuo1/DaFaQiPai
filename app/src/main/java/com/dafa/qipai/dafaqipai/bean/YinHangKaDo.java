package com.dafa.qipai.dafaqipai.bean;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 2017/4/14.
 */

public class YinHangKaDo implements Serializable {

    /**
     * result : 1
     * description :
     * userBankCardList : [{"id":47,"bankName":"中国工商银行","subBankName":"罅些喇提督大马路支行","userName":"测试一","bankAccount":"62245709834148278","location":null,"default":true}]
     */

    private int result;
    private String description;
    private List<UserBankCardListBean> userBankCardList;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserBankCardListBean> getUserBankCardList() {
        return userBankCardList;
    }

    public void setUserBankCardList(List<UserBankCardListBean> userBankCardList) {
        this.userBankCardList = userBankCardList;
    }

    public static class UserBankCardListBean implements Serializable, Comparable<UserBankCardListBean> {
        /**
         * id : 47
         * bankName : 中国工商银行
         * subBankName : 罅些喇提督大马路支行
         * userName : 测试一
         * bankAccount : 62245709834148278
         * location : null
         * default : true
         */

        private int id;
        private String bankName;
        private String subBankName;
        private String userName;
        private String bankAccount;
        private String location;
        @SerializedName("default")
        private Boolean defaultX;
        private boolean isCheck;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getSubBankName() {
            return subBankName;
        }

        public void setSubBankName(String subBankName) {
            this.subBankName = subBankName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Boolean getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(Boolean defaultX) {
            this.defaultX = defaultX;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        @Override
        public int compareTo(@NonNull UserBankCardListBean that) {

            return this.getDefaultX().compareTo(that.getDefaultX());
        }

        @Override
        public String toString() {
            return "UserBankCardListBean{" +
                    "id=" + id +
                    ", bankName='" + bankName + '\'' +
                    ", subBankName='" + subBankName + '\'' +
                    ", userName='" + userName + '\'' +
                    ", bankAccount='" + bankAccount + '\'' +
                    ", location='" + location + '\'' +
                    ", defaultX=" + defaultX +
                    ", isCheck=" + isCheck +
                    '}';
        }
    }
}
