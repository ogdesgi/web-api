package com.esgi.events.webservice;

import com.esgi.events.models.Categories;
import com.esgi.events.models.Category;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

/**
 * Created by sylvainvincent on 15/02/16.
 */
public interface CategoryService {

    @GET("/myeventmanager/categories/")
    Call<Categories> getCategories();

    @Headers("Content-Type: application/json")
    @POST("/myeventmanager/categories/")
    Call<Category> postCategory(@Header("Authorization") String authorization, @Body Category category);

    @GET("/myeventmanager/categories/{id}")
    Call<List<Category>> getCategory(@Header("Authorization") String authorization,@Query("id") String id);

    @PUT("/myeventmanager/categories/{id}")
    Call<Category> updateCategory(@Header("Authorization") String authorization,@Query("id") String id);

    @DELETE("/myeventmanager/categories/{id}")
    Call<Category> deleteCategory(@Header("Authorization") String authorization, @Query("id") String id);

}
