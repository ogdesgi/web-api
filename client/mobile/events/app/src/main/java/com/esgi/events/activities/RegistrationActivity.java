package com.esgi.events.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.models.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by sylvainvincent on 04/02/16.
 */
public class RegistrationActivity extends AppCompatActivity {

    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.lastname_field)
    EditText lastnameField;
    @Bind(R.id.firstname_field)
    EditText firstnameField;
    @Bind(R.id.email_field)
    EditText emailField;
    @Bind(R.id.password_field)
    EditText passwordField;
    @Bind(R.id.registration_coordinator)
    CoordinatorLayout registrationCoordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.subscribe_button)
    public void onClick() {
        if(isValid()){
            User user = new User();
            user.setFirstName(firstnameField.getText().toString());
            user.setLastName(lastnameField.getText().toString());
            user.setEmail(emailField.getText().toString());


        }
    }

    private boolean isValid() {
        boolean isValid = false;
        if (!firstnameField.getText().toString().equals("")
                && !lastnameField.getText().toString().equals("")
                && !emailField.getText().toString().equals("")
                && !passwordField.getText().toString().equals("")) {

        } else {
            Snackbar.make(registrationCoordinator,"Tous les champs sont obligatoires",Snackbar.LENGTH_LONG).show();
        }
        return isValid;
    }
}
