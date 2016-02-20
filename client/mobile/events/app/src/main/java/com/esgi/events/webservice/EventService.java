package com.esgi.events.webservice;

import com.esgi.events.models.Category;
import com.esgi.events.models.Event;
import com.esgi.events.models.Events;
import com.esgi.events.models.User;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by sylvainvincent on 03/02/16.
 */
public interface EventService {

    @Multipart
    @POST("/myeventmanager/events")
    Call<Event> makeEvent (@Header("Authorization") String authorization,
                           @Part("logo\"; filename=\"image.jpeg\" ") RequestBody file,
                           @Part("title") RequestBody title,
                           @Part("description") RequestBody description,
                           @Part("category") RequestBody category);

    @Multipart
    @POST("/myeventmanager/events")
    Call<Event> makeEventWithoutPhoto (@Header("Authorization") String authorization,
                           @Part("title") RequestBody title,
                           @Part("description") RequestBody description,
                                       @Part("category") RequestBody category);


    @GET("/myeventmanager/events")
    Call<Events> getEvents();

    @GET("/myeventmanager/events/{evt_id}")
    Call<Events> getEvent(@Path("evt_id") String id);

    @DELETE("/myeventmanager/events/{evt_id}")
    Call<Events> deleteEvent(@Header("Authorization") String authorization, @Path("evt_id") String id);

    @Headers("Content-Type: application/json")
    @POST("/myeventmanager/events/{evt_id}/join")
    Call<Event> joinEvent(@Header("Authorization") String authorization, @Path("evt_id") String id, @Body User user);

    @Headers("Content-Type: application/json")
    @POST("/myeventmanager/events/{evt_id}/leave")
    Call<Event> leaveEvent(@Header("Authorization") String authorization, @Path("evt_id") String id, @Body User user);

}
