package com.example.circlechat.api;

import com.example.circlechat.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ContactsWebServiceAPI {
    @GET("contacts")
    Call<List<Contact>> GetAll(@Header("Authorization") String token);

    @POST("contacts")
    Call<Contact> AddContact(@Header("Authorization") String token, @Body Contact contact);
}