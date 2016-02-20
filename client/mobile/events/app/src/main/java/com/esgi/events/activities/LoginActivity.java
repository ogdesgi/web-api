package com.esgi.events.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.models.User;
import com.esgi.events.webservice.UserRestClient;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 25/01/16.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @Bind(R.id.test)
    TextView test;
    @Bind(R.id.email_field)
    EditText emailField;
    @Bind(R.id.password_field)
    EditText passwordField;
    @Bind(R.id.coordinator_login)
    CoordinatorLayout coordinatorLogin;
    private Callback<User> userCallback;

    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        toolbarInit();

        this.userCallback = new Callback<User>() {

            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                //    Realm realm = Realm.getInstance(LoginActivity.this);

                    User user = response.body();
                    /*realm.beginTransaction();
                    User realmUser = realm.copyToRealm(user);
                    realm.commitTransaction();*/
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    token = user.getToken();
                    intent.putExtra("token",user.getToken());
                    intent.putExtra("userId",user.getId());
                    startActivity(intent);
                } else {
                    Snackbar.make(coordinatorLogin, "Identifiant/mot de passe incorrect", Snackbar.LENGTH_LONG).show();
                    Log.e(TAG, "onResponse: no success body " + response.body());
                    Log.e(TAG, "onResponse: no success errorbody " + response.errorBody());
                    Log.e(TAG, "onResponse: no success raw body " + response.raw().body());
                    Log.e(TAG, "onResponse: no success message " + response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Snackbar.make(coordinatorLogin, "Serveur indisponible", Snackbar.LENGTH_LONG).show();
                Log.e(TAG, "onResponse: fail ");
            }
        };
    }

    private void toolbarInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private boolean isValide() {
        boolean bool = false;

        if (!(emailField.getText().toString().equals("") && passwordField.getText().toString().equals(""))) {
            bool = true;
        }

        return bool;
    }


    public void registrationAction(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public void loginAction(View view) {

        if (isValide()) {

            UserRestClient userRestClient = new UserRestClient();

            userRestClient.toLogin(emailField.getText().toString(), passwordField.getText().toString()).enqueue(userCallback);

        } else {
            Snackbar.make(coordinatorLogin, "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG).show();
        }

    }
}
