package com.example.gundammobile.ui.fragment.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.adapter.activity.OrderDetailsAdapter;
import com.example.gundammobile.adapter.activity.OrderInfoAdapter;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.databinding.FragmentDashboardBinding;
import com.example.gundammobile.model.Order;
import com.example.gundammobile.model.OrderDetails;
import com.example.gundammobile.model.User;
import com.example.gundammobile.ui.activity.BillingActivity;
import com.example.gundammobile.ui.activity.ShoppingCartAdapter;
import com.example.gundammobile.ui.fragment.user.UserViewModel;
import com.example.gundammobile.utils.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {
    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";
    private FrameLayout loadingContainer;
    private UserViewModel userViewModel;
    private User user;
    private ArrayList<Order> itemList;
    private OrderInfoAdapter orderInfoAdapter;
    private RecyclerView orderList;
    private ScrollView orderListLayout;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        View root = binding.getRoot();

        orderListLayout = binding.orderListLayout;
        orderList = binding.orderList;
        loadingContainer = root.findViewById(R.id.loadingContainer);
        showLoading(true);
        getOrdersByAccountId();
        return root;

    }

    private void showLoading(boolean show) {
        loadingContainer.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void getOrdersByAccountId() {
        try{
            // Create a Retrofit instance
            user = userViewModel.getUser().getValue();

            int accountId = Objects.requireNonNull(user).getACCOUNT_ID();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateDeserializer())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            // Create an instance of the JSONPlaceholder interface
            JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);

            Call<ArrayList<Order>> call = jsonPlaceholder.getOrdersByAccountId(accountId);
            call.enqueue(new Callback<ArrayList<Order>>() {
                @Override
                public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                    showLoading(false);
                    if (response.isSuccessful() && response.body() != null) {
                        ArrayList<Order> orders = response.body();
                        itemList = orders;
                        orderInfoAdapter = new OrderInfoAdapter(itemList);
                        orderList.setAdapter(orderInfoAdapter);
                        orderList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
                        orderListLayout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(binding.getRoot().getContext(), "Failed to fetch order details", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Order>> call, Throwable t) {
                    showLoading(false);
                    Toast.makeText(binding.getRoot().getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        } catch (Exception e){
            Toast.makeText(binding.getRoot().getContext(), "Bạn chưa đăng nhập: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}