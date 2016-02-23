package com.esgi.events.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by sylvainvincent on 16/01/16.
 */
public class Event {

    private String _id;
    private String title;
    @SerializedName("description")
    private String description;
    private String logo;
    private String creator;
    private String creatorName;
    private String category;
    private String categoryName;
    private Date date;
    @Ignore
    private List<String> participants;
    private List<String> participantsNames;

    public Event(){}

    public Event(String id, String title, String logo) {
        this._id = id;
        this.title = title;
        this.logo = logo;
    }

    public Event(String id, String title, String description, String photoPath, String creatorName, Date date) {
        this._id = id;
        this.title = title;
        this.description = description;
        this.logo = photoPath;
        this.creatorName = creatorName;
        this.date = date;
    }

   /* public Event(int id, String title, String description, String photoPath, User creator, Date date, RealmList<User> participants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logo = photoPath;
        this.creator = creator;
        this.date = date;
        this.participants = participants;
    }*/

    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   /* public RealmList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(RealmList<User> participants) {
        this.participants = participants;
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getParticipantsNames() {
        return participantsNames;
    }

    public void setParticipantsNames(List<String> participantsNames) {
        this.participantsNames = participantsNames;
    }


}
