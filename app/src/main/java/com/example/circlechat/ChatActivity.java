package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.circlechat.api.MessagesWebService;
import com.example.circlechat.databinding.ActivityChatBinding;
import com.example.circlechat.entities.Contact;
import com.example.circlechat.entities.Message;

import java.util.List;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private static final MutableLiveData<Contact> currentContact = new MutableLiveData<>();
    ActivityChatBinding binding;
    RecyclerView recyclerView;
    private MutableLiveData<List<Message>> messages;
    MessageRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        messages = new MutableLiveData<>();
        MessagesWebService messagesWebService = new MessagesWebService();
        adapter = new MessageRecyclerViewAdapter(this);

        recyclerView = findViewById(R.id.recyclerMessages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.backIcon.setOnClickListener(v -> finish());

        currentContact.observe(this, contact -> {
            messagesWebService.GetContactMessages(contact, this);
            adapter.setMessages(messages.getValue());
            this.binding.textViewTitle.setText(contact.getName());
        });

        messages.observe(this, messages -> {
            adapter.setMessages(messages);
        });
    }
//
    public void setMessages(List<Message> messages) {
        this.messages.setValue(messages);
    }
//
//    public void sendMessage() {
//        // send message to server
//
//    }
//
    public static void setCurrentContact(Contact currentContact) {
        ChatActivity.currentContact.setValue(currentContact);
    }
}