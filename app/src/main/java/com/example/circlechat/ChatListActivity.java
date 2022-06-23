package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.circlechat.databinding.ActivityChatListBinding;
import com.example.circlechat.viewModels.ContactsViewModel;

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
            viewModel.updateContactsDB();
        });

        binding.addNewChat.setOnClickListener(v -> {
            Intent intent = new Intent(ChatListActivity.this, AddContactActivity.class);
            startActivity(intent);
        });

        binding.settingsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ChatListActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}