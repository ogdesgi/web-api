package com.esgi.events.webservice;

import com.esgi.events.models.User;

import junit.framework.Test;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by sylvainvincent on 04/02/16.
 */
public interface UserService {

    @Headers("Content-Type: application/json")
    @POST("/myeventmanager/auth/login")
    Call<com.esgi.events.models.Test> toLogin(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("/myeventmanager/users")
    Call<User> toSubscribe(@Body User user);

}

