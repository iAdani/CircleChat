package com.example.circlechat.api;

import com.example.circlechat.entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RegisterWebServiceAPI {
    @POST("register")
    Call<String> Register(
            @Header("Token") String token,
            @Body User user);
}
