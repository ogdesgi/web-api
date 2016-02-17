package com.esgi.events.webservice;

import com.esgi.events.models.Event;
import com.esgi.events.models.Events;
import com.esgi.events.models.Test;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by sylvainvincent on 03/02/16.
 */
public interface EventService {

    @Multipart
    @POST("/myeventmanager/events")
    Call<Event> makeEvent (@Header("Authorization") String authorization,
                           @Part("logo\"; filename=\"image.jpeg\" ") RequestBody file,
                           @Part("title") String title,
                           @Part("description") String description,
                           @Part("category") String category);

    @GET("/myeventmanager/events")
    Call<Events> getEvents();

}
