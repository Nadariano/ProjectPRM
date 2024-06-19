package com.example.gundammobile.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.model.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartAdapter.OnItemRemoveListener {

    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_ITEMS = "cart_items";
    private RecyclerView cartRecyclerView;
    private ShoppingCartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private TextView txtTotalTemp;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        cartRecyclerView = findViewById(R.id.recyclerViewCart);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        txtTotalTemp = findViewById(R.id.txtTotal_temp);
        txtTotal = findViewById(R.id.txtTotal);

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
        txtTotal.setText(total + "$"); // Update this if there are additional deductions or calculations
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
