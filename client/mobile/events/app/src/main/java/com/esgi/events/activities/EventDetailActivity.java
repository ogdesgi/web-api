package com.esgi.events.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.adapters.EventsListAdapter;
import com.esgi.events.models.Event;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by sylvainvincent on 27/01/16.
 */
public class EventDetailActivity extends AppCompatActivity {

    private ImageView eventCover;
    private TextView eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        int eventId = getIntent().getIntExtra(EventsListAdapter.PUT_EVENT_ID, 0);

        /*Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        Event event = realm.where(Event.class).equalTo("id", eventId).findFirst();
        Picasso.with(this).load(event.getLogo()).into(eventCover);
        eventTitle.setText(event.getTitle());*/

        /*title.setText(event.getTitle().toString());
        date.setText(event.getDate().toString());
        description.setText(event.getDescription());*/

    }

    private void init(){
        eventCover = (ImageView) findViewById(R.id.event_picture);
        eventTitle = (TextView) findViewById(R.id.event_title);
    }

}
