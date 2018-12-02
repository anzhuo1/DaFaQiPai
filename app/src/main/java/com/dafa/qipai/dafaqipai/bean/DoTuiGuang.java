package com.dafa.qipai.dafaqipai.bean;

import java.util.List;

public class DoTuiGuang {

    /**
     * result : 1
     * description :
     * agentContact : [{"id":401226,"account":"客服小小","email":"","qq":"1314520","telephone":"","qrCodeUrl":"","creatTime":1543484642000,"companyShortName":"a2","weixin":"wx11111111"},{"id":401225,"account":"客服天天","email":"818286@qq.com","qq":"12345678","telephone":"13245678908","qrCodeUrl":"","creatTime":1543484610000,"companyShortName":"a2","weixin":""},{"id":401227,"account":"客服久久","email":"818286@qq.com","qq":"","telephone":"13245678908","qrCodeUrl":"","creatTime":1543484677000,"companyShortName":"a2","weixin":""}]
     */

    private int result;
    private String description;
    private List<AgentContactBean> agentContact;

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

    public List<AgentContactBean> getAgentContact() {
        return agentContact;
    }

    public void setAgentContact(List<AgentContactBean> agentContact) {
        this.agentContact = agentContact;
    }

    public static class AgentContactBean {
        /**
         * id : 401226
         * account : 客服小小
         * email :
         * qq : 1314520
         * telephone :
         * qrCodeUrl :
         * creatTime : 1543484642000
         * companyShortName : a2
         * weixin : wx11111111
         */

        private int id;
        private String account;
        private String email;
        private String qq;
        private String telephone;
        private String qrCodeUrl;
        private long creatTime;
        private String companyShortName;
        private String weixin;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getQrCodeUrl() {
            return qrCodeUrl;
        }

        public void setQrCodeUrl(String qrCodeUrl) {
            this.qrCodeUrl = qrCodeUrl;
        }

        public long getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(long creatTime) {
            this.creatTime = creatTime;
        }

        public String getCompanyShortName() {
            return companyShortName;
        }

        public void setCompanyShortName(String companyShortName) {
            this.companyShortName = companyShortName;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }
    }
}
