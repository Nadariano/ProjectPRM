package com.example.gundammobile.ui.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.gundammobile.databinding.FragmentNotificationsBinding;
import com.example.gundammobile.databinding.FragmentUserBinding;
import com.example.gundammobile.ui.fragment.user.UserViewModel;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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