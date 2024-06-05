package com.example.gundammobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceholder {
    @GET("products/detail")
    Call<Product> getProductDetail(@Query("productId") String productId);
}

