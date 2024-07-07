package com.example.gundammobile.context;

import com.example.gundammobile.model.CartItem;
import com.example.gundammobile.model.Coupon;
import com.example.gundammobile.model.Order;
import com.example.gundammobile.model.OrderDetails;
import com.example.gundammobile.model.OrderRequest;
import com.example.gundammobile.model.OrderResponse;
import com.example.gundammobile.model.Product;
import com.example.gundammobile.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSONPlaceholder {
    @GET("products/detail")
    Call<Product> getProductDetail(@Query("productId") String productId);
    @GET("products")
    Call<List<Product>> getProductList();
    @GET("products/random")
    Call<List<Product>> getRandomProductList();
    @GET("discounts")
    Call<List<Coupon>> getCoupons();
    @POST("users/register")
    Call<User> registerUser(@Body User user);
    @POST("users/login")
    Call<User> loginUser(@Body User user);
    @POST("orders")
    Call<OrderResponse> createOrder(@Body OrderRequest orderRequest);
    @GET("orders/info")
    Call<Order> getOrderInfo(@Query("orderId") String orderId);
    @GET("orders/details")
    Call<ArrayList<OrderDetails>> getOrderDetails(@Query("orderId") String orderId);
    @GET("orders")
    Call<ArrayList<Order>> getOrdersByAccountId(@Query("accountId ") Integer accountId);

}

