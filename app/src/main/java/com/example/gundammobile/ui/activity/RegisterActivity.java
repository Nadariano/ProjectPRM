package com.example.gundammobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gundammobile.R;
import com.example.gundammobile.context.JSONPlaceholder;
import com.example.gundammobile.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://prm-be.vercel.app/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etFullName = findViewById(R.id.etFullName);
        EditText etAddress = findViewById(R.id.etAddress);
        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnSignUp = findViewById(R.id.btnSignUp);

        TextView tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount);
        tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister(etEmail, etFullName, etAddress, etPhoneNumber, etPassword, etConfirmPassword);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void attemptRegister(EditText etEmail, EditText etFullName, EditText etAddress, EditText etPhoneNumber, EditText etPassword, EditText etConfirmPassword) {
        // Get the input data from your form fields
        String email = etEmail.getText().toString();
        String accountName = etFullName.getText().toString();
        String accountAddress = etAddress.getText().toString();
        String accountPhone = etPhoneNumber.getText().toString();
        String accountPass = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        // Validate the input data
        if (email.isEmpty() || accountName.isEmpty() || accountAddress.isEmpty() || accountPhone.isEmpty() || accountPass.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!accountPass.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (accountPass.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the JSONPlaceholder interface
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);

        // Create a new User object with the input data
        User newUser = new User(email, accountPass, accountName, accountPhone, accountAddress);

        // Make the network request to register the user
        Call<User> call = jsonPlaceholder.registerUser(newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed: Username already existed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Registration error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}