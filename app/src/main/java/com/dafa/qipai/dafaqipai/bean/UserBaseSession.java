package com.dafa.qipai.dafaqipai.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by pc on 2017/4/18.
 */

public class UserBaseSession implements Serializable {

    /**
     * result : 1
     * description :
     * id : 190
     * registerIp :
     * sex : 1
     * birthday : 1492482016000
     * nickname : apptest1
     * email :
     * account : apptest1
     * name : 测试一
     * telephone :
     * qq : 112233
     * skype :
     * msn :
     * balance : 100755.627
     * freezingAmount : 99999.99
     * registerTime : 1489924070000
     * layerId : 1
     * layerName : 普通会员
     * privateUrl : f.xiaosanseo.com
     * fandian : 2.0
     * totalFandianMoney : 43.04
     * subUserTotalFandianMoney : 0.72
     * agentDomain : null
     * lastGameId : 2222
     * lastPlayId : 11111
     * lastLoginTime : 1492480749000
     * lastWeekValidBetMoney : 2462.0
     * thisWeekValidBetMoney : 30.0
     * lastDepositTime : 1490338369000
     * lastWithdrawTime : 1491786436000
     * lastLoginIp : 202.100.199.217
     * enable : true
     * frozen : false
     */

    private int result;
    private String description;
    private int id;
    private String registerIp;
    private int sex;
    private long birthday;
    private String nickname;
    private String email;
    private String account;
    private String name;
    private String telephone;
    private String qq;
    private String skype;
    private String msn;
    private double balance;
    private double freezingAmount;
    private long registerTime;
    private int layerId;
    private String layerName;
    private String privateUrl;
    private double fandian;
    private BigDecimal totalFandianMoney;
    private BigDecimal subUserTotalFandianMoney;
    private Object agentDomain;
    private int lastGameId;
    private int lastPlayId;
    private long lastLoginTime;
    private BigDecimal lastWeekValidBetMoney;
    private BigDecimal thisWeekValidBetMoney;
    private long lastDepositTime;
    private long lastWithdrawTime;
    private String lastLoginIp;
    private boolean enable;
    private boolean frozen;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getFreezingAmount() {
        return freezingAmount;
    }

    public void setFreezingAmount(double freezingAmount) {
        this.freezingAmount = freezingAmount;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public int getLayerId() {
        return layerId;
    }

    public void setLayerId(int layerId) {
        this.layerId = layerId;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getPrivateUrl() {
        return privateUrl;
    }

    public void setPrivateUrl(String privateUrl) {
        this.privateUrl = privateUrl;
    }

    public double getFandian() {
        return fandian;
    }

    public void setFandian(double fandian) {
        this.fandian = fandian;
    }

    public BigDecimal getTotalFandianMoney() {
        return totalFandianMoney;
    }

    public void setTotalFandianMoney(BigDecimal totalFandianMoney) {
        this.totalFandianMoney = totalFandianMoney;
    }

    public BigDecimal getSubUserTotalFandianMoney() {
        return subUserTotalFandianMoney;
    }

    public void setSubUserTotalFandianMoney(BigDecimal subUserTotalFandianMoney) {
        this.subUserTotalFandianMoney = subUserTotalFandianMoney;
    }

    public Object getAgentDomain() {
        return agentDomain;
    }

    public void setAgentDomain(Object agentDomain) {
        this.agentDomain = agentDomain;
    }

    public int getLastGameId() {
        return lastGameId;
    }

    public void setLastGameId(int lastGameId) {
        this.lastGameId = lastGameId;
    }

    public int getLastPlayId() {
        return lastPlayId;
    }

    public void setLastPlayId(int lastPlayId) {
        this.lastPlayId = lastPlayId;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public BigDecimal getLastWeekValidBetMoney() {
        return lastWeekValidBetMoney;
    }

    public void setLastWeekValidBetMoney(BigDecimal lastWeekValidBetMoney) {
        this.lastWeekValidBetMoney = lastWeekValidBetMoney;
    }

    public BigDecimal getThisWeekValidBetMoney() {
        return thisWeekValidBetMoney;
    }

    public void setThisWeekValidBetMoney(BigDecimal thisWeekValidBetMoney) {
        this.thisWeekValidBetMoney = thisWeekValidBetMoney;
    }

    public long getLastDepositTime() {
        return lastDepositTime;
    }

    public void setLastDepositTime(long lastDepositTime) {
        this.lastDepositTime = lastDepositTime;
    }

    public long getLastWithdrawTime() {
        return lastWithdrawTime;
    }

    public void setLastWithdrawTime(long lastWithdrawTime) {
        this.lastWithdrawTime = lastWithdrawTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
