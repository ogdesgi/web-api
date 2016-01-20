package com.esgi.events.models;

import java.util.Date;

/**
 * Created by sylvainvincent on 16/01/16.
 */
public class Event {

    private String title;
    private String description;
    private String photoPath;
    private String author;
    private Date date;

    public Event(){}

    public Event(String title, String description, String photoPath, String author, Date date) {
        this.title = title;
        this.description = description;
        this.photoPath = photoPath;
        this.author = author;
        this.date = date;
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

    @Override
    public String toString() {
        return "event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }
}
