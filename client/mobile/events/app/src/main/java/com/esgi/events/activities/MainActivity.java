package com.esgi.events.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.adapters.EventsListAdapter;
import com.esgi.events.models.Event;
import com.esgi.events.models.Events;
import com.esgi.events.webservice.EventRestClient;
import com.esgi.events.webservice.EventService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView eventRecyclerView;
    private TextView title;
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Event> eventArrayList ;
    private String token;
    private String userId;
    private EventsListAdapter eventsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbarInit();
        this.init();
        token = getIntent().getStringExtra("token");
        userId = getIntent().getStringExtra("userId");
        eventArrayList = new ArrayList<Event>();

        EventRestClient eventRestClient = new EventRestClient();
        Call<Events> call = eventRestClient.getEvents();

        call.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(retrofit.Response<Events> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Events eventList = response.body();
                    Log.e(TAG, "onResponse: " + response.message());

                    for (Event event : eventList.getEventList()) {
                        Log.e(TAG, "onResponse: " + event.get_id() );
                        Log.e(TAG, "onResponse: " + event.getCreatorName() );
                        eventArrayList.add(new Event(event.get_id(), event.getTitle(), event.getDescription(), event.getLogo(), event.getCreatorName(), event.getDate()));
                    }
                    eventRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    eventsListAdapter = new EventsListAdapter(eventArrayList, MainActivity.this, userId);
                    eventRecyclerView.setAdapter(eventsListAdapter);
                } else {

                    Log.e(TAG, "onResponse: non success ");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onResponse: failure " + t);
                t.printStackTrace();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    private void init() {
        eventRecyclerView = (RecyclerView) findViewById(R.id.events_recycler_view);
    }

    private void toolbarInit(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.add_event_button:
                intent = new Intent(this, EventFormActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("userId", userId);
                startActivityForResult(intent, 1);
                break;
            case R.id.link_to_category:
                intent = new Intent(this, CategoriesActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
                break;
            case R.id.filter_button:
                Collections.reverse(this.eventArrayList);
                eventsListAdapter = new EventsListAdapter(eventArrayList, MainActivity.this, userId);
                eventRecyclerView.setAdapter(eventsListAdapter);
                break;
        }
    }
}