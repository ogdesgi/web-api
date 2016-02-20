package com.esgi.events.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sylvainvincent on 17/02/16.
 */
public class Events {

    @SerializedName(value="list")
    private List<Event> eventList;

    private Event profile;

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Event getProfile() {
        return profile;
    }

    public void setProfile(Event profile) {
        this.profile = profile;
    }
}
