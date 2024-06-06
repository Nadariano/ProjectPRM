package com.example.gundammobile.home;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.home.adapters.CarouselAdapter;
import com.example.gundammobile.home.adapters.CouponsAdapter;
import com.example.gundammobile.home.adapters.ProductsAdapter;
import com.example.gundammobile.home.models.Coupon;
import com.example.gundammobile.home.models.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize RecyclerViews
        RecyclerView rvCarousel = findViewById(R.id.rvCarousel);
        RecyclerView rvCoupons = findViewById(R.id.rvCoupons);
        RecyclerView rvProductList = findViewById(R.id.rvProductList);

    // Set up Carousel RecyclerView
        List<String> carouselImageUrls = new ArrayList<>();
        carouselImageUrls.add("https://laz-img-sg.alicdn.com/p/00e56a6b2bab0577ae915215ad03105f.jpg");
        carouselImageUrls.add("https://tabmohinh.com/wp-content/uploads/2020/06/MSN-04II-Nightingale.jpg");
    // Add more image URLs as needed

        CarouselAdapter carouselAdapter = new CarouselAdapter(this, carouselImageUrls);
        rvCarousel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCarousel.setAdapter(carouselAdapter);
        // Set up Coupons RecyclerView
        List<Coupon> coupons = new ArrayList<>(); // Placeholder coupons
        CouponsAdapter couponsAdapter = new CouponsAdapter(coupons);
        rvCoupons.setLayoutManager(new LinearLayoutManager(this));
        rvCoupons.setAdapter(couponsAdapter);

        // Set up Product List RecyclerView
        List<Product> products = new ArrayList<>(); // Placeholder products
        ProductsAdapter productsAdapter = new ProductsAdapter(products, new ProductsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Handle click on product item
            }
        });
        rvProductList.setLayoutManager(new LinearLayoutManager(this));
        rvProductList.setAdapter(productsAdapter);
    }
}
