package com.example.gundammobile.model;


public class Coupon {
    private String DISCOUNTID;
    private int DISCOUNTRATE;

    public Coupon(String DISCOUNTID, int DISCOUNTRATE) {
        this.DISCOUNTID = DISCOUNTID;
        this.DISCOUNTRATE = DISCOUNTRATE;
    }

    public String getDISCOUNTID() {
        return DISCOUNTID;
    }

    public void setDISCOUNTID(String DISCOUNTID) {
        this.DISCOUNTID = DISCOUNTID;
    }

    public int getDISCOUNTRATE() {
        return DISCOUNTRATE;
    }

    public void setDISCOUNTRATE(int DISCOUNTRATE) {
        this.DISCOUNTRATE = DISCOUNTRATE;
    }
}
