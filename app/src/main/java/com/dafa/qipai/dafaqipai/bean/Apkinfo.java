package com.dafa.qipai.dafaqipai.bean;

/**
 * User: ZiYeYouHu
 * Date: 2016-07-04
 * Time: 12:27
 * Des:
 * FIXME
 */
public class Apkinfo {


    /**
     * result : 1
     * version : 0.0.1
     * type : 0
     * remarks :
     * url :
     * force : false
     */

    private int result;
    private String version;
    private String type;
    private String remarks;
    private String url;
    private boolean force;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
