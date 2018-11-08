package com.dafa.qipai.dafaqipai.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pc on 2017/4/14.
 */

public class TiXIanJiLuDo implements Serializable {

    /**
     * result : 1
     * description :
     * pageNum : 1
     * pageSize : 25
     * size : 1
     * total : 1
     * firstPage : 1
     * prePage : 0
     * nextPage : 0
     * lastPage : 1
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1]
     * pageMoney : 2
     * totalMoney : 2
     * userWithdrawList : [{"orderNo":"3","time":1479982526000,"type":4,"money":2,"remarks":"1","status":1}]
     */


    private int result;
    private String description;
    private int pageNum;
    private int pageSize;
    private int size;
    private int total;
    private int firstPage;
    private int prePage;
    private int nextPage;
    private int lastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private BigDecimal pageMoney;
    private BigDecimal totalMoney;
    private List<Integer> navigatepageNums;
    private List<UserWithdrawListBean> userWithdrawList;

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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public BigDecimal getPageMoney() {
        return pageMoney;
    }

    public void setPageMoney(BigDecimal pageMoney) {
        this.pageMoney = pageMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public List<UserWithdrawListBean> getUserWithdrawList() {
        return userWithdrawList;
    }

    public void setUserWithdrawList(List<UserWithdrawListBean> userWithdrawList) {
        this.userWithdrawList = userWithdrawList;
    }

    public static class UserWithdrawListBean implements Serializable {
        /**
         * orderNo : 3
         * time : 1479982526000
         * type : 4
         * money : 2
         * remarks : 1
         * status : 1
         */

        private String orderNo;
        private long time;
        private int type;
        private BigDecimal money;
        private String remarks;
        private int status;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserWithdrawListBean that = (UserWithdrawListBean) o;

            if (time != that.time) return false;
            if (type != that.type) return false;
            if (status != that.status) return false;
            if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null)
                return false;
            if (money != null ? !money.equals(that.money) : that.money != null) return false;
            return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;

        }

        @Override
        public int hashCode() {
            int result = orderNo != null ? orderNo.hashCode() : 0;
            result = 31 * result + (int) (time ^ (time >>> 32));
            result = 31 * result + type;
            result = 31 * result + (money != null ? money.hashCode() : 0);
            result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
            result = 31 * result + status;
            return result;
        }
    }
}
