package com.example.gundammobile.adapter.activity;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.model.OrderDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
    private ArrayList<OrderDetails> itemList;

    public OrderDetailsAdapter(ArrayList<OrderDetails> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetails item = itemList.get(position);
        holder.productImage.setImageURI(Uri.parse(item.getPRODUCTIMAGE()));
        Picasso.get().load(item.getPRODUCTIMAGE()).into(holder.productImage);
        holder.productName.setText(item.getPRODUCTNAME());
        holder.quantity.setText(String.valueOf(item.getQUANTITY()));
        holder.unitPrice.setText(String.valueOf(item.getUNITPRICE()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, quantity, unitPrice;
        ImageView productImage;

        public ViewHolder(View itemview) {
            super(itemview);

            productImage = itemview.findViewById(R.id.productImage);
            productName = itemview.findViewById(R.id.productName);
            quantity = itemview.findViewById(R.id.quantity);
            unitPrice = itemview.findViewById(R.id.unitPrice);
        }
    }
}
