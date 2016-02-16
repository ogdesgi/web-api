package com.esgi.events.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.models.User;
import com.esgi.events.webservice.UserRestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by sylvainvincent on 04/02/16.
 */
public class RegistrationActivity extends AppCompatActivity {


    private final String TAG = getClass().getSimpleName();
    @Bind(R.id.lastname_field)
    EditText lastnameField;
    @Bind(R.id.firstname_field)
    EditText firstnameField;
    @Bind(R.id.email_field)
    EditText emailField;
    @Bind(R.id.password_field)
    EditText passwordField;
    @Bind(R.id.coordinator_registration)
    CoordinatorLayout registrationCoordinator;
    Callback<User> callback;
    @Bind(R.id.subscribe_button)
    TextView subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        callback = new Callback<User>() {
            @Override
            public void onResponse(Response response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Snackbar.make(registrationCoordinator, "Inscription réussi", Snackbar.LENGTH_LONG).show();
                } else {
                    Log.e(TAG, "no success: " + response.message());
                    Log.e(TAG, "no success: " + response.code());
                    Log.e(TAG, "no success: " + response.errorBody().toString());
                    Snackbar.make(registrationCoordinator, "Cette adresse mail existe déjà", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.e(TAG, "onFailure: " + t.toString());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                t.printStackTrace();
                Snackbar.make(registrationCoordinator, "Une erreur s'est produite lors de votre inscription", Snackbar.LENGTH_LONG).show();
            }
        };

    }

    private boolean isValid() {
        boolean isValid = false;
        if (!firstnameField.getText().toString().equals("")
                && !lastnameField.getText().toString().equals("")
                && !emailField.getText().toString().equals("")
                && !passwordField.getText().toString().equals("")) {
            isValid = true;
        } else {
            Snackbar.make(registrationCoordinator, "Tous les champs sont obligatoires", Snackbar.LENGTH_LONG).show();
        }
        return isValid;
    }

    @OnClick(R.id.subscribe_button)
    public void onClick() {
        if(isValid()){

            User user = new User();
            user.setFirstname(firstnameField.getText().toString());
            user.setLastname(lastnameField.getText().toString());
            user.setEmail(emailField.getText().toString());
            user.setPassword(passwordField.getText().toString());

            UserRestClient userRestClient = new UserRestClient();
            try{
                userRestClient.postUser(user).enqueue(callback);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
