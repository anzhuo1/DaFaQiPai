package com.dafa.qipai.dafaqipai.bean;

import java.math.BigDecimal;

public class DoZhenrenBbinYue {


    /**
     * result : 1
     * userBalance : 100000.0
     * agAmount : 0
     */

    private int result;
    private BigDecimal userBalance;
    private BigDecimal bbinAmount;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

    public BigDecimal getBbinAmount() {
        return bbinAmount;
    }

    public void setBbinAmount(BigDecimal bbinAmount) {
        this.bbinAmount = bbinAmount;
    }
}
