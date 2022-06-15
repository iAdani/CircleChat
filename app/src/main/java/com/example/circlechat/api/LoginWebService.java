package com.example.circlechat.api;

import android.content.Intent;
import android.widget.Toast;

import com.example.circlechat.ChatListActivity;
import com.example.circlechat.LoginActivity;
import com.example.circlechat.R;
import com.example.circlechat.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginWebService {

    private final LoginWebServiceAPI loginWebServiceAPI;
    private final LoginActivity loginActivity; // The context

    public LoginWebService(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;

        // Building the retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.loginActivity.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loginWebServiceAPI = retrofit.create(LoginWebServiceAPI.class);
    }

    public void login(String username, String password) {
        // Creating a login call to the server
        Call<String> call = loginWebServiceAPI.Login(new User(username, password));
        loginActivity.runOnUiThread(() -> call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    // If 200OK move to chats
                    Intent intent = new Intent(loginActivity, ChatListActivity.class);
                    loginActivity.startActivity(intent);
                } else {
                    Toast.makeText(loginActivity, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(loginActivity, "Cannot find the server.", Toast.LENGTH_SHORT).show();
            }
        }));
    }


}
