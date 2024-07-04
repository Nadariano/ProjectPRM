package com.example.gundammobile.model;

import java.util.Date;

public class Order {
    private int ORDER_ID;
    private Date ORDER_DATE;
    private int ACCOUNT_ID;
    private String ACCOUNT_NAME;
    private float TOTALMONEY;
    private String ORDER_NOTE;
    private String PAYMENTMETHOD;

    public Order(int ORDER_ID, Date ORDER_DATE, int ACCOUNT_ID, String ACCOUNT_NAME, float TOTALMONEY, String ORDER_NOTE, String PAYMENTMETHOD) {
        this.ORDER_ID = ORDER_ID;
        this.ORDER_DATE = ORDER_DATE;
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.ACCOUNT_NAME = ACCOUNT_NAME;
        this.TOTALMONEY = TOTALMONEY;
        this.ORDER_NOTE = ORDER_NOTE;
        this.PAYMENTMETHOD = PAYMENTMETHOD;
    }

    public Order(int ACCOUNT_ID, float TOTALMONEY, String ORDER_NOTE, String PAYMENTMETHOD) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.TOTALMONEY = TOTALMONEY;
        this.ORDER_NOTE = ORDER_NOTE;
        this.PAYMENTMETHOD = PAYMENTMETHOD;
    }


    public int getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(int ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public Date getORDER_DATE() {
        return ORDER_DATE;
    }

    public void setORDER_DATE(Date ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public float getTOTALMONEY() {
        return TOTALMONEY;
    }

    public void setTOTALMONEY(float TOTALMONEY) {
        this.TOTALMONEY = TOTALMONEY;
    }

    public String getORDER_NOTE() {
        return ORDER_NOTE;
    }

    public void setORDER_NOTE(String ORDER_NOTE) {
        this.ORDER_NOTE = ORDER_NOTE;
    }

    public String getPAYMENTMETHOD() {
        return PAYMENTMETHOD;
    }

    public void setPAYMENTMETHOD(String PAYMENTMETHOD) {
        this.PAYMENTMETHOD = PAYMENTMETHOD;
    }
}
