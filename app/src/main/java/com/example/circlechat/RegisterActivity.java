package com.example.circlechat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.circlechat.api.RegisterWebService;
import com.example.circlechat.databinding.ActivityRegisterBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(RegisterActivity.this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
        });

        RegisterWebService registerWebService = new RegisterWebService(this);

        binding.signupbtn.setOnClickListener(v -> {
            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            String rePassword = binding.repeatpassword.getText().toString();

            // Checking if empty
            if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() ||
                binding.nickname.getText().toString().isEmpty()){
                Toast.makeText(RegisterActivity.this, "All fields are required :)", Toast.LENGTH_SHORT).show();
                return;
            }
            // Checking username
            if (username.length() < 3 || username.length() > 16 || !username.matches("^[A-Za-z0-9]*$")) {
                Toast.makeText(RegisterActivity.this, "Username must contain:\n3-16 chars\nletters and numbers only", Toast.LENGTH_SHORT).show();
                return;
            }
            // Checking password validations
            if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$")) {
                String message = "Password must contain:\n8-20 chars\na Capital letter\na Small letter\na number\na special symbol";
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                return;
            }
            // Checking repeated password
            if (!password.equals(rePassword)) {
                Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Login. Will work only if username doesn't already exist!
            registerWebService.register(username, password);
        });

        binding.clickRegistered.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}