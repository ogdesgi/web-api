package com.e.api.events.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.e.api.events.R;

/**
 * Created by sylvainvincent on 16/01/16.
 */
public class EventFormActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;

    private void init(){
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_event_fab);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_event_form);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
