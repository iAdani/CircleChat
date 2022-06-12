package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.circlechat.databinding.ActivityChatListBinding;

public class ChatListActivity extends AppCompatActivity {
    ActivityChatListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addNewChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }

}