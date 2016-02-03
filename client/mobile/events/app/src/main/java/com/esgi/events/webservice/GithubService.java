package com.esgi.events.webservice;

import com.esgi.events.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sylvainvincent on 02/02/16.
 */
public interface GithubService {

    public static final String ENDPOINT = "https://api.github.com";

    @Headers("Cache-Control: max-age=640000")
    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("/search/repositories")
    Call<List<Repo>> searchRepos(@Query("q") String query);

}