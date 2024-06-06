package com.example.gundammobile.home.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.home.models.Coupon;

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
        private TextView tvCouponTitle;

        CouponViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCouponTitle = itemView.findViewById(R.id.tvCouponTitle);
        }

        void bind(Coupon coupon) {
            tvCouponTitle.setText("Discount: " + coupon.getDiscountRate() + "%");
        }
    }
}
