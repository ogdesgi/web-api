package com.esgi.events.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.models.Event;

/**
 * Created by sylvainvincent on 27/01/16.
 */
public class EventDetailActivity extends AppCompatActivity {

    private TextView title,
            date,
            description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Event event = new Event();

        title.setText(event.getTitle().toString());
        date.setText(event.getDate().toString());
        description.setText(event.getDescription());

    }

    private void init(){
        title = (TextView) findViewById(R.id.event_title);
        date = (TextView) findViewById(R.id.event_date);
        description = (TextView) findViewById(R.id.event_description);
    }
}
