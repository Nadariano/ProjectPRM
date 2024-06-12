package com.example.gundammobile.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.model.Product;
import com.example.gundammobile.ui.fragment.home.adapters.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";
    private SearchView searchView;
    private RecyclerView productList;
    private ProductsAdapter productsAdapter;
    private List<Product> allProducts = new ArrayList<>();
    private List<Product> filteredProducts = new ArrayList<>();
    private TextView noResultsText;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        searchView = findViewById(R.id.search_view);
        productList = findViewById(R.id.product_list);
        noResultsText = findViewById(R.id.no_results_text);
        drawerLayout = findViewById(R.id.drawer_layout);


        productList.setLayoutManager(new GridLayoutManager(this, 2));
        productsAdapter = new ProductsAdapter(filteredProducts, product -> {
            // Handle item click (e.g., navigate to detail activity)
        });
        productList.setAdapter(productsAdapter);
        fetchAllProducts();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });
    }

    private void fetchAllProducts() {
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
                    allProducts = response.body();
                    filteredProducts.addAll(allProducts);
                    productsAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SearchActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterProducts(String query) {
        filteredProducts.clear();
        if (query.isEmpty()) {
            filteredProducts.addAll(allProducts);
        } else {
            for (Product product : allProducts) {
                if (product.getPRODUCTNAME().toLowerCase().contains(query.toLowerCase())) {
                    filteredProducts.add(product);
                }
            }
        }
        productsAdapter.notifyDataSetChanged();

        if (filteredProducts.isEmpty()) {
            // Show the "No results" TextView
            noResultsText.setVisibility(View.VISIBLE);
        } else {
            // Hide the "No results" TextView
            noResultsText.setVisibility(View.GONE);
        }
    }
}
