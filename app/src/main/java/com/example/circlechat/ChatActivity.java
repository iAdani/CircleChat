package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.circlechat.api.MessagesWebService;
import com.example.circlechat.databinding.ActivityChatBinding;
import com.example.circlechat.entities.Contact;
import com.example.circlechat.entities.Message;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private static MutableLiveData<Contact> currentContact;
    ActivityChatBinding binding;
    MutableLiveData<List<Message>> messages;
    MessagesWebService messagesWebService;
    MessageRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backIcon.setOnClickListener(v -> finish());

        messagesWebService = new MessagesWebService();
//        binding.


        currentContact.observe(this, contact -> messagesWebService.GetContactMessages(currentContact.getValue(), this));

            messages.observe(this, messages -> {
                adapter.setMessages(messages);
                adapter.notifyDataSetChanged();
                binding.chatRecycler.scrollToPosition(adapter.getItemCount() - 1);
            });
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