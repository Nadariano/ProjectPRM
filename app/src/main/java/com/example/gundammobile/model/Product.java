package com.example.gundammobile.model;

public class Product {
    private String PRODUCT_ID, PRODUCTNAME, ORIGIN, PRODUCTDETAILS, PRODUCTMATERIAL,
            TYPE_ID, TYPENAME, RATIO, HEIGHT, BRANDNAME, PRODUCTIMAGE;
    private int BRAND_ID, QUANTITY;

    public String getPRODUCTIMAGE() {
        return PRODUCTIMAGE;
    }

    public void setPRODUCTIMAGE(String PRODUCTIMAGE) {
        this.PRODUCTIMAGE = PRODUCTIMAGE;
    }

    private float PRICE;

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getPRODUCTNAME() {
        return PRODUCTNAME;
    }

    public void setPRODUCTNAME(String PRODUCTNAME) {
        this.PRODUCTNAME = PRODUCTNAME;
    }

    public String getORIGIN() {
        return ORIGIN;
    }

    public void setORIGIN(String ORIGIN) {
        this.ORIGIN = ORIGIN;
    }

    public String getPRODUCTDETAILS() {
        return PRODUCTDETAILS;
    }

    public void setPRODUCTDETAILS(String PRODUCTDETAILS) {
        this.PRODUCTDETAILS = PRODUCTDETAILS;
    }

    public String getPRODUCTMATERIAL() {
        return PRODUCTMATERIAL;
    }

    public void setPRODUCTMATERIAL(String PRODUCTMATERIAL) {
        this.PRODUCTMATERIAL = PRODUCTMATERIAL;
    }

    public String getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(String TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public String getTYPENAME() {
        return TYPENAME;
    }

    public void setTYPENAME(String TYPENAME) {
        this.TYPENAME = TYPENAME;
    }

    public String getRATIO() {
        return RATIO;
    }

    public void setRATIO(String RATIO) {
        this.RATIO = RATIO;
    }

    public String getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(String HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public String getBRANDNAME() {
        return BRANDNAME;
    }

    public void setBRANDNAME(String BRANDNAME) {
        this.BRANDNAME = BRANDNAME;
    }

    public int getBRAND_ID() {
        return BRAND_ID;
    }

    public void setBRAND_ID(int BRAND_ID) {
        this.BRAND_ID = BRAND_ID;
    }

    public float getPRICE() {
        return PRICE;
    }

    public void setPRICE(float PRICE) {
        this.PRICE = PRICE;
    }
}

