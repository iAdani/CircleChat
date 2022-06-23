package com.example.circlechat.api;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.circlechat.ChatActivity;
import com.example.circlechat.CircleChatApp;
import com.example.circlechat.R;
import com.example.circlechat.Repository;
import com.example.circlechat.entities.Contact;
import com.example.circlechat.entities.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessagesWebService {

    private final MessagesWebServiceAPI messagesWebServiceAPI;

    public MessagesWebService() {
        // Building the retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CircleChatApp.getContext().getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        messagesWebServiceAPI = retrofit.create(MessagesWebServiceAPI.class);
    }

    public void GetContactMessages(Contact contact, ChatActivity context) {
        if (contact != null) {
            Call<List<Message>> call = messagesWebServiceAPI.GetAll("Bearer " + Repository.getJwtToken(), contact.getId());
            call.enqueue(new Callback<List<Message>>() {
                @Override
                public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                    if (response.isSuccessful()) {
                        context.setMessages(response.body());
                    } else {
                        Toast.makeText(CircleChatApp.getContext(), "Error getting messages", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable t) {
                }
            });
        }
    }
}
