package com.dafa.qipai.dafaqipai.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pc on 2017/4/6.
 */

public class ZiJinJiLuDo implements Serializable {

    /**
     * description :
     * logUserCoinList : [{"leftCoin":138800.085,"pk":"7abd5da97daf46e9b2f989ff9f24090d","createTime":1491401641000,"money":78800,"coinOperType":12,"remarks":"注单号：20170405849625942444527616"},{"leftCoin":60000.085,"pk":"11ce997a023c46f8987f992608d6784a","createTime":1491401584000,"money":-40000,"coinOperType":11,"remarks":""}]
     * hasNextPage : false
     * navigatepageNums : [1]
     * pageNum : 1
     * lastPage : 1
     * total : 2
     * nextPage : 0
     * navigatePages : 8
     * size : 0
     * hasPreviousPage : false
     * prePage : 0
     * result : 1
     * firstPage : 1
     * pageSize : 10
     */

    private String description;
    private boolean hasNextPage;
    private int pageNum;
    private int lastPage;
    private int total;
    private int nextPage;
    private int navigatePages;
    private int size;
    private boolean hasPreviousPage;
    private int prePage;
    private int result;
    private int firstPage;
    private int pageSize;
    private List<LogUserCoinListBean> logUserCoinList;
    private List<Integer> navigatepageNums;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<LogUserCoinListBean> getLogUserCoinList() {
        return logUserCoinList;
    }

    public void setLogUserCoinList(List<LogUserCoinListBean> logUserCoinList) {
        this.logUserCoinList = logUserCoinList;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class LogUserCoinListBean {
        /**
         * leftCoin : 138800.085
         * pk : 7abd5da97daf46e9b2f989ff9f24090d
         * createTime : 1491401641000
         * money : 78800
         * coinOperType : 12
         * remarks : 注单号：20170405849625942444527616
         */

        private BigDecimal leftCoin;
        private String pk;
        private long createTime;
        private BigDecimal money;
        private int coinOperType;
        private String remarks;

        public BigDecimal getLeftCoin() {
            return leftCoin;
        }

        public void setLeftCoin(BigDecimal leftCoin) {
            this.leftCoin = leftCoin;
        }

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
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

        public int getCoinOperType() {
            return coinOperType;
        }

        public void setCoinOperType(int coinOperType) {
            this.coinOperType = coinOperType;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LogUserCoinListBean that = (LogUserCoinListBean) o;

            if (createTime != that.createTime) return false;
            if (coinOperType != that.coinOperType) return false;
            if (leftCoin != null ? !leftCoin.equals(that.leftCoin) : that.leftCoin != null)
                return false;
            if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;
            if (money != null ? !money.equals(that.money) : that.money != null) return false;
            return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;

        }

        @Override
        public int hashCode() {
            int result = leftCoin != null ? leftCoin.hashCode() : 0;
            result = 31 * result + (pk != null ? pk.hashCode() : 0);
            result = 31 * result + (int) (createTime ^ (createTime >>> 32));
            result = 31 * result + (money != null ? money.hashCode() : 0);
            result = 31 * result + coinOperType;
            result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
            return result;
        }
    }
}
