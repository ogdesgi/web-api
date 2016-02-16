package com.esgi.events.webservice;

import com.esgi.events.models.Category;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

/**
 * Created by sylvainvincent on 15/02/16.
 */
public interface CategoryService {

    @GET("/myeventmanager/categories/")
    Call<List<Category>> getCategories();

    @POST("/myeventmanager/categories/")
    Call<List<Category>> postCategory(@Header("Authorization") String authorization, @Body Category category);

    @GET("/myeventmanager/categories/{id}")
    Call<List<Category>> getCategory(@Header("Authorization") String authorization,@Query("id") String id);

    @PUT("/myeventmanager/categories/{id}")
    Call<List<Category>> updateCategory(@Header("Authorization") String authorization,@Query("id") String id);

    @DELETE("/myeventmanager/categories/{id}")
    Call<List<Category>> deleteCategory(@Header("Authorization") String authorization, @Query("id") String id);

}
