package com.example.gundammobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.model.CartItem;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private List<CartItem> cartItems;

    public ShoppingCartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName;
        public TextView quantity;
        public TextView unitPrice;

        public ViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.productImage);
            productName = view.findViewById(R.id.productName);
            quantity = view.findViewById(R.id.quantity);
            unitPrice = view.findViewById(R.id.unitPrice);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.productName.setText(item.getProductName());
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        holder.unitPrice.setText(String.valueOf(item.getUnitPrice()));
        // Assuming you have a method to set the product image
        // holder.productImage.setImageResource(item.getProductImageResId());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
