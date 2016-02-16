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
import com.esgi.events.webservice.EventRestClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView eventRecyclerView;
    private TextView title;
    private final String TAG = getClass().getSimpleName();
    ArrayList<Event> eventArrayList ;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbarInit();
        this.init();
        token = getIntent().getStringExtra("token");
        eventArrayList = new ArrayList<>();

        EventRestClient eventRestClient = new EventRestClient();
        try {
            Call<List<Event>> listCall = eventRestClient.getEvents();
            listCall.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(retrofit.Response<List<Event>> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        List<Event> eventList = response.body();
                        for (Event event : eventList) {
                            eventArrayList.add(new Event(event.getId(), event.getTitle(), event.getDescription(), event.getLogo(), event.getCreator(), event.getDate()));
                        }
                        eventRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        eventRecyclerView.setAdapter(new EventsListAdapter(eventArrayList, MainActivity.this));
                    } else {

                        Log.e(TAG, "onResponse: non success ");
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "onResponse: failure " + t.getMessage());
                    t.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
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

    public void actionToEventFormActivity(View view) {
        Intent intent = new Intent(this, EventFormActivity.class);
        intent.putExtra("token",token);
        startActivity(intent);
    }
}