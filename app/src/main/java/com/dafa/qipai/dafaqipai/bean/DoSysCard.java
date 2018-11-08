package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

/**
 * Created by shn on 2017/5/1.
 */
public class DoSysCard {

    /**
     * result : 1
     * description : null
     * bankcardList : [{"id":1,"bankAccount":"8888888888888888888","bankName":"工商银行（测试）","userName":"张三（测试）","subBankName":"张三支行（测试）","isEnable":null,"updateTime":null,"createTime":null},{"id":2,"bankAccount":"123","bankName":"123","userName":"421","subBankName":"142","isEnable":null,"updateTime":null,"createTime":null}]
     */

    private int result;
    private Object description;
    /**
     * id : 1
     * bankAccount : 8888888888888888888
     * bankName : 工商银行（测试）
     * userName : 张三（测试）
     * subBankName : 张三支行（测试）
     * isEnable : null
     * updateTime : null
     * createTime : null
     */

    private List<BankcardListBean> bankcardList;

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

    public List<BankcardListBean> getBankcardList() {
        return bankcardList;
    }

    public void setBankcardList(List<BankcardListBean> bankcardList) {
        this.bankcardList = bankcardList;
    }

    public static class BankcardListBean {
        private int id;
        private String bankAccount;
        private String bankName;
        private String userName;
        private String subBankName;
        private Object isEnable;
        private Object updateTime;
        private Object createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSubBankName() {
            return subBankName;
        }

        public void setSubBankName(String subBankName) {
            this.subBankName = subBankName;
        }

        public Object getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(Object isEnable) {
            this.isEnable = isEnable;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }
    }
}
