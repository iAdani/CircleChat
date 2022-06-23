package com.example.circlechat.api;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.circlechat.ChatListActivity;
import com.example.circlechat.LoginActivity;
import com.example.circlechat.R;
import com.example.circlechat.Repository;
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
        // Create a user object
        User user = new User(username, password);
        // Get firebase token
        String token = Repository.getFirebaseToken();
        // Creating a login call to the server!
        Call<String> call = loginWebServiceAPI.Login(token, user);
        loginActivity.runOnUiThread(() -> call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()) {

                    // If 200OK save token and move to chats
                    String header = response.headers().get("Set-Cookie");
                    assert header != null;
                    Repository.setJwtToken(header.substring(header.indexOf("=") + 1, header.indexOf(";")));
                    Intent intent = new Intent(loginActivity, ChatListActivity.class);
                    loginActivity.startActivity(intent);
                } else {
                    Toast.makeText(loginActivity, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(loginActivity, R.string.server_not_found_err, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
