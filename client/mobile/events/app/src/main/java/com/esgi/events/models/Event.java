package com.esgi.events.models;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sylvainvincent on 16/01/16.
 */
public class Event extends RealmObject {

    private int id;
    private String title;
    private String description;
    private String logo;
    private User creator;
    private Date date;
    private RealmList<User> participants;

    public Event(){}

    public Event(int id, String title, String logo) {
        this.id = id;
        this.title = title;
        this.logo = logo;
    }

    public Event(int id, String title, String description, String photoPath, User creator, Date date, RealmList<User> participants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logo = photoPath;
        this.creator = creator;
        this.date = date;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public User getAuthor() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RealmList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(RealmList<User> participants) {
        this.participants = participants;
    }


}
