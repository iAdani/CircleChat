package com.example.circlechat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.circlechat.databinding.ActivityAddUserBinding;

public class AddUserActivity extends AppCompatActivity {
    ActivityAddUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backIcon.setOnClickListener(v -> finish());
    }
}