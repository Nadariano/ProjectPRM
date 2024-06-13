package com.example.gundammobile.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.gundammobile.databinding.FragmentNotificationsBinding;
import com.example.gundammobile.databinding.FragmentUserBinding;
import com.example.gundammobile.ui.activity.AboutusActivity;
import com.example.gundammobile.ui.activity.LoginActivity;
import com.example.gundammobile.ui.activity.RegisterActivity;
import com.example.gundammobile.ui.activity.ShoppingCartActivity;
import com.example.gundammobile.ui.fragment.user.UserViewModel;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private Button loginBtn,signupBtn,viewCartBtn,viewOrderHistoryBtn;
    private TextView aboutusNav;
    private Intent intent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Initialize buttons
        loginBtn = binding.loginBtn;
        signupBtn = binding.signupBtn;
        viewCartBtn = binding.viewCartBtn;
        viewOrderHistoryBtn = binding.viewOrderHistoryBtn;
        aboutusNav=binding.aboutusNav;

        // Set click listeners for each button
        loginBtn.setOnClickListener(v -> {
            // Handle login button click
            intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        signupBtn.setOnClickListener(v -> {
            // Handle signup button click
            intent = new Intent(getActivity(), RegisterActivity.class);
            startActivity(intent);
        });

        viewCartBtn.setOnClickListener(v -> {
            // Handle view cart button click
            intent = new Intent(getActivity(), ShoppingCartActivity.class);
            startActivity(intent);
        });

        viewOrderHistoryBtn.setOnClickListener(v -> {
            // Handle view order history button click
        });

        aboutusNav.setOnClickListener(v -> {
            // Handle about us button click
            intent = new Intent(getActivity(), AboutusActivity.class);
            startActivity(intent);
        });

        userViewModel.shouldShowFirstCard().observe(getViewLifecycleOwner(), shouldShow -> {
            if (shouldShow) {
                binding.guestCard.setVisibility(View.VISIBLE);
                binding.userCard.setVisibility(View.GONE);
            } else {
                binding.guestCard.setVisibility(View.GONE);
                binding.userCard.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}