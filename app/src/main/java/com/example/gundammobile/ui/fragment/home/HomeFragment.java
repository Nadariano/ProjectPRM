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
import com.example.gundammobile.ui.activity.ItemDetailActivity;
import com.example.gundammobile.ui.fragment.home.adapters.CarouselAdapter;
import com.example.gundammobile.ui.fragment.home.adapters.CouponsAdapter;
import com.example.gundammobile.ui.fragment.home.adapters.ProductsAdapter;
import com.example.gundammobile.ui.fragment.home.models.Product;
import com.example.gundammobile.model.Coupon;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerViews
        RecyclerView rvCarousel = root.findViewById(R.id.rvCarousel);
        RecyclerView rvCoupons = root.findViewById(R.id.rvCoupons);
        RecyclerView rvProductList = root.findViewById(R.id.rvProductList);

        // Set up Carousel RecyclerView with products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42", 10.99));
        products.add(new Product("Product 2", "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42", 19.99));
        products.add(new Product("Product 3", "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42", 19.99));
        CarouselAdapter carouselAdapter = new CarouselAdapter(requireContext(), products, new CarouselAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Handle click on carousel item (e.g., show product details)
                Intent detail = new Intent(getContext(), ItemDetailActivity.class);
                startActivity(detail);
                Toast.makeText(getContext(), "Clicked on: " + product.getProductName(), Toast.LENGTH_SHORT).show();
            }
        });
        rvCarousel.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCarousel.setAdapter(carouselAdapter);

        // Set up Coupons RecyclerView with dummy data
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("Coupon 1", 22));
        coupons.add(new Coupon("Coupon 2", 45));
        coupons.add(new Coupon("Coupon 3", 45));
        coupons.add(new Coupon("Coupon 4", 45));
        coupons.add(new Coupon("Coupon 5", 45));
        coupons.add(new Coupon("Coupon 6", 45));
        coupons.add(new Coupon("Coupon 7", 45));
        CouponsAdapter couponsAdapter = new CouponsAdapter(coupons);
        rvCoupons.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCoupons.setAdapter(couponsAdapter);

        // Set up Product List RecyclerView with dummy data
        ProductsAdapter productsAdapter = new ProductsAdapter(products, product -> {
            // Handle click on product item
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        rvProductList.setLayoutManager(gridLayoutManager);
        rvProductList.setAdapter(productsAdapter);

        // Ensure nested scrolling is disabled
        rvProductList.setNestedScrollingEnabled(false);

        return root;
    }
}
