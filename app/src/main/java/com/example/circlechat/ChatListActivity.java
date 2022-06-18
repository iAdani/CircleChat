package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.circlechat.databinding.ActivityChatListBinding;
import com.example.circlechat.entities.Contact;
import com.example.circlechat.viewModels.ContactsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private ActivityChatListBinding binding;
    private ContactRecyclerViewAdapter adapter;
    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);


        // For showing all the recycler views (contacts list)
        RecyclerView recyclerView = binding.myRecycler;
        adapter = new ContactRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });



        binding.addNewChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}