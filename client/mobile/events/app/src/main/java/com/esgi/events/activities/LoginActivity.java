package com.esgi.events.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.esgi.events.R;

/**
 * Created by sylvainvincent on 25/01/16.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbarInit();
    }

    public void loginAction(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void toolbarInit(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
