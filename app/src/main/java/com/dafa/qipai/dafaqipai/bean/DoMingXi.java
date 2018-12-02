package com.dafa.qipai.dafaqipai.bean;

import java.math.BigDecimal;
import java.util.List;

public class DoMingXi {

    /**
     * result : 1
     * description :
     * list : [{"id":"d2088bbe65d34449b56d820b5c4d3cbc","userId":401323,"createTime":1542540760000,"type":"deposit","operationMoney":0,"balance":300.96},{"id":"53f44ddc7e7d45b890090e22ecc572d2","userId":401323,"createTime":1542531685000,"type":"deposit","operationMoney":0,"balance":300.96}]
     * perPage : 1
     * nextPage : 2
     * indexPage : 1
     * total : 41
     */

    private int result;
    private String description;
    private int perPage;
    private int nextPage;
    private int indexPage;
    private int total;
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

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(int indexPage) {
        this.indexPage = indexPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : d2088bbe65d34449b56d820b5c4d3cbc
         * userId : 401323
         * createTime : 1542540760000
         * type : deposit
         * operationMoney : 0.0
         * balance : 300.96
         */

        private String id;
        private int userId;
        private long createTime;
        private String type;
        private BigDecimal operationMoney;
        private BigDecimal balance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BigDecimal getOperationMoney() {
            return operationMoney;
        }

        public void setOperationMoney(BigDecimal operationMoney) {
            this.operationMoney = operationMoney;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }
    }
}
