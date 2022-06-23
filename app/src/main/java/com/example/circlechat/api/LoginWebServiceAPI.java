package com.example.circlechat.api;

import com.example.circlechat.entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginWebServiceAPI {
    @POST("login")
    Call<String> Login(
            @Header("Token") String token, // firebase token
            @Body User user);

    @GET("{id}")
    Call<String> isUserExists(String id);
}
