package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

/**
 * Created by pc on 2017/4/13.
 */

public class ZhanNeiXinDo {

    /**
     * result : 1
     * description : null
     * pageNum : 1
     * pageSize : 20
     * size : 0
     * total : 0
     * firstPage : 0
     * prePage : 0
     * nextPage : 0
     * lastPage : 0
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1,2]
     * userInboxList : []
     * totalNum : 0
     * totalHasReadNum : 0
     * totalUnReadNum : 0
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
    private int totalNum;
    private int totalHasReadNum;
    private int totalUnReadNum;
    private List<Integer> navigatepageNums;
    private List<UserInboxBean> userInboxList;

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

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalHasReadNum() {
        return totalHasReadNum;
    }

    public void setTotalHasReadNum(int totalHasReadNum) {
        this.totalHasReadNum = totalHasReadNum;
    }

    public int getTotalUnReadNum() {
        return totalUnReadNum;
    }

    public void setTotalUnReadNum(int totalUnReadNum) {
        this.totalUnReadNum = totalUnReadNum;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public List<UserInboxBean> getUserInboxList() {
        return userInboxList;
    }

    public void setUserInboxList(List<UserInboxBean> userInboxList) {
        this.userInboxList = userInboxList;
    }

    public class UserInboxBean {
        private Long id;
        private String content;
        private Long createTime;
        private Boolean hasRead;
        private boolean isTast;

        public UserInboxBean(boolean isTast) {
            this.isTast = isTast;
        }

        public boolean isTast() {
            return isTast;
        }

        public void setTast(boolean tast) {
            isTast = tast;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Boolean getHasRead() {
            return hasRead;
        }

        public void setHasRead(Boolean hasRead) {
            this.hasRead = hasRead;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserInboxBean that = (UserInboxBean) o;

            if (isTast != that.isTast) return false;
            if (id != null ? !id.equals(that.id) : that.id != null) return false;
            if (content != null ? !content.equals(that.content) : that.content != null)
                return false;
            if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
                return false;
            return hasRead != null ? hasRead.equals(that.hasRead) : that.hasRead == null;

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (content != null ? content.hashCode() : 0);
            result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
            result = 31 * result + (hasRead != null ? hasRead.hashCode() : 0);
            result = 31 * result + (isTast ? 1 : 0);
            return result;
        }
    }
}
