package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

public class DoDaili {


    /**
     * result : 1
     * description :
     * list : [{"id":400809,"account":"代理天天","weixin":"","qq":"12345678","phone":"13245678907","email":"818286@qq.com","imgUrl":"http://103.233.250.90:8080/1.png","createTime":1543483123000,"companyShortName":"a2"},{"id":400810,"account":"代理笑笑","weixin":"","qq":"","phone":"13245678907","email":"818286@qq.com","imgUrl":"http://103.233.250.90:8080/2.png","createTime":1543483171000,"companyShortName":"a2"},{"id":400811,"account":"代理美美","weixin":"wx33333","qq":"33333333","phone":"13333333333","email":"818286@qq.com","imgUrl":"http://103.233.250.90:8080/3.jpg","createTime":1543483586000,"companyShortName":"a2"},{"id":400812,"account":"summer","weixin":"wx4444","qq":"","phone":"","email":"818286@qq.com","imgUrl":"http://103.233.250.90:8080/4.png","createTime":1543483621000,"companyShortName":"a2"}]
     */

    private int result;
    private String description;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 400809
         * account : 代理天天
         * weixin :
         * qq : 12345678
         * phone : 13245678907
         * email : 818286@qq.com
         * imgUrl : http://103.233.250.90:8080/1.png
         * createTime : 1543483123000
         * companyShortName : a2
         */

        private int id;
        private String account;
        private String weixin;
        private String qq;
        private String phone;
        private String email;
        private String imgUrl;
        private long createTime;
        private String companyShortName;
        private String payTreasure;

        public String getPayTreasure() {
            return payTreasure;
        }

        public void setPayTreasure(String payTreasure) {
            this.payTreasure = payTreasure;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCompanyShortName() {
            return companyShortName;
        }

        public void setCompanyShortName(String companyShortName) {
            this.companyShortName = companyShortName;
        }
    }
}
