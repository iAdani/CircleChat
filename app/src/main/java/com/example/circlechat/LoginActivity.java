package com.example.circlechat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.circlechat.api.LoginWebService;
import com.example.circlechat.databinding.ActivityLoginBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get firebase token
        if (Repository.getFirebaseToken() == null) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
                String token = instanceIdResult.getToken();
                Repository.setFirebaseToken(token);
            });
        }

        LoginWebService loginWebService = new LoginWebService(this);

        binding.loginbtn.setOnClickListener(v -> {
            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            if(username.isEmpty() || password.isEmpty()) {
                // if at least one of the fields is empty
                Toast.makeText(LoginActivity.this, R.string.all_fields_required, Toast.LENGTH_SHORT).show();
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