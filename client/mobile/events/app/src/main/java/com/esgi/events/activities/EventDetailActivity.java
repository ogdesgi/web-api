package com.esgi.events.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.models.Event;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sylvainvincent on 27/01/16.
 */
public class EventDetailActivity extends AppCompatActivity {

    @Bind(R.id.event_title) private TextView title;
    @Bind(R.id.event_date) private TextView date;
    @Bind(R.id.event_description) private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        Event event = new Event();

        title.setText(event.getTitle().toString());
        date.setText(event.getDate().toString());
        description.setText(event.getDescription());

    }

}
