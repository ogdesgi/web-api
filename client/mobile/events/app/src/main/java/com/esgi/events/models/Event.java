package com.esgi.events.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sylvainvincent on 16/01/16.
 */
public class Event extends RealmObject {

    private int id;
    private String title;
    private String description;
    private String photoPath;
    private String author;
    private Date date;
    private RealmList<User> participantsArrayList;

    public Event(){}

    public Event(int id, String title, String photoPath) {
        this.id = id;
        this.title = title;
        this.photoPath = photoPath;
    }

    public Event(String title, String description, String photoPath, String author, Date date) {
        this.title = title;
        this.description = description;
        this.photoPath = photoPath;
        this.author = author;
        this.date = date;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RealmList<User> getParticipantsArrayList() {
        return participantsArrayList;
    }

    public void setParticipantsArrayList(RealmList<User> participantsArrayList) {
        this.participantsArrayList = participantsArrayList;
    }


}
