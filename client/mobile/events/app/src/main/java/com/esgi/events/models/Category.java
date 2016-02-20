package com.esgi.events.models;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by sylvainvincent on 17/01/16.
 */
public class Category extends RealmObject{

    private String _id;
    private String name;

    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
