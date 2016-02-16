package com.esgi.events.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.esgi.events.R;
import com.esgi.events.models.Category;
import com.esgi.events.models.Event;
import com.esgi.events.models.User;
import com.esgi.events.webservice.CategoryRestClient;
import com.esgi.events.webservice.EventRestClient;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by sylvainvincent on 16/01/16.
 */
public class EventFormActivity extends AppCompatActivity {

    public static final int PICK_PICTURE_CODE = 1;
    private FloatingActionButton addPictureButton;
    private ImageView image;
    private EditText titleField,
                     dateField,
                     descriptionField;
    private Spinner categoryEventSpinner;
    private Uri uri;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
        init();
        toolbarInit();
        token = getIntent().getStringExtra("token");


        Call<List<Category>> call = null;
        Response<List<Category>> response = null;
        try {
            call = new CategoryRestClient().getCategories();
            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Response<List<Category>> response, Retrofit retrofit) {
                    if(response.isSuccess()){

                        List<Category> list = response.body();
                        List<String> categoriesNames = null;
                        categoriesNames.add("Créer une nouvelle catégorie");
                        categoriesNames.add("Créer une nouvelle catégorie");
                        for(Category category :list){
                            categoriesNames.add(category.getName());
                        }
                        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<>(EventFormActivity.this,android.R.layout.activity_list_item,categoriesNames);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        categoryEventSpinner.setAdapter(arrayAdapter);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("fail", "onFailure: "+t.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == PICK_PICTURE_CODE){
                uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void init(){
        addPictureButton = (FloatingActionButton) findViewById(R.id.add_picture_button);
        titleField = (EditText) findViewById(R.id.title_edit_text);
        dateField = (EditText) findViewById(R.id.date_edit_text);
        descriptionField = (EditText) findViewById(R.id.description_edit_text);
        image = (ImageView) findViewById(R.id.event_picture);
        categoryEventSpinner = (Spinner) findViewById(R.id.category_spinner);
    }

    private void toolbarInit(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_event_button:
                if (!(titleField.getText().toString().equals("") &&
                        descriptionField.getText().toString().equals("") &&
                        dateField.getText().toString().equals(""))) {
                    Event event = new Event();
                    event.setTitle(titleField.getText().toString());
                    event.setDescription(descriptionField.getText().toString());
                    event.setDate(new Date());
                    event.setCreator(new User());
                    EventRestClient eventRestClient = new EventRestClient();
                    eventRestClient.createEvents(token, event);
                    setResult(RESULT_OK);
                    finish();
                }
                break;
            case R.id.add_picture_button:
               // File file = createNewFile();
               // uri = Uri.fromFile(file);
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               // intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, PICK_PICTURE_CODE);
                break;
        }

    }

    /*private File createNewFile(){
        String fileName = "test";
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/event";

        File file = new File(directory,fileName);
        file.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }*/

    /*private void createEvent(String title, String description, Date date,String photoPath){
        Realm realm = Realm.getInstance(this);

        realm.beginTransaction();

        Event event= realm.createObject(Event.class);
        event.setTitle(title);
        event.setDescription(description);
        event.setDate(date);
        event.setLogo(photoPath);

        realm.commitTransaction();
    }*/
}