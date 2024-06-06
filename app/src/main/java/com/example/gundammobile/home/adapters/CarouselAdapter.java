package com.example.gundammobile.home.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private Context mContext;
    private List<String> mImageUrls;

    public CarouselAdapter(Context context, List<String> imageUrls) {
        mContext = context;
        mImageUrls = imageUrls;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.carousel_item_layout, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        String imageUrl = mImageUrls.get(position);
        Picasso.get().load(imageUrl).into(holder.carouselImageView);
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView carouselImageView;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            carouselImageView = itemView.findViewById(R.id.carousel_image_view);
        }
    }
}
