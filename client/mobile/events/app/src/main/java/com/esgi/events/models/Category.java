package com.esgi.events.models;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by sylvainvincent on 17/01/16.
 */
public class Category{

    private String name;

    public Category(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
