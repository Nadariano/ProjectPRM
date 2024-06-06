package com.example.gundammobile.home.models;


public class Coupon {
    private String discountId;
    private int discountRate;

    // Constructor
    public Coupon(String discountId, int discountRate) {
        this.discountId = discountId;
        this.discountRate = discountRate;
    }

    // Getters
    public String getDiscountId() {
        return discountId;
    }

    public int getDiscountRate() {
        return discountRate;
    }
}
