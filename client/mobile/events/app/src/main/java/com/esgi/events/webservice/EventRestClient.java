package com.esgi.events.webservice;

import com.esgi.events.models.Event;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 03/02/16.
 */
public class EventRestClient {

    private EventService eventService;

    private String url = "http://10.0.3.2:8080";

    public EventRestClient() {


        eventService = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventService.class);
    }

    public Call<List<Event>> getEvents(){
        return eventService.getEvents();
    }

    public void createEvents(String token, Event event){
        eventService.makeEvent("Bearer "+ token, event);
    }
}
