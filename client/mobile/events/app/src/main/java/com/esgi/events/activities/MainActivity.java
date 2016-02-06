package com.esgi.events.activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.esgi.events.models.Exemple;
import com.esgi.events.webservice.ExempleRestClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView eventRecyclerView;
    private TextView title;
    private final String TAG = getClass().getSimpleName();
    ArrayList<Event> eventArrayList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbarInit();
        this.init();

        eventArrayList = new ArrayList<>();
        ExempleRestClient exempleRestClient = new ExempleRestClient();
        try {
            exempleRestClient.getExemple("square", "retrofit", new Callback<List<Exemple>>() {
                @Override
                public void onResponse(retrofit.Response<List<Exemple>> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        List<Exemple> exemples = response.body();
                        for(Exemple exemple: exemples){
                            eventArrayList.add(new Event(exemple.getLogin(),"http://www.wepeek.fr/wp-content/uploads/2015/08/Groupe-de-travail.jpg"));
                        }
                        eventRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        eventRecyclerView.setAdapter(new EventsListAdapter(eventArrayList,MainActivity.this));
                    } else {

                        Log.e(TAG, "onResponse: non success" );
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "onResponse: failure" );
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }




        /*String url="http://api.androidhive.info/volley/person_object.json";
        eventArrayList = new ArrayList<>();
        final Event event = new Event();
        final Event event2 = new Event();
        final Event event3 = new Event();
        JSONObject test = null;

        try {
            test = new JSONObject(loadJSONFromRaw(R.raw.exemple));
            JSONArray jsonArray = test.getJSONArray("user");
            JSONObject obj = (JSONObject) jsonArray.get(0);
            title.setText(obj.getString("lastname"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
        // title.setText("Response: " + jsonObject.toString());
                String textResult = "";
                try {
                    //JSONArray arrProducts = jsonObject.getJSONArray("products");
                    //for(int i=0; i<arrProducts.length(); i++){
                        //JSONObject productItem = (JSONObject)arrProducts.get(i);
                    Log.d(TAG, "onResponse: " + jsonObject.getString("email"));
                        event.setTitle(jsonObject.getString("email"));
                        event2.setTitle(jsonObject.getString("email"));
                        event3.setTitle(jsonObject.getString("email"));
                    event.setPhotoPath("http://www.wepeek.fr/wp-content/uploads/2015/08/Groupe-de-travail.jpg");
                    event2.setPhotoPath("http://www.secretaire-inc.com/image9043");
                    event3.setPhotoPath("http://www.frenchweb.fr/wp-content/uploads/2014/02/Meeting.png");
                        //event.setDate(new Date());
                        //textResult += "Description: " + jsonObject.getString("email") + "\n";
                        //textResult += "Price: " + jsonObject.getString("phone") + "\n\n";
                   // }

                    eventArrayList.add(event);
                    eventArrayList.add(event2);
                    eventArrayList.add(event3);
                    eventRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    eventRecyclerView.setAdapter(new EventsListAdapter(eventArrayList,MainActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError != null) Log.e(TAG, volleyError.getMessage());
            }
        });

        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
*/
    }

    private void init() {
        eventRecyclerView = (RecyclerView) findViewById(R.id.events_recycler_view);
    }

    private void toolbarInit(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public String loadJSONFromRaw(int jsonPath) {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(jsonPath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
        startActivity(new Intent(this, EventFormActivity.class));
    }
}