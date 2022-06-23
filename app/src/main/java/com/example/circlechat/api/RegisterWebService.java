package com.example.circlechat.api;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.circlechat.ChatListActivity;
import com.example.circlechat.R;
import com.example.circlechat.RegisterActivity;
import com.example.circlechat.Repository;
import com.example.circlechat.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterWebService {
    private final RegisterWebServiceAPI registerWebServiceAPI;
    private final RegisterActivity registerActivity;

    public RegisterWebService(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;

        // Building the retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.registerActivity.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        registerWebServiceAPI = retrofit.create(RegisterWebServiceAPI.class);
    }

    public void register(String username, String password) {
        // Creating a register call to the server
        User user = new User(username, password);
        // Get firebase token
        String token = Repository.getFirebaseToken();
        // Creating a register call to the server
        Call<String> call = registerWebServiceAPI.Register(token, user);
        registerActivity.runOnUiThread(() -> call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()) {

                    // If 200OK move to chats
                    Intent intent = new Intent(registerActivity, ChatListActivity.class);
                    registerActivity.startActivity(intent);
                } else {
                    Toast.makeText(registerActivity, R.string.user_exists_err, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(registerActivity, R.string.server_not_found_err, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
