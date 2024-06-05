package com.example.gundammobile.model;

public class CartItem {
    private String productImage;
    private String productName;
    private int quantity;
    private float unitPrice;

    public CartItem(String productImage,String productName, int quantity, float unitPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public String getProductImage() {
        return productImage;
    }
    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }
}
