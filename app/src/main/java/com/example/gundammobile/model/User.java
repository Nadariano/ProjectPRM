package com.example.gundammobile.model;

public class User {
    private String EMAIL, ACCOUNT_PASS, ACCOUNT_NAME, ACCOUNT_PHONE, ACCOUNT_ADDRESS;

    private int ACCOUNT_ID;

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public User(String EMAIL, String ACCOUNT_PASS, String ACCOUNT_NAME, String ACCOUNT_PHONE, String ACCOUNT_ADDRESS) {
        this.EMAIL = EMAIL;
        this.ACCOUNT_PASS = ACCOUNT_PASS;
        this.ACCOUNT_NAME = ACCOUNT_NAME;
        this.ACCOUNT_PHONE = ACCOUNT_PHONE;
        this.ACCOUNT_ADDRESS = ACCOUNT_ADDRESS;
    }

    public User(String EMAIL, String ACCOUNT_PASS) {
        this.EMAIL = EMAIL;
        this.ACCOUNT_PASS = ACCOUNT_PASS;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getACCOUNT_PASS() {
        return ACCOUNT_PASS;
    }

    public void setACCOUNT_PASS(String ACCOUNT_PASS) {
        this.ACCOUNT_PASS = ACCOUNT_PASS;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public String getACCOUNT_PHONE() {
        return ACCOUNT_PHONE;
    }

    public void setACCOUNT_PHONE(String ACCOUNT_PHONE) {
        this.ACCOUNT_PHONE = ACCOUNT_PHONE;
    }

    public String getACCOUNT_ADDRESS() {
        return ACCOUNT_ADDRESS;
    }

    public void setACCOUNT_ADDRESS(String ACCOUNT_ADDRESS) {
        this.ACCOUNT_ADDRESS = ACCOUNT_ADDRESS;
    }
}
