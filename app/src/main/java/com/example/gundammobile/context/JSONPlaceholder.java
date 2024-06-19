package com.example.gundammobile.context;

import com.example.gundammobile.model.Coupon;
import com.example.gundammobile.model.Product;
import com.example.gundammobile.model.User;

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
}

