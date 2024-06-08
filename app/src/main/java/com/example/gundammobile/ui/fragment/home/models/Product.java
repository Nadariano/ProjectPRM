package com.example.gundammobile.ui.fragment.home.models;

public class Product {
    private String productId;
    private String productName;
    private String origin;
    private double price;
    private String productDetails;
    private String productMaterial;
    private int quantity;
    private String productImage;
    private String typeId;
    private String typeName;
    private String ratio;
    private String height;
    private int brandId; // Brand ID
    private String brandName; // Brand Name

    // Constructor
    public Product(String productId, String productName, String origin, double price, String productDetails, String productMaterial,
                   int quantity, String productImage, String typeId, String typeName, String ratio, String height, int brandId, String brandName) {
        this.productId = productId;
        this.productName = productName;
        this.origin = origin;
        this.price = price;
        this.productDetails = productDetails;
        this.productMaterial = productMaterial;
        this.quantity = quantity;
        this.productImage = productImage;
        this.typeId = typeId;
        this.typeName = typeName;
        this.ratio = ratio;
        this.height = height;
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public Product(String productName,String productImage, double price) {
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;

    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getOrigin() {
        return origin;
    }

    public double getPrice() {
        return price;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getRatio() {
        return ratio;
    }

    public String getHeight() {
        return height;
    }

    // Getters for Brand properties
    public int getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }
}
