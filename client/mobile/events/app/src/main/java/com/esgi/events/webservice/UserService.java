package com.esgi.events.webservice;

import com.esgi.events.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sylvainvincent on 04/02/16.
 */
public interface UserService {

    @GET("/user/{userId}")
    Call<User> getUser(@Path("userId") int userId);
}
