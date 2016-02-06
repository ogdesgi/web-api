package com.esgi.events.webservice;

import com.esgi.events.models.User;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by sylvainvincent on 04/02/16.
 */
public interface UserService {

    @GET("/users/user")
    Call<User> getUser(@Field("first_name") String email, @Field("last_name") String password);

    @POST("/users/new")
    Call<User> createUser(@Body User user);

}
