package com.example.gundammobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gundammobile.R;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.model.Product;
import com.example.gundammobile.model.User;
import com.example.gundammobile.ui.fragment.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(etUsername, etPassword);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void attemptLogin(EditText etUsername, EditText etPassword) {
        // Get the input data from your form fields
        String email = etUsername.getText().toString();
        String accountPass = etPassword.getText().toString();

        // Validate the input data
        if (email.isEmpty() || accountPass.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the JSONPlaceholder interface
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        User newUser = new User(email, accountPass);
        Call<User> call = jsonPlaceholder.loginUser(newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this, HomeFragment.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Login failed: Username or password is incorrect", Toast.LENGTH_SHORT).show();
//                }
                if (response.isSuccessful()){
                    user = response.body();
                    if (user != null){
                        String email = user.getEMAIL();
                        Toast.makeText(LoginActivity.this, "Login " + email,Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}