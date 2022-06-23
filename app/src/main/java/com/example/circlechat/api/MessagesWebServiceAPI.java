package com.example.circlechat.api;

import com.example.circlechat.entities.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessagesWebServiceAPI {
    @GET("contacts/{id}/messages")
    Call<List<Message>> GetAll(@Header("Authorization") String token, @Path("id") String id);

    @POST("messages")
    Call<Message> AddMessage(@Header("Authorization") String token, @Body Message message);
}
