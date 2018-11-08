package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

/**
 * Created by shn on 2017/5/1.
 */
public class DoSSS {

    /**
     * result : 1
     * description : null
     * depositChannelList : [{"id":1,"name":"在线银行"},{"id":2,"name":"手机银行"},{"id":3,"name":"柜台转账"},{"id":4,"name":"ATM现金转账"},{"id":8,"name":"QQ转账"}]
     */

    private int result;
    private Object description;
    /**
     * id : 1
     * name : 在线银行
     */

    private List<DepositChannelListBean> depositChannelList;

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

    public List<DepositChannelListBean> getDepositChannelList() {
        return depositChannelList;
    }

    public void setDepositChannelList(List<DepositChannelListBean> depositChannelList) {
        this.depositChannelList = depositChannelList;
    }

    public static class DepositChannelListBean {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
