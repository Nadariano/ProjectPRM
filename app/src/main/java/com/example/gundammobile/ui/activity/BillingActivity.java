package com.example.gundammobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.adapter.activity.OrderDetailsAdapter;
import com.example.gundammobile.model.OrderDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BillingActivity extends AppCompatActivity {
    String orderId;
    ArrayList<OrderDetails> itemList;
    RecyclerView billItemList;

    FloatingActionButton backBtn;

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

        billItemList = findViewById(R.id.billItemList);

        itemList = new ArrayList<>();
        itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));
        itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));
        itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));
        itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));

        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(itemList);

        billItemList.setAdapter(orderDetailsAdapter);

        billItemList.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}