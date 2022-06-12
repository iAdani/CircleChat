package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.circlechat.databinding.ActivityLoginBinding;
import com.example.circlechat.databinding.ActivityRegisterBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.username.getText().toString().isEmpty() &&
                        !binding.password.getText().toString().isEmpty()){
                    // all fields are not empty
                    // still need to check if username and password match
                }
                else{
                    // if at least one of the fields is empty
                    Toast.makeText(LoginActivity.this, "All fields are required :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.clickNotRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}