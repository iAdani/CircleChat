package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.circlechat.api.MessagesWebService;
import com.example.circlechat.databinding.ActivityChatBinding;
import com.example.circlechat.entities.Contact;
import com.example.circlechat.entities.Message;

import java.util.List;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private static MutableLiveData<Contact> currentContact;
    ActivityChatBinding binding;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Populate dummy messages in List, you can implement your code here
        ArrayList<MessageModel> messagesList = new ArrayList<>();
        for (int i=0;i<10;i++) {
            messagesList.add(new MessageModel("Hi", i % 2 == 0 ? CustomAdapter.MESSAGE_TYPE_IN : CustomAdapter.MESSAGE_TYPE_OUT));
        }

        CustomAdapter adapter = new CustomAdapter(this, messagesList);

        recyclerView = findViewById(R.id.recyclerMessages);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, ChatListActivity.class);
                startActivity(intent);
            }

        });
    }

    public void setMessages(List<Message> messages) {
        this.messages.setValue(messages);
    }

    public void sendMessage() {
        // send message to server

    }

    public static void setCurrentContact(Contact currentContact) {
        ChatActivity.currentContact = currentContact;
    }
}