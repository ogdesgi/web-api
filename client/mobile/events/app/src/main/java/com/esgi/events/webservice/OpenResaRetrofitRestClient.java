package com.esgi.events.webservice;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by sylvainvincent on 03/02/16.
 */
public class OpenResaRetrofitRestClient {

    private OpenResaServices mOpenResaServices;

    public OpenResaRetrofitRestClient(Context context) {
        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(context.getString(ENDPOINT))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                 .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.e("RestClient", "resquest: " + message);
                    }
                }) //permet d'avoir le détail de la requête Retrofit dans les logs
                .setConverter(new GsonConverter(gson))
                .build();

        mOpenResaServices = restAdapter.create(OpenResaServices.class);

    }
}
