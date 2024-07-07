package com.example.gundammobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.adapter.activity.OrderDetailsAdapter;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.model.Order;
import com.example.gundammobile.model.OrderDetails;
import com.example.gundammobile.utils.DateDeserializer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BillingActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";
    private FrameLayout loadingContainer;
    private Order order;
    private OrderDetails orderDetails;
    private OrderDetailsAdapter orderDetailsAdapter;
    String orderId;
    ArrayList<OrderDetails> itemList;
    RecyclerView billItemList;

    FloatingActionButton backBtn;

    ScrollView orderDetailsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_billing);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        TextView orderIdTextView = findViewById(R.id.orderId);
        orderIdTextView.setText(orderId);

        orderDetailsLayout=findViewById(R.id.orderDetailsLayout);
        loadingContainer = findViewById(R.id.loadingContainer);

        billItemList = findViewById(R.id.billItemList);
        showLoading(true);
        fetchOrderInfo(orderId);

        itemList = new ArrayList<>();
        fetchOrderDetails(orderId);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showLoading(boolean show) {
        loadingContainer.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private void fetchOrderInfo(String productId) {
        // Create a Retrofit instance

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Create an instance of the JSONPlaceholder interface
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);

        Call<Order> call = jsonPlaceholder.getOrderInfo(orderId);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    Order order = response.body();
                    ((TextView) findViewById(R.id.orderId)).setText(String.valueOf(order.getORDER_ID()));
                    ((TextView) findViewById(R.id.accountName)).setText(order.getACCOUNT_NAME());

                    // Format the Date object to a String
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = sdf.format(order.getORDER_DATE());
                    ((TextView) findViewById(R.id.createdDate)).setText(formattedDate);
                    ((TextView) findViewById(R.id.totalMoney)).setText(String.valueOf(order.getTOTALMONEY()));

                    orderDetailsLayout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(BillingActivity.this, "Failed to fetch order details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                showLoading(false);
                Toast.makeText(BillingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void fetchOrderDetails(String productId) {
        // Create a Retrofit instance

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the JSONPlaceholder interface
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);

        Call<ArrayList<OrderDetails>> call = jsonPlaceholder.getOrderDetails(orderId);
        call.enqueue(new Callback<ArrayList<OrderDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderDetails>> call, Response<ArrayList<OrderDetails>> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<OrderDetails> orderDetails = response.body();
                    itemList = orderDetails;
                    orderDetailsAdapter = new OrderDetailsAdapter(itemList);
                    billItemList.setAdapter(orderDetailsAdapter);
                    billItemList.setLayoutManager(new LinearLayoutManager(BillingActivity.this));
                    orderDetailsLayout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(BillingActivity.this, "Failed to fetch order details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OrderDetails>> call, Throwable t) {
                showLoading(false);
                Toast.makeText(BillingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void setFormattedDate(String sqlDate) {
        // Define the input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            // Parse the SQL datetime string to a Date object
            Date date = (Date) inputFormat.parse(sqlDate);

            // Format the Date object to the desired format
            String formattedDate = outputFormat.format(date);

            // Set the formatted date to the TextView
            ((TextView) findViewById(R.id.createdDate)).setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}