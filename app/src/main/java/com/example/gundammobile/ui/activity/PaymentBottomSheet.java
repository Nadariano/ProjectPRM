// PaymentBottomSheet.java
package com.example.gundammobile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.gundammobile.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PaymentBottomSheet extends BottomSheetDialog {

    private TextView txtTotalBottomSheet;
    private RadioGroup radioGroupPayment;
    private Button btnConfirmPayment;
    private ImageButton btnClose;

    public PaymentBottomSheet(@NonNull Context context, double totalAmount) {
        super(context);
        setContentView(R.layout.bottom_sheet_payment);
        txtTotalBottomSheet = findViewById(R.id.txtTotalBottomSheet);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);
        btnClose = findViewById(R.id.btnClose);

//        txtTotalBottomSheet.setText("Total: $" + totalAmount);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupPayment.getCheckedRadioButtonId();
                Intent intent;
                if (selectedId == R.id.radioDirectPayment) {
                    intent = new Intent(context, MainActivity.class);
                    //physical nha
                } else {
                    intent = new Intent(context, MainActivity.class);
                    //online vnpay nha
                }
                context.startActivity(intent);
                dismiss();
            }
        });
    }
}
