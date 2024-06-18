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

import com.example.gundammobile.databinding.FragmentLoggedInUserBinding;
import com.example.gundammobile.ui.activity.ShoppingCartActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoggedInUser extends Fragment {
    private FragmentLoggedInUserBinding binding;
    private Button viewCartBtn, viewOrderHistoryBtn;
    private FloatingActionButton logoutBtn;
    private Intent intent;

    private UserViewModel userViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(requireParentFragment()).get(UserViewModel.class);

        binding = FragmentLoggedInUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Initialize buttons
        viewCartBtn = binding.viewCartBtn;
        viewOrderHistoryBtn = binding.viewOrderHistoryBtn;
        logoutBtn = binding.logoutBtn;

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
            userViewModel.setIsLoggedIn(false);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}