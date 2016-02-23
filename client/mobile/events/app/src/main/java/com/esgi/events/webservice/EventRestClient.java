package com.esgi.events.webservice;

import android.util.Log;

import com.esgi.events.models.Category;
import com.esgi.events.models.Event;
import com.esgi.events.models.Events;
import com.esgi.events.models.User;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

import io.realm.RealmObject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 03/02/16.
 */
public class EventRestClient {

    private static final String TAG = "EventRestClient";
    
    private EventService eventService;

    private String url = "http://10.0.3.2:8080";

    public EventRestClient() {

        ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setExclusionStrategies(exclusionStrategy)
                .create();

        eventService = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(EventService.class);
    }

    public Call<Events> getEvent(String id){
        return eventService.getEvent(id);
    }

    public Call<Events> deleteEvent(String token, String id){
        return eventService.deleteEvent("Bearer " + token, id);
    }

    public Call<Event> joinEvent(String token, String id, User user){
        return eventService.joinEvent("Bearer " + token, id, user);
    }

    public Call<Event> leaveEvent(String token, String id, User user){
        return eventService.leaveEvent("Bearer " + token, id, user);
    }

    public Call<Events> getEvents(){
        return eventService.getEvents();
    }

    public Call<Event> createEvents(String token, RequestBody file, Event event){
        RequestBody title = RequestBody.create(MediaType.parse("multipart/form-data"), event.getTitle());
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), event.getDescription());
        RequestBody category = RequestBody.create(MediaType.parse("multipart/form-data"), event.getCategory());
        Log.e(TAG, "createEvents: " + event.getCategory() );
        if(file != null)return eventService.makeEvent("Bearer " + token, file, title, description, category);
        else return eventService.makeEventWithoutPhoto("Bearer " + token, title, description, category);
    }

    public Call<Event> updateEvent(String token, String id, RequestBody file, Event event){
        RequestBody title = RequestBody.create(MediaType.parse("multipart/form-data"), event.getTitle());
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), event.getDescription());
        RequestBody category = RequestBody.create(MediaType.parse("multipart/form-data"), event.getCategory());
        Log.e(TAG, "createEvents: " + event.getCategory() );
        if(file != null)return eventService.updateEvent("Bearer " + token, id, file, title, description, category);
        else return eventService.updateEventWithoutPhoto("Bearer " + token, id, title, description, category);
    }
}
