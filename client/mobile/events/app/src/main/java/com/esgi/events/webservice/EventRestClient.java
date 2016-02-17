package com.esgi.events.webservice;

import com.esgi.events.models.Event;
import com.esgi.events.models.Events;
import com.esgi.events.models.Test;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
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

    public Call<Events> getEvents(){
        return eventService.getEvents();
    }

    public Call<Event> createEvents(String token, RequestBody file, Event event){
        return eventService.makeEvent("Bearer " + token, file, event.getTitle(), event.getDescription(), event.getCategory().get_id());
    }
}
