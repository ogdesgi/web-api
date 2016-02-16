package com.esgi.events.webservice;

import com.esgi.events.models.Category;
import com.esgi.events.models.Event;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 15/02/16.
 */
public class CategoryRestClient {

    private CategoryService categoryService;
    private final String URL = "http://10.0.3.2:8080";

    public CategoryRestClient(){
        categoryService = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(CategoryService.class);
    }

    public Call<List<Category>> getCategories() throws IOException {
        return categoryService.getCategories();
    }

}
