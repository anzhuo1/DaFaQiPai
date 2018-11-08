package com.dafa.qipai.dafaqipai.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shn on 2017/4/27.
 */
public class DoGetBankcard {

    /**
     * result : 1
     * description : null
     * userBankCardList : [{"id":58,"bankName":"中国邮政储蓄银行","subBankName":"佛争一炷香","userName":"去瞧瞧","bankAccount":"123654789087946","location":null,"default":true},{"id":55,"bankName":"交通银行","subBankName":"摁着我了","userName":"去瞧瞧","bankAccount":"1234567986036998","location":null,"default":false},{"id":54,"bankName":"中国农业银行","subBankName":"海口啦啦啦支行","userName":"去瞧瞧","bankAccount":"1234579986539497","location":null,"default":false},{"id":53,"bankName":"请选择银行","subBankName":"额咯low了","userName":"去瞧瞧","bankAccount":"9087654321147258","location":null,"default":false},{"id":52,"bankName":"请选择银行","subBankName":"额咯low了","userName":"去瞧瞧","bankAccount":"9087654321147258","location":null,"default":false}]
     */

    private int result;
    private Object description;
    /**
     * id : 58
     * bankName : 中国邮政储蓄银行
     * subBankName : 佛争一炷香
     * userName : 去瞧瞧
     * bankAccount : 123654789087946
     * location : null
     * default : true
     */

    private List<UserBankCardListBean> userBankCardList;

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

    public List<UserBankCardListBean> getUserBankCardList() {
        return userBankCardList;
    }

    public void setUserBankCardList(List<UserBankCardListBean> userBankCardList) {
        this.userBankCardList = userBankCardList;
    }

    public static class UserBankCardListBean {
        private int id;
        private String bankName;
        private String subBankName;
        private String userName;
        private String bankAccount;
        private Object location;
        @SerializedName("default")
        private boolean defaultX;

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

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public boolean isDefaultX() {
            return defaultX;
        }

        public void setDefaultX(boolean defaultX) {
            this.defaultX = defaultX;
        }
    }
}
