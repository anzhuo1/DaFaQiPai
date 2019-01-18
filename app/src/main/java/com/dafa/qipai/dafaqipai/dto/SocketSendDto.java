package com.dafa.qipai.dafaqipai.dto;

import java.util.Date;

public class SocketSendDto {

    public SocketSendDto(String type, String uid) {
        this.type = type;
        this.uid = uid;

    }

    /**

     * type : “ky”
     * uid : 999
     * date : new Date()
     */

    private String type;
    private String uid;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


}
