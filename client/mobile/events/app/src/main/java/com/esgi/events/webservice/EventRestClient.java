package com.esgi.events.webservice;

import android.content.Context;

import com.esgi.events.models.Event;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
/**
 * Created by sylvainvincent on 03/02/16.
 */
public class EventRestClient {

    private EventService eventService;

    private String url = "https://api.github.com";

    public EventRestClient() {
        eventService = new Retrofit.Builder()
                .baseUrl(url)
               // .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventService.class);
    }

    public List<Event> getEvents() throws IOException {
        return eventService.getAllEvent().execute().body();
    }
}
