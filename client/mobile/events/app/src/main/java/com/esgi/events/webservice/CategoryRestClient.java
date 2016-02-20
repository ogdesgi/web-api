package com.esgi.events.webservice;

import android.util.Log;

import com.esgi.events.models.Categories;
import com.esgi.events.models.Category;
import com.esgi.events.models.Event;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import io.realm.RealmObject;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 15/02/16.
 */
public class CategoryRestClient {

    private static final String TAG = "CategoryRestClient";
    private CategoryService categoryService;
    private final String URL = "http://10.0.3.2:8080";

    public CategoryRestClient(){

        ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(exclusionStrategy)
                .create();

        categoryService = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build()
                                .create(CategoryService.class);
    }

    public Call<Categories> getCategories(){
        return categoryService.getCategories();
    }

    public Call<Category> postCategory(String token, Category category){
        Log.e(TAG, "postCategory: " + token );
        return categoryService.postCategory("Bearer " + token, category);
    }

    public Call<Category> updateCategory(String token, String id, Category category){
        return categoryService.updateCategory("Bearer " + token, id, category);
    }

    public Call<Category> deleteCategory(String token, String id){
        Log.e(TAG, "deleteCategory: " + id );
        Log.e(TAG, "deleteCategory: " + token );
        return categoryService.deleteCategory("Bearer " + token, id);
    }
}
