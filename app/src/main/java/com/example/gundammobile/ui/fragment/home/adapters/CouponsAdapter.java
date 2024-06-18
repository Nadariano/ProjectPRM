package com.example.gundammobile.ui.fragment.home.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.model.Coupon;

import java.util.List;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.CouponViewHolder> {

    private List<Coupon> coupons;

    public CouponsAdapter(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);
        return new CouponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        Coupon coupon = coupons.get(position);
        holder.bind(coupon);
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    static class CouponViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCouponImage;
        private TextView tvCouponTitle;
        private TextView tvDiscountRate;
        private Button btnCopyCoupon;

        CouponViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCouponImage = itemView.findViewById(R.id.ivCouponImage);
            tvCouponTitle = itemView.findViewById(R.id.tvCouponTitle);
            tvDiscountRate = itemView.findViewById(R.id.tvDiscountRate);
            btnCopyCoupon = itemView.findViewById(R.id.btnCopyCoupon);
        }

        void bind(Coupon coupon) {
            // Set dummy image (you can replace with actual image logic if available)
//            ivCouponImage.setImageResource(R.drawable.ic_coupon_image_placeholder);

            tvCouponTitle.setText("Coupon: " + coupon.getDISCOUNTID());
            tvDiscountRate.setText("Discount Rate: " + coupon.getDISCOUNTRATE() + "%");

            btnCopyCoupon.setOnClickListener(view -> {
                Context context = view.getContext();
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Coupon Code", coupon.getDISCOUNTID());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Coupon code copied to clipboard", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
