package com.example.gundammobile.model;

public class CartItem {
    private String productName;
    private String productImage;
    private double price;
    private int quantity;

    public CartItem(String productName, String productImage, double price, int quantity) {
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
