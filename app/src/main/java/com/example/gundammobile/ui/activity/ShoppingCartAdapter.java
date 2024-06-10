package com.example.gundammobile.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.model.CartItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    public ShoppingCartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_list, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.productName.setText(cartItem.getProductName());
        holder.productPrice.setText(String.valueOf(cartItem.getPrice()) + "$");
        holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));
        Picasso.get().load(cartItem.getProductImage()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        ImageView productImage;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.unitPrice);
            productQuantity = itemView.findViewById(R.id.quantity);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }
}
