package com.example.circlechat.api;

import com.example.circlechat.entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginWebServiceAPI {

    @POST("login")
    Call<String> Login(@Body User user);
}
