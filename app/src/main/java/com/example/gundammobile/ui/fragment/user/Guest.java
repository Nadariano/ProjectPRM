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

import com.example.gundammobile.databinding.FragmentGuestBinding;
import com.example.gundammobile.ui.activity.LoginActivity;
import com.example.gundammobile.ui.activity.RegisterActivity;

public class Guest extends Fragment {

    private FragmentGuestBinding binding;
    private Button loginBtn, signupBtn;
    private Intent intent;

    private UserViewModel userViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(requireParentFragment()).get(UserViewModel.class);

        binding = FragmentGuestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Initialize buttons
        loginBtn = binding.loginBtn;
        signupBtn = binding.signupBtn;

        // Set click listeners for each button
        loginBtn.setOnClickListener(v -> {
            // Handle login button click
            intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
//            userViewModel.setIsLoggedIn(true);
        });

        signupBtn.setOnClickListener(v -> {
            // Handle signup button click
            intent = new Intent(getActivity(), RegisterActivity.class);
            startActivity(intent);
//            userViewModel.setIsLoggedIn(true);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}