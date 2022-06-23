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

        // get firebase token
        if (Repository.getFirebaseToken() == null) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
                String token = instanceIdResult.getToken();
                Repository.setFirebaseToken(token);
            });
        }

        RegisterWebService registerWebService = new RegisterWebService(this);

        binding.signupbtn.setOnClickListener(v -> {
            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            String rePassword = binding.repeatpassword.getText().toString();

            // Checking if empty
            if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() ||
                    binding.nickname.getText().toString().isEmpty()) {
                Toast.makeText(RegisterActivity.this, R.string.all_fields_required, Toast.LENGTH_SHORT).show();
                return;
            }
            // Checking username
            if (username.length() < 3 || username.length() > 16 || !username.matches(getString(R.string.regex_AZaz09))) {
                Toast.makeText(RegisterActivity.this, R.string.username_regex_mismatch, Toast.LENGTH_SHORT).show();
                return;
            }
            // Checking password validations
            if (!password.matches(getString(R.string.password_regex_criteria))) {
                String message = getString(R.string.password_regex_mismatch);
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                return;
            }
            // Checking repeated password
            if (!password.equals(rePassword)) {
                Toast.makeText(RegisterActivity.this, R.string.password_mismatch_err, Toast.LENGTH_SHORT).show();
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