package com.esgi.events.models;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by sylvainvincent on 17/01/16.
 */
public class Category extends RealmObject{

    private String label;

    public Category(){}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
