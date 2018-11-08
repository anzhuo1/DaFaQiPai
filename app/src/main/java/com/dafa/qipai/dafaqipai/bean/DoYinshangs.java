package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

/**
 * Created by shn on 2017/5/1.
 */
public class DoYinshangs {

    /**
     * result : 1
     * description : null
     * czlxList : [{"id":0,"name":"任意"},{"id":1,"name":"银行转账"},{"id":2,"name":"支付宝转账"},{"id":3,"name":"徽信转账"},{"id":4,"name":"财付通转账"},{"id":5,"name":"在线支付"}]
     * bankList : [{"id":1,"name":"工商银行"},{"id":2,"name":"招商银行"},{"id":3,"name":"建设银行"},{"id":4,"name":"农业银行"},{"id":5,"name":"中国银行"},{"id":6,"name":"北京银行"},{"id":7,"name":"交通银行"},{"id":8,"name":"东莞银行"},{"id":9,"name":"广州银行"},{"id":10,"name":"杭州银行"},{"id":11,"name":"上海银行"},{"id":12,"name":"渤海银行"},{"id":13,"name":"中信银行"},{"id":14,"name":"光大银行"},{"id":15,"name":"广发银行"},{"id":16,"name":"民生银行"},{"id":17,"name":"浙商银行"},{"id":18,"name":"兴业银行"},{"id":19,"name":"中国邮政储蓄"},{"id":20,"name":"浦发银行"},{"id":21,"name":"深圳发展银行"},{"id":22,"name":"华夏银行"},{"id":23,"name":"平安银行"},{"id":24,"name":"北京农村商业银行"},{"id":25,"name":"柳州银行"},{"id":26,"name":"江苏省农村信用社联合社"},{"id":27,"name":"吉林省农村信用社联合社"},{"id":28,"name":"龙江银行"},{"id":29,"name":"上海农商银行"},{"id":30,"name":"其他银行"}]
     */

    private int result;
    private Object description;
    /**
     * id : 0
     * name : 任意
     */

    private List<CzlxListBean> czlxList;
    /**
     * id : 1
     * name : 工商银行
     */

    private List<BankListBean> bankList;

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

    public List<CzlxListBean> getCzlxList() {
        return czlxList;
    }

    public void setCzlxList(List<CzlxListBean> czlxList) {
        this.czlxList = czlxList;
    }

    public List<BankListBean> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankListBean> bankList) {
        this.bankList = bankList;
    }

    public static class CzlxListBean {
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

    public static class BankListBean {
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
