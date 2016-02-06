package com.esgi.events.webservice;

import android.content.Context;

import com.esgi.events.models.Event;
import com.esgi.events.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by sylvainvincent on 04/02/16.
 */
public class UserRestClient {

    private UserService userService;

    private String url = "https://api.github.com";

    public UserRestClient() {
        Gson gson = new GsonBuilder().create();
        userService = new Retrofit.Builder()
                .baseUrl(url)
                //.addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

    public void getUser(String email, String password, Callback<User> userCallback) throws IOException {
        userService.getUser(email, password).enqueue(userCallback);
    }

}
