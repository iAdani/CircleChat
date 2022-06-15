package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.circlechat.api.LoginWebService;
import com.example.circlechat.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LoginWebService loginWebService = new LoginWebService(this);

        binding.loginbtn.setOnClickListener(v -> {
            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            if(username.isEmpty() || password.isEmpty()) {
                // if at least one of the fields is empty
                Toast.makeText(LoginActivity.this, "All fields are required :)", Toast.LENGTH_SHORT).show();
            }
            else{
                // if not empty, check the login with the server
                 loginWebService.login(username, password);
            }
        });

        binding.clickNotRegistered.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}