package com.esgi.events.webservice;

import com.esgi.events.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sylvainvincent on 04/02/16.
 */
public interface UserService {

    @GET("/users/user")
    Call<User> getUser(@Field("first_name") String email, @Field("last_name") String password);

    @POST("/users/new")
    Call<User> createUser(@Body User user);

}
