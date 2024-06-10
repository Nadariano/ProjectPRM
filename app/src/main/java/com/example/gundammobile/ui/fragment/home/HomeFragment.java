package com.example.gundammobile.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.ui.activity.ItemDetailActivity;
import com.example.gundammobile.ui.fragment.home.adapters.CarouselAdapter;
import com.example.gundammobile.ui.fragment.home.adapters.CouponsAdapter;
import com.example.gundammobile.ui.fragment.home.adapters.ProductsAdapter;
import com.example.gundammobile.model.Product;
import com.example.gundammobile.model.Coupon;
import com.google.android.material.carousel.CarouselLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class HomeFragment extends Fragment {

    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rvCarousel = root.findViewById(R.id.rvCarousel);
        RecyclerView rvCoupons = root.findViewById(R.id.rvCoupons);
        RecyclerView rvProductList = root.findViewById(R.id.rvProductList);

        fetchProducts(rvCarousel, rvProductList);
        fetchCoupons(rvCoupons);

        return root;
    }

    private void fetchProducts(RecyclerView rvCarousel, RecyclerView rvProductList) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<Product>> call = jsonPlaceholder.getProductList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();

                    // Update CarouselAdapter with the fetched product list
                    CarouselAdapter carouselAdapter = new CarouselAdapter(requireContext(), products, product -> {
                        // Handle click on carousel item (e.g., show product details)
                        Intent detail = new Intent(getContext(), ItemDetailActivity.class);
                        detail.putExtra("PRODUCT_ID", product.getPRODUCT_ID());
                        startActivity(detail);
                    });

                    CarouselLayoutManager layoutManager = new CarouselLayoutManager();
                    rvCarousel.setLayoutManager(layoutManager);
                    rvCarousel.setAdapter(carouselAdapter);

                    // Update ProductsAdapter with the fetched product list
                    ProductsAdapter productsAdapter = new ProductsAdapter(products, product -> {
                        // Handle click on product item
                        Intent detail = new Intent(getContext(), ItemDetailActivity.class);
                        detail.putExtra("PRODUCT_ID", product.getPRODUCT_ID());
                        startActivity(detail);
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
                    rvProductList.setLayoutManager(gridLayoutManager);
                    rvProductList.setAdapter(productsAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to fetch products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchCoupons(RecyclerView rvCoupons) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<Coupon>> call = jsonPlaceholder.getCoupons();
        call.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Coupon> coupons = response.body();

                    // Update CouponsAdapter with the fetched coupon list
                    CouponsAdapter couponsAdapter = new CouponsAdapter(coupons);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                    rvCoupons.setLayoutManager(layoutManager);
                    rvCoupons.setAdapter(couponsAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to fetch coupons", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}