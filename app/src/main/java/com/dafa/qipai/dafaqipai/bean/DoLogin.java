package com.dafa.qipai.dafaqipai.bean;

/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2017-03-02 11:22
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */

public class DoLogin {
    /**
     * result : 1
     * description : null
     * userId : 172
     * token : 13f5b0b241474bd48a52c969c4be03e6
     * webNoticeList : null
     */

    private int result;
    private String description;
    private int userId;
    private String token;
    private String webNoticeList;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWebNoticeList() {
        return webNoticeList;
    }

    public void setWebNoticeList(String webNoticeList) {
        this.webNoticeList = webNoticeList;
    }
}
