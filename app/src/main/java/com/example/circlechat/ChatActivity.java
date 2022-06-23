package com.example.circlechat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.circlechat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backIcon.setOnClickListener(v -> finish());
    }
}