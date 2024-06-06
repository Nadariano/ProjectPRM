package com.example.gundammobile.home.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.home.models.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> products;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public ProductsAdapter(List<Product> products, OnItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivProductImage;
        private TextView tvItemTitle;
        private TextView tvItemPrice;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            itemView.setOnClickListener(this);
        }

        void bind(Product product) {
            // Set product data to views
            // Example:
            // ivProductImage.setImageResource(product.getImageResource());
            tvItemTitle.setText(product.getProductName());
            tvItemPrice.setText(String.valueOf(product.getPrice()));
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Product product = products.get(position);
                listener.onItemClick(product);
            }
        }
    }
}
