package com.esgi.events.webservice;

import android.util.TypedValue;

import com.esgi.events.models.User;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import junit.framework.Test;

import java.io.IOException;

import io.realm.RealmObject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 04/02/16.
 */
public class UserRestClient {

    private UserService userService;

    private String url = "http://10.0.3.2:8080";

    public UserRestClient() {
        OkHttpClient okClient = new OkHttpClient();
        okClient.interceptors().add(new Interceptor() {
                                        @Override
                                        public Response intercept(Chain chain) throws IOException {
                                            Response response = chain.proceed(chain.request());
                                            return response;
                                        }
                                    });

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

        userService = new Retrofit.Builder()
                .baseUrl(url)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(UserService.class);
    }

    public Call<User> toLogin(String email, String password){
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        return userService.toLogin(user);
    }

    public Call<User> postUser(User user){
       return userService.toSubscribe(user);
    }

}
