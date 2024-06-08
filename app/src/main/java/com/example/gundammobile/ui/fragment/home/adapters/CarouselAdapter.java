package com.example.gundammobile.ui.fragment.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.ui.fragment.home.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private Context mContext;
    private List<Product> mProducts;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public CarouselAdapter(Context context, List<Product> products, OnItemClickListener listener) {
        mContext = context;
        mProducts = products;
        mListener = listener;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.carousel_item_layout, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.bind(product, mListener);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class CarouselViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carouselImageView;
        OnItemClickListener listener;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            carouselImageView = itemView.findViewById(R.id.carousel_image_view);
            itemView.setOnClickListener(this);
        }

        void bind(Product product, OnItemClickListener listener) {
            // Load product image using Picasso
            Picasso.get().load(product.getProductImage()).into(carouselImageView);

            // Set click listener for the carousel item
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Product product = mProducts.get(position);
                listener.onItemClick(product);
            }
        }
    }
}
