package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.circlechat.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.username.getText().toString().isEmpty() &&
                    !binding.nickname.getText().toString().isEmpty() &&
                    !binding.password.getText().toString().isEmpty() &&
                    !binding.repeatpassword.getText().toString().isEmpty()){
                        // all fields are not empty
                        // still need to check if they're valid
                }
                else{
                    Toast.makeText(RegisterActivity.this, "All fields are required :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.clickRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}