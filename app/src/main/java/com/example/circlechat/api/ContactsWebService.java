package com.example.circlechat.api;

import android.widget.Toast;

import com.example.circlechat.ChatListActivity;
import com.example.circlechat.CircleChatApp;
import com.example.circlechat.R;
import com.example.circlechat.entities.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsWebService {
    private final ContactsWebServiceAPI contactsWebServiceAPI;

    public ContactsWebService() {
        // Building the retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CircleChatApp.getContext().getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        contactsWebServiceAPI = retrofit.create(ContactsWebServiceAPI.class);
    }

    // @TODO finish
    public List<Contact> getContacts() {
        List<Contact> contacts;
        Call<List<Contact>> call = contactsWebServiceAPI.GetAll();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });
    }
}
