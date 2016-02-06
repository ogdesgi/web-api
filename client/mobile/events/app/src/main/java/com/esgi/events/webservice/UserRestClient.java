package com.esgi.events.webservice;

import com.esgi.events.models.User;

import java.io.IOException;

import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 04/02/16.
 */
public class UserRestClient {

    private UserService userService;

    private String url = "https://api.github.com";

    public UserRestClient() {
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
