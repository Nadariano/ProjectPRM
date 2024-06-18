package com.example.gundammobile.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;

import com.example.gundammobile.databinding.FragmentUserBinding;
import com.example.gundammobile.ui.activity.AboutusActivity;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private TextView aboutusNav;
    FragmentContainerView fragment_user_container;

    Intent intent;

    private UserViewModel userViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        aboutusNav=binding.aboutusNav;
        aboutusNav.setOnClickListener(v -> {
            intent = new Intent(getActivity(), AboutusActivity.class);
            startActivity(intent);
        });

        fragment_user_container=binding.fragmentUserContainer;

        userViewModel.getIsLoggedIn().observe(getViewLifecycleOwner(), isLoggedIn -> {
            Fragment selectedFragment;
            if (isLoggedIn) {
                selectedFragment = new LoggedInUser(); // If logged in, show Fragment A
            } else {
                selectedFragment = new Guest(); // If not logged in, show Fragment B
            }
            // Perform the fragment transaction to replace the container with the selected fragment
            getChildFragmentManager().beginTransaction()
                    .replace(fragment_user_container.getId(), selectedFragment)
                    .commit();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}