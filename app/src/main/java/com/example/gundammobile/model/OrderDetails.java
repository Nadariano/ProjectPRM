package com.example.gundammobile.model;

public class OrderDetails {
    private int ORDER_ID;
    private String PRODUCT_ID;
    private String PRODUCTNAME;
    private String PRODUCTIMAGE;
    private int QUANTITY;
    private float UNITPRICE;

    public OrderDetails(int ORDER_ID, String PRODUCT_ID, String PRODUCTNAME, String PRODUCTIMAGE, int QUANTITY, float UNITPRICE) {
        this.ORDER_ID = ORDER_ID;
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCTNAME = PRODUCTNAME;
        this.PRODUCTIMAGE = PRODUCTIMAGE;
        this.QUANTITY = QUANTITY;
        this.UNITPRICE = UNITPRICE;
    }

    public int getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(int ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
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

    public String getPRODUCTIMAGE() {
        return PRODUCTIMAGE;
    }

    public void setPRODUCTIMAGE(String PRODUCTIMAGE) {
        this.PRODUCTIMAGE = PRODUCTIMAGE;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public float getUNITPRICE() {
        return UNITPRICE;
    }

    public void setUNITPRICE(float UNITPRICE) {
        this.UNITPRICE = UNITPRICE;
    }
}
