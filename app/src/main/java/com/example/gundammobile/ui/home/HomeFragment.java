package com.example.gundammobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.databinding.FragmentHomeBinding;
import com.example.gundammobile.home.adapters.CarouselAdapter;
import com.example.gundammobile.home.adapters.CouponsAdapter;
import com.example.gundammobile.home.adapters.ProductsAdapter;
import com.example.gundammobile.home.models.Coupon;
import com.example.gundammobile.home.models.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize ViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Find RecyclerViews and other views using view binding
        RecyclerView rvCarousel = binding.rvCarousel;
        RecyclerView rvCoupons = binding.rvCoupons;
        RecyclerView rvProductList = binding.rvProductList;

        // Set up RecyclerView adapters, layout managers, etc.
        // For rvCarousel, you need to set up a PagerAdapter if you're using ViewPager.
        // Example:
        List<String> carouselImageUrls = new ArrayList<>();
        carouselImageUrls.add("https://laz-img-sg.alicdn.com/p/00e56a6b2bab0577ae915215ad03105f.jpg");
        carouselImageUrls.add("https://tabmohinh.com/wp-content/uploads/2020/06/MSN-04II-Nightingale.jpg");
        CarouselAdapter carouselAdapter = new CarouselAdapter(getContext(), carouselImageUrls);
        rvCarousel.setAdapter(carouselAdapter);

        // For rvCoupons and rvProductList, set up their adapters, layout managers, etc. as needed.
        // Example:
//        List<Coupon> coupons = new ArrayList<>(); // Placeholder coupons
//        CouponsAdapter couponsAdapter = new CouponsAdapter(coupons);
//        rvCoupons.setAdapter(couponsAdapter);
//        LinearLayoutManager couponsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        rvCoupons.setLayoutManager(couponsLayoutManager);
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("Coupon 1", 22));
        coupons.add(new Coupon("Coupon 2", 45));
// Add more coupons as needed

// Set up Coupons RecyclerView
        CouponsAdapter couponsAdapter = new CouponsAdapter(coupons);
        rvCoupons.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCoupons.setAdapter(couponsAdapter);



//
//        List<Product> products = new ArrayList<>(); // Placeholder products
//        ProductsAdapter productsAdapter = new ProductsAdapter(products, product -> {
//            // Handle click on product item
//        });
//        rvProductList.setAdapter(productsAdapter);
//        LinearLayoutManager productLayoutManager = new LinearLayoutManager(getContext());
//        rvProductList.setLayoutManager(productLayoutManager);
// Create some dummy products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "https://example.com/product1.jpg", 10.99));
        products.add(new Product("Product 2",  "https://example.com/product2.jpg", 19.99));
// Add more products as needed

// Set up Product List RecyclerView
        ProductsAdapter productsAdapter = new ProductsAdapter(products, product -> {
            // Handle click on product item
        });
        rvProductList.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvProductList.setAdapter(productsAdapter);

        // Observe LiveData in ViewModel
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
