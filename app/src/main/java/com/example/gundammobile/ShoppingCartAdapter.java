package com.example.gundammobile;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.model.CartItem;
import com.squareup.picasso.Picasso;

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
        Picasso.get().load(item.getProductImage()).into(holder.productImage);
        holder.productName.setText(item.getProductName());
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        holder.unitPrice.setText(String.valueOf(item.getUnitPrice()));
    }


    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
