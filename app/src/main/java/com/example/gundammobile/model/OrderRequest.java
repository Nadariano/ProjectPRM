package com.example.gundammobile.model;

import java.util.ArrayList;

public class OrderRequest {
    private Order order;
    private ArrayList<CartItem> cart;

    public OrderRequest(Order order, ArrayList<CartItem> cart) {
        this.order = order;
        this.cart = cart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public void setCart(ArrayList<CartItem> cart) {
        this.cart = cart;
    }
}
