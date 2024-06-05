package com.example.gundammobile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gundammobile.model.JSONPlaceholder;
import com.example.gundammobile.model.Product;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemDetailActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";

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

        fetchProductDetails("HG001"); //fetch by productId
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
                    Product product = response.body();
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
}