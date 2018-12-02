package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

public class DoYuxoi {


    /**
     * result : 1
     * description :
     * list : [{"pk":"cad9c92a4aa54f92a92b0ed78eeedc24","gamename":"乐游Fg-棋牌","createTime":1543483200000,"totalMoney":3,"effectiveMoney":3,"zjMoney":5.7,"status":1,"account":"cheng001","content":"poker","number":"6301392","orderNumber":"6301392","updateTime":1543483000,"sorting":1068072071283671040,"fandian":3,"companyShortName":"a2","agentId":1109,"type":"4","appid":null},{"pk":"f31dcc26d263418389e5329cdf64d951","gamename":"乐游Fg-棋牌","createTime":1543422720000,"totalMoney":2,"effectiveMoney":10187.45,"zjMoney":0,"status":1,"account":"cheng001","content":"poker","number":"60141088","orderNumber":"60141088","updateTime":1543422000,"sorting":1067818399781945344,"fandian":2,"companyShortName":"a2","agentId":1109,"type":"4","appid":null},{"pk":"52369b6a44fc4c52abf8651e21aa47fe","gamename":"乐游Fg-棋牌","createTime":1543422720000,"totalMoney":2,"effectiveMoney":10189.45,"zjMoney":0,"status":1,"account":"cheng001","content":"poker","number":"60141087","orderNumber":"60141087","updateTime":1543422000,"sorting":1067818399710642176,"fandian":2,"companyShortName":"a2","agentId":1109,"type":"4","appid":null},{"pk":"a2bcccaea05c4d0596c042990ceb9c17","gamename":"乐游Fg-棋牌","createTime":1543422720000,"totalMoney":2,"effectiveMoney":10191.45,"zjMoney":11.5,"status":1,"account":"cheng001","content":"poker","number":"60141084","orderNumber":"60141084","updateTime":1543422000,"sorting":1067818399526092800,"fandian":2,"companyShortName":"a2","agentId":1109,"type":"4","appid":null}]
     * cPage : 4
     * cNumber : 14
     */

    private int result;
    private String description;
    private int cPage;
    private int cNumber;
    private List<ListBean> list;

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

    public int getCPage() {
        return cPage;
    }

    public void setCPage(int cPage) {
        this.cPage = cPage;
    }

    public int getCNumber() {
        return cNumber;
    }

    public void setCNumber(int cNumber) {
        this.cNumber = cNumber;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * pk : cad9c92a4aa54f92a92b0ed78eeedc24
         * gamename : 乐游Fg-棋牌
         * createTime : 1543483200000
         * totalMoney : 3.0
         * effectiveMoney : 3.0
         * zjMoney : 5.7
         * status : 1
         * account : cheng001
         * content : poker
         * number : 6301392
         * orderNumber : 6301392
         * updateTime : 1543483000
         * sorting : 1068072071283671040
         * fandian : 3.0
         * companyShortName : a2
         * agentId : 1109
         * type : 4
         * appid : null
         */

        private String pk;
        private String gamename;
        private long createTime;
        private double totalMoney;
        private double effectiveMoney;
        private double zjMoney;
        private int status;
        private String account;
        private String content;
        private String number;
        private String orderNumber;
        private int updateTime;
        private long sorting;
        private double fandian;
        private String companyShortName;
        private int agentId;
        private String type;
        private Object appid;

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        public String getGamename() {
            return gamename;
        }

        public void setGamename(String gamename) {
            this.gamename = gamename;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public double getEffectiveMoney() {
            return effectiveMoney;
        }

        public void setEffectiveMoney(double effectiveMoney) {
            this.effectiveMoney = effectiveMoney;
        }

        public double getZjMoney() {
            return zjMoney;
        }

        public void setZjMoney(double zjMoney) {
            this.zjMoney = zjMoney;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public long getSorting() {
            return sorting;
        }

        public void setSorting(long sorting) {
            this.sorting = sorting;
        }

        public double getFandian() {
            return fandian;
        }

        public void setFandian(double fandian) {
            this.fandian = fandian;
        }

        public String getCompanyShortName() {
            return companyShortName;
        }

        public void setCompanyShortName(String companyShortName) {
            this.companyShortName = companyShortName;
        }

        public int getAgentId() {
            return agentId;
        }

        public void setAgentId(int agentId) {
            this.agentId = agentId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getAppid() {
            return appid;
        }

        public void setAppid(Object appid) {
            this.appid = appid;
        }
    }
}
