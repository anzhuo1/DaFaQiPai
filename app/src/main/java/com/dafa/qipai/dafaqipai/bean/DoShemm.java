package com.dafa.qipai.dafaqipai.bean;

import java.math.BigDecimal;
import java.util.List;

public class DoShemm {


    /**
     * result : 1
     * loginType : 0
     * list : [{"id":400806,"account":"mumu22","createTime":1541691071000,"money":4205,"password":"ecdd43d1add2856113d85a99acbc43a0"}]
     * wallet : 4205
     */

    private int result;
    private int loginType;
    private BigDecimal wallet;
    private List<ListBean> list;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 400806
         * account : mumu22
         * createTime : 1541691071000
         * money : 4205
         * password : ecdd43d1add2856113d85a99acbc43a0
         */

        private int id;
        private String account;
        private long createTime;
        private BigDecimal money;
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
