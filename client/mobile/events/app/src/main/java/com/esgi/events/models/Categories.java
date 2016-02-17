package com.esgi.events.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sylvainvincent on 17/02/16.
 */
public class Categories {

    @SerializedName(value="list")
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
