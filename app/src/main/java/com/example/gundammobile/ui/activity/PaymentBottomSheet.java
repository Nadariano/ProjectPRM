// PaymentBottomSheet.java
package com.example.gundammobile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.gundammobile.Api.CreateOrder;
import com.example.gundammobile.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONObject;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;

public class PaymentBottomSheet extends BottomSheetDialog {

    private TextView txtTotalBottomSheet;
    private RadioGroup radioGroupPayment;
    private Button btnConfirmPayment;
    private ImageButton btnClose;

    private double total;
    private String totalString;

    public PaymentBottomSheet(@NonNull Context context, double totalAmount) {
        super(context);
        setContentView(R.layout.bottom_sheet_payment);
        txtTotalBottomSheet = findViewById(R.id.txtTotalBottomSheet);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);
        btnClose = findViewById(R.id.btnClose);

//        txtTotalBottomSheet.setText("Total: $" + totalAmount);

//        //ZaloPay
//        StrictMode.ThreadPolicy policy = new
//                StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        // ZaloPay SDK Init
//        ZaloPaySDK.init(553, Environment.SANDBOX);
        total = 100000;
        totalString = String.format("%.0f", total);

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
//                    intent = new Intent(context, MainActivity.class);
                    //physical nha
                } else {
//                    intent = new Intent(context, MainActivity.class);
                    //online vnpay nha
//                    CreateOrder orderApi = new CreateOrder();
//                    try {
//                        JSONObject data = orderApi.createOrder(totalString);
//                        String code = data.getString("returncode");
////                        Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();
//
//                        if (code.equals("1")) {
//                            String token = data.getString("zptranstoken");
//                            ZaloPaySDK.getInstance().payOrder(PaymentBottomSheet.this.getOwnerActivity(), token, "demozpdk://app", new PayOrderListener() {
//                                @Override
//                                public void onPaymentSucceeded(String s, String s1, String s2) {
//                                    Intent intent1 = new Intent(PaymentBottomSheet.this.getOwnerActivity(),PaymentNotification.class);
//                                    intent1.putExtra("result","Thanh toán thành công");
//                                    context.startActivity(intent1);
//                                }
//
//                                @Override
//                                public void onPaymentCanceled(String s, String s1) {
//                                    Intent intent1 = new Intent(PaymentBottomSheet.this.getOwnerActivity(),PaymentNotification.class);
//                                    intent1.putExtra("result","Hủy thanh toán");
//                                    context.startActivity(intent1);
//                                }
//
//                                @Override
//                                public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
//                                    Intent intent1 = new Intent(PaymentBottomSheet.this.getOwnerActivity(),PaymentNotification.class);
//                                    intent1.putExtra("result","Thanh toán thất bại, có lỗi xảy ra");
//                                    context.startActivity(intent1);
//                                }
//                            });
//
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                }
//                context.startActivity(intent);
                dismiss();
            }
        });

    }

}
