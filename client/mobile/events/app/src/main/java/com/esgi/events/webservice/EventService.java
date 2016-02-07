package com.esgi.events.webservice;

import com.esgi.events.models.Event;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

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
