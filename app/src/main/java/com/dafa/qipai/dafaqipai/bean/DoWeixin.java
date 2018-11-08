package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

/**
 * Created by shn on 2017/4/30.
 */
public class DoWeixin {

    /**
     * result : 1
     * description : null
     * skInfoList : [{"id":1,"username":"142421","account":"21","imageId":27,"minMoney":1,"maxMoney":2,"description":"214","imageData":null}]
     */

    private int result;
    private String description;
    /**
     * id : 1
     * username : 142421
     * account : 21
     * imageId : 27
     * minMoney : 1.0
     * maxMoney : 2.0
     * description : 214
     * imageData : null
     */

    private List<SkInfoListBean> skInfoList;

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

    public List<SkInfoListBean> getSkInfoList() {
        return skInfoList;
    }

    public void setSkInfoList(List<SkInfoListBean> skInfoList) {
        this.skInfoList = skInfoList;
    }

    public static class SkInfoListBean {
        private int id;
        private String username;
        private String account;
        private int imageId;
        private double minMoney;
        private double maxMoney;
        private String description;
        private String imageData;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public double getMinMoney() {
            return minMoney;
        }

        public void setMinMoney(double minMoney) {
            this.minMoney = minMoney;
        }

        public double getMaxMoney() {
            return maxMoney;
        }

        public void setMaxMoney(double maxMoney) {
            this.maxMoney = maxMoney;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageData() {
            return imageData;
        }

        public void setImageData(String imageData) {
            this.imageData = imageData;
        }
    }
}
