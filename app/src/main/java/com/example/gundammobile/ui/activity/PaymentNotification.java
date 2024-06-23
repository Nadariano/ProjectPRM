package com.example.gundammobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gundammobile.R;

public class PaymentNotification extends AppCompatActivity {

    TextView txtPayNoti;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_notification);

        txtPayNoti = findViewById(R.id.txtPayNoti);

        intent = getIntent();

        txtPayNoti.setText(intent.getStringExtra("result"));
    }
}