package com.example.gundammobile.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gundammobile.R;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.model.CartItem;
import com.example.gundammobile.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemDetailActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";
    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_ITEMS = "cart_items";

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String productId = getIntent().getStringExtra("PRODUCT_ID");
        fetchProductDetails(productId);

        ImageButton backButton = findViewById(R.id.productDetailBack);
        backButton.setOnClickListener(v -> {
            // This will close the current activity and go back to the previous one
            finish();
        });

        ImageButton addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(v -> addToCart());
    }

    private void fetchProductDetails(String productId) {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the JSONPlaceholder interface
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);

        // Make the API call
        Call<Product> call = jsonPlaceholder.getProductDetail(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product = response.body();
                    // Update your TextViews with product details here
                    ((TextView) findViewById(R.id.productDetailName)).setText(product.getPRODUCTNAME());
                    ((TextView) findViewById(R.id.productDetailBrand)).setText(product.getBRANDNAME());
                    ((TextView) findViewById(R.id.productDetailType)).setText(product.getTYPENAME());
                    ((TextView) findViewById(R.id.productDetailDesc)).setText(product.getPRODUCTDETAILS());
                    ((TextView) findViewById(R.id.productDetailPrice)).setText(String.valueOf(product.getPRICE()) + "$");
                    ((TextView) findViewById(R.id.productDetailQuantity)).setText(String.valueOf(product.getQUANTITY()));
                    ImageView productImageView = findViewById(R.id.productDetailImage);
                    Picasso.get()
                            .load(product.getPRODUCTIMAGE())
                            .into(productImageView);
                } else {
                    // Handle the case where the response is not successful
                    Toast.makeText(ItemDetailActivity.this, "Failed to fetch product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                // Handle the case where the API call fails
                Toast.makeText(ItemDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart() {
        if (product == null) {
            Toast.makeText(this, "Product details not loaded yet", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String json = sharedPreferences.getString(CART_ITEMS, null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        CartItem cartItem = new CartItem(product.getPRODUCTNAME(), product.getPRODUCTIMAGE(), product.getPRICE(), 1);
        cartItems.add(cartItem);

        json = gson.toJson(cartItems);
        editor.putString(CART_ITEMS, json);
        editor.apply();

        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
    }
}
