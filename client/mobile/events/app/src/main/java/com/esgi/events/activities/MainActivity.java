package com.esgi.events.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.e.api.events.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private TextView title;

    private void init(){
        title = (TextView) findViewById(R.id.test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.init();

        String url="http://api.androidhive.info/volley/person_object.json";

        /*final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                title.setText("Response: " + jsonObject.toString());
                String textResult = "";
                try {
                    //JSONArray arrProducts = jsonObject.getJSONArray("products");
                    //for(int i=0; i<arrProducts.length(); i++){
                        //JSONObject productItem = (JSONObject)arrProducts.get(i);
                        textResult += "Name: " + jsonObject.getString("name") + "\n";
                        textResult += "Description: " + jsonObject.getString("email") + "\n";
                        textResult += "Price: " + jsonObject.getString("phone") + "\n\n";
                   // }
                    title.setText(textResult);
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

        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);*/

        try {
            JSONObject test = new JSONObject(loadJSONFromRaw(R.raw.exemple));
            JSONArray jsonArray = test.getJSONArray("user");
            JSONObject obj = (JSONObject) jsonArray.get(0);
            title.setText(obj.getString("lastname"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
}
