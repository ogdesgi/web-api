package com.esgi.events.webservice;

import com.esgi.events.models.Event;

import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 03/02/16.
 */
public class EventRestClient {

    private EventService eventService;

    private String url = "http://localhost:8080";

    public EventRestClient() {
        eventService = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventService.class);
    }

    public void getEvents(Callback<List<Event>> eventCallback) throws IOException {
        eventService.getAllEvent().enqueue(eventCallback);
    }
}
