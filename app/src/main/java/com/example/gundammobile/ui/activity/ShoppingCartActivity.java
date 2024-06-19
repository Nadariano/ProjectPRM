package com.example.gundammobile.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.model.CartItem;
import com.example.gundammobile.model.Coupon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartAdapter.OnItemRemoveListener {

    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_ITEMS = "cart_items";
    private RecyclerView cartRecyclerView;
    private ShoppingCartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private TextView txtTotalTemp;
    private TextView txtTotal;
    private TextView txtDeduction;
    private EditText edtDiscountCode;
    private double discount = 0;

    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        cartRecyclerView = findViewById(R.id.recyclerViewCart);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        txtTotalTemp = findViewById(R.id.txtTotal_temp);
        txtTotal = findViewById(R.id.txtTotal);
        txtDeduction = findViewById(R.id.txtDeduction);
        edtDiscountCode = findViewById(R.id.edtDiscountCode);

        // Initialize Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadCartItems();
        updateTotalPrice();

        cartAdapter = new ShoppingCartAdapter(this, cartItems, this, this::updateTotalPrice);
        cartRecyclerView.setAdapter(cartAdapter);

        Button btnDeleteAll = findViewById(R.id.btnDelete);
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllItems();
            }
        });

        EditText edtDiscountCode = findViewById(R.id.edtDiscountCode);
        edtDiscountCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String discountCode = edtDiscountCode.getText().toString();
                if (!discountCode.isEmpty()) {
                    fetchAndApplyDiscount(discountCode);
                } else {
                    Toast.makeText(ShoppingCartActivity.this, "Please enter a discount code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchAndApplyDiscount(String discountCode) {
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<Coupon>> call = jsonPlaceholder.getCoupons();

        call.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Coupon> coupons = response.body();
                    boolean couponApplied = false;
                    for (Coupon coupon : coupons) {
                        if (coupon.getDISCOUNTRATE() != 0 && coupon.getDISCOUNTID().equals(discountCode)) {
                            discount = coupon.getDISCOUNTRATE();
                            updateTotalPrice();
                            txtDeduction.setText(String.format("%.2f%%", discount)); // Cập nhật hiển thị phần trăm giảm giá
                            Toast.makeText(ShoppingCartActivity.this, "Discount applied", Toast.LENGTH_SHORT).show();
                            couponApplied = true;
                            break;
                        }
                    }
                    if (!couponApplied) {
                        Toast.makeText(ShoppingCartActivity.this, "Invalid discount code", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShoppingCartActivity.this, "Failed to retrieve discounts", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                Toast.makeText(ShoppingCartActivity.this, "Failed to apply discount", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CART_ITEMS, null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        cartItems = gson.fromJson(json, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
            Toast.makeText(this, "No items in cart", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteAllItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_ITEMS);
        editor.apply();

        cartItems.clear();
        cartAdapter.notifyDataSetChanged();
        updateTotalPrice();

        Toast.makeText(this, "All items removed from cart", Toast.LENGTH_SHORT).show();
    }

    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        txtTotalTemp.setText(total + "$");

        double discountAmount = total * (discount / 100.0);
        double totalAfterDiscount = total - discountAmount;

        txtDeduction.setText(String.format("%.2f%%", discount)); // Hiển thị phần trăm giảm giá
        txtTotal.setText(totalAfterDiscount + "$");
    }



    @Override
    public void onItemRemove(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
            cartAdapter.notifyItemRemoved(position);
            saveCartItems();
            updateTotalPrice();
            Toast.makeText(this, "Item removed from cart", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(CART_ITEMS, json);
        editor.apply();
    }
}
