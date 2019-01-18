package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

public class DoYuxoi {


    /**
     * result : 1
     * description :
     * list : [{"pk":"4680319b72cb481b86355c65f7695fd5","gamename":"德州扑克","createTime":1544093760000,"totalMoney":1,"effectiveMoney":1,"zjMoney":-1,"status":1,"account":"zz111111","content":"德州扑克","number":"720000003","orderNumber":"50-1544093460-577059658-2","updateTime":1544093460000,"sorting":1070633036202639360,"fandian":0,"companyShortName":"ky","agentId":50044,"type":"1","appid":null},{"pk":"f5511a2132144ad2af26f5b27deef662","gamename":"二八杠","createTime":1544093520000,"totalMoney":117,"effectiveMoney":117,"zjMoney":-115,"status":1,"account":"zz111111","content":"二八杠","number":"1440200047","orderNumber":"50-1544093123-577047940-4","updateTime":1544093123000,"sorting":1070632077355384832,"fandian":0,"companyShortName":"ky","agentId":50044,"type":"1","appid":null},{"pk":"4a54b39985b844dfbe4b36a19612a723","gamename":"二八杠","createTime":1544093520000,"totalMoney":49,"effectiveMoney":49,"zjMoney":46.55,"status":1,"account":"zz111111","content":"二八杠","number":"1440200047","orderNumber":"50-1544093087-577047940-4","updateTime":1544093087000,"sorting":1070632074398400512,"fandian":2.45,"companyShortName":"ky","agentId":50044,"type":"1","appid":null},{"pk":"6796321225654610aa69b590968cc015","gamename":"德州扑克","createTime":1544093520000,"totalMoney":1,"effectiveMoney":1,"zjMoney":-1,"status":1,"account":"zz111111","content":"德州扑克","number":"720000015","orderNumber":"50-1544093220-577052738-9","updateTime":1544093220000,"sorting":1070632013069287424,"fandian":0,"companyShortName":"ky","agentId":50044,"type":"1","appid":null}]
     * cPage : 4
     * cNumber : 16
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
         * pk : 4680319b72cb481b86355c65f7695fd5
         * gamename : 德州扑克
         * createTime : 1544093760000
         * totalMoney : 1.0
         * effectiveMoney : 1.0
         * zjMoney : -1.0
         * status : 1
         * account : zz111111
         * content : 德州扑克
         * number : 720000003
         * orderNumber : 50-1544093460-577059658-2
         * updateTime : 1544093460000
         * sorting : 1070633036202639360
         * fandian : 0.0
         * companyShortName : ky
         * agentId : 50044
         * type : 1
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
        private long updateTime;
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

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
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
