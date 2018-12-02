package com.dafa.qipai.dafaqipai.bean;

/**
 * Created by shn on 2018/9/5.
 * ziye
 */

public class DoZhenren {


    /**
     * result : 1
     * loginUrl : http://gci.076930.com:81/forwardGame.do
     * key : c8fe448a79c30e726343dcb4a79c760a
     * params : DERPMtnqGnfhVHfsjmKjYi6Ev/8L5ITEsXh0ryPWu/OPgN7Fmz4NyWFIwctVim3QOmp3VYOFAws3ARR2fMv2Cp70omaznXmk7Xf/LGAFxJWo6ujh8PF4f/2hgDIudM0FBIWBg14+6LGW4mQWeNKv2P0+LjQ0BbJbyFw6KfIiTNEWIEDHMfzKVoCKG4dgxxliFAvWI4v5RjMu7REMFHTr9734JeTQeXM1
     */

    private int result;
    private String loginUrl;
    private String key;
    private String params;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
