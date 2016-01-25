package com.esgi.events.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.esgi.events.R;


/**
 * Created by sylvainvincent on 16/01/16.
 */
public class EventFormActivity extends AppCompatActivity {


    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
        init();
        toolbarInit();
        Log.e("e", "onCreate: ok");
    }

    private void init(){
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_event_fab);
    }

    private void toolbarInit(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void addEventAction(View view) {

    }
}