package com.example.gundammobile.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gundammobile.databinding.FragmentLoggedInUserBinding;
import com.example.gundammobile.model.User;
import com.example.gundammobile.ui.activity.ShoppingCartActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class LoggedInUser extends Fragment {
    private FragmentLoggedInUserBinding binding;
    private TextView userName;
    private Button viewCartBtn, viewOrderHistoryBtn;
    private FloatingActionButton logoutBtn;
    private Intent intent;

    private User user;
    private UserViewModel userViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(requireParentFragment()).get(UserViewModel.class);

        binding = FragmentLoggedInUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        userName=binding.userName;
        // Initialize buttons
        viewCartBtn = binding.viewCartBtn;
        viewOrderHistoryBtn = binding.viewOrderHistoryBtn;
        logoutBtn = binding.logoutBtn;

        userName.setText(Objects.requireNonNull(userViewModel.getUser().getValue()).getACCOUNT_NAME());
        viewCartBtn.setOnClickListener(v -> {
            // Handle view cart button click
            intent = new Intent(getActivity(), ShoppingCartActivity.class);
            startActivity(intent);
        });

        viewOrderHistoryBtn.setOnClickListener(v -> {
            // Handle view order history button click
        });

        logoutBtn.setOnClickListener(v -> {
            // Handle logout button click
            userViewModel.logOut();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}