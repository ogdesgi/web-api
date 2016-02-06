package com.esgi.events.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.esgi.events.R;
import com.esgi.events.models.User;
import com.esgi.events.webservice.UserRestClient;
import com.squareup.picasso.Downloader;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 25/01/16.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Callback<User> userCallback;
    private EditText emailField,
                     passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        toolbarInit();

        this.userCallback = new Callback<User>() {

            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Realm realm = Realm.getInstance(LoginActivity.this);
                    realm.beginTransaction();

                    User user = response.body();
                    realm.copyToRealm(user);
                    realm.commitTransaction();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Log.e(TAG, "onResponse: no success " );
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onResponse: fail " );
            }
        };
    }

    private void init(){
        emailField = (EditText) findViewById(R.id.email_field);
        passwordField = (EditText) findViewById(R.id.password_field);

    }

    private void toolbarInit(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private boolean isValide(){
        boolean bool = false;

        if (!(emailField.getText().toString().equals("") && passwordField.getText().toString().equals(""))) {
            bool = true;
        }

        return bool;
    }

    public void loginAction(View view) throws IOException {

        if(isValide()){

            UserRestClient userRestClient = new UserRestClient();
            userRestClient.getUser(emailField.getText().toString(), passwordField.getText().toString(), userCallback);
        }else{
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }


}
