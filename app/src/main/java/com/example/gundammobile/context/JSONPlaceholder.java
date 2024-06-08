package com.example.gundammobile.context;

import com.example.gundammobile.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceholder {
    @GET("products/detail")
    Call<Product> getProductDetail(@Query("productId") String productId);
}

