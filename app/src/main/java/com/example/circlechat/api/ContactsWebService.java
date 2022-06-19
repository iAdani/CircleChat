package com.example.circlechat.api;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.circlechat.ChatListActivity;
import com.example.circlechat.CircleChatApp;
import com.example.circlechat.R;
import com.example.circlechat.Repository;
import com.example.circlechat.entities.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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

    public void getContacts(MutableLiveData<List<Contact>> contacts) {
        Call<List<Contact>> call = contactsWebServiceAPI.GetAll("Bearer " + Repository.getJwtToken());
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call,@NonNull Response<List<Contact>> response) {
                contacts.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) { }
        });
    }
}
