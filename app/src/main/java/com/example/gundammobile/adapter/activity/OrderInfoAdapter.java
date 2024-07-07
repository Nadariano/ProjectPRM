package com.example.gundammobile.adapter.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.model.Order;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderInfoAdapter extends RecyclerView.Adapter<OrderInfoAdapter.ViewHolder> {
    private ArrayList<Order> itemList;

    public OrderInfoAdapter(ArrayList<Order> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order item = itemList.get(position);
        holder.order_id.setText(String.valueOf(item.getORDER_ID()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = sdf.format(item.getORDER_DATE());
        holder.order_date.setText(formattedDate);
        holder.total_money.setText(String.valueOf(item.getTOTALMONEY()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_id, order_date, total_money;

        public ViewHolder(View itemview) {
            super(itemview);

            order_id = itemview.findViewById(R.id.order_id);
            order_date = itemview.findViewById(R.id.order_date);
            total_money = itemview.findViewById(R.id.total_money);
        }
    }

}
