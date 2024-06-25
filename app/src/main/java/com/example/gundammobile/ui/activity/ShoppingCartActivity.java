// ShoppingCartActivity.java
package com.example.gundammobile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.Api.CreateOrder;
import com.example.gundammobile.R;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.model.CartItem;
import com.example.gundammobile.model.Coupon;
import com.example.gundammobile.ui.fragment.user.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;
//import vn.zalopay.sdk.listeners.PayOrderListener;

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


    private String totalStr;
    private UserViewModel userViewModel;
    private Intent intent;

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

        //ZaloPay
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(553, Environment.SANDBOX);
        Button btnPaid = findViewById(R.id.btnPaid);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        userViewModel.getIsLoggedIn().observe(this, isLoggedIn -> {
//            if(isLoggedIn){
//                intent =new Intent(this, BillingActivity.class);
//            } else{
//                intent = new Intent(this, LoginActivity.class);
//            }
//            this.startActivity(intent);
//        });
        btnPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPaymentBottomSheet();
                totalStr = (txtTotal.getText().toString()+"000").replace("$","");
                Log.d("MyApp", "Total String: " + totalStr);
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(totalStr);
//                    JSONObject data = orderApi.createOrder("300000");
                    String code = data.getString("returncode");
//                        Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();

                    if (code.equals("1")) {
                        String token = data.getString("zptranstoken");
                        ZaloPaySDK.getInstance().payOrder(ShoppingCartActivity.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                Intent intent1 = new Intent(ShoppingCartActivity.this,PaymentNotification.class);
                                intent1.putExtra("result","Thanh toán thành công");
                                startActivity(intent1);
                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                Intent intent1 = new Intent(ShoppingCartActivity.this,PaymentNotification.class);
                                intent1.putExtra("result","Hủy thanh toán");
                                startActivity(intent1);
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s2) {
                                Intent intent1 = new Intent(ShoppingCartActivity.this,PaymentNotification.class);
                                intent1.putExtra("result","Thanh toán thất bại, có lỗi xảy ra");
                                startActivity(intent1);
                            }
                        });

                    }

                } catch (Error | Exception e) {
//                    e.printStackTrace();
                    Toast.makeText(ShoppingCartActivity.this, "Lỗi: ", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void showPaymentBottomSheet() {
        double totalAmount = getTotalAmount(); // Method to get the total amount
        PaymentBottomSheet bottomSheet = new PaymentBottomSheet(this, totalAmount);
        bottomSheet.show();
    }

    private double getTotalAmount() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total - (total * (discount / 100.0));
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
                }
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                Toast.makeText(ShoppingCartActivity.this, "Failed to apply discount", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CART_ITEMS, null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        cartItems = gson.fromJson(json, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
    }

    private void saveCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(CART_ITEMS, json);
        editor.apply();
    }

    @Override
    public void onItemRemove(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
            cartAdapter.notifyItemRemoved(position);
            saveCartItems();
            Toast.makeText(this, "Item removed from cart", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        txtTotalTemp.setText(String.format("$%.0f", total));
        txtTotal.setText(String.format("$%.0f", total - (total * (discount / 100.0))));
    }

    private void deleteAllItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_ITEMS);
        editor.apply();

        cartItems.clear();
        cartAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

}
