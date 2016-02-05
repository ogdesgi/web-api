package com.esgi.events.webservice;

import com.esgi.events.models.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sylvainvincent on 03/02/16.
 */
public interface EventService {

    @Headers("Content-Type: application/json")
    @POST("/event/new")
    Call<Event> makeEvent (@Body Event event);

    @GET("/event/all")
    Call<List<Event>> getAllEvent();

}
