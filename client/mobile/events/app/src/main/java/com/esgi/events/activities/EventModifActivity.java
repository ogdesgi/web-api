package com.esgi.events.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.esgi.events.R;
import com.esgi.events.helpers.FonctionsHelper;
import com.esgi.events.models.Categories;
import com.esgi.events.models.Category;
import com.esgi.events.models.Event;
import com.esgi.events.models.User;
import com.esgi.events.webservice.CategoryRestClient;
import com.esgi.events.webservice.EventRestClient;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by sylvainvincent on 16/01/16.
 */
public class EventModifActivity extends AppCompatActivity {

    public static final int PICK_PICTURE_CODE = 1;
    private final String TAG = getClass().getSimpleName();
    private FloatingActionButton addPictureButton;
    private CoordinatorLayout coordinatorLayoutEventForm;
    private ImageView image;
    private EditText titleField,
            dateField,
            descriptionField;
    private Spinner categoryEventSpinner;
    private Uri uri;
    private String token;
    private String userId;
    private String eventId;
    private String photoPath;
    private List<String> categoriesNames;
    private List<Category> categoryList;
    private Button modifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
        init();
        toolbarInit();
        token = getIntent().getStringExtra("token");
        userId = getIntent().getStringExtra("userId");
        eventId = getIntent().getStringExtra("eventId");
        descriptionField.setText(getIntent().getStringExtra("eventDescription"));
        titleField.setText(getIntent().getStringExtra("eventTitle"));
        dateField.setText(getIntent().getStringExtra("eventDate"));

        modifyButton.setText("MODIFIER");
        categoriesNames = new ArrayList<String>();
        Call<Categories> call = new CategoryRestClient().getCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Response<Categories> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Categories list = response.body();
                    categoryList = list.getCategoryList();

                    for (Category category : categoryList) {
                        categoriesNames.add(category.getName());
                        Log.e(TAG, "onResponse: " + category.get_id() );
                    }

                } else {
                    categoriesNames.add("Créer une catégorie");
                    categoryEventSpinner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EventModifActivity.this, CategoryFormActivity.class);
                            intent.putExtra("token", token);
                            startActivity(intent);
                        }
                    });
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EventModifActivity.this, android.R.layout.simple_list_item_1, categoriesNames);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categoryEventSpinner.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("fail", "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == PICK_PICTURE_CODE){
                final Uri uri = data.getData();
                photoPath = getImagePath(uri);
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
        coordinatorLayoutEventForm = (CoordinatorLayout) findViewById(R.id.coordinator_event_form);
        modifyButton = (Button) findViewById(R.id.add_event_button);
    }

    private void toolbarInit(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_event_button:
                if (isValid()) {
                    final Event event = new Event();
                    event.setTitle(titleField.getText().toString());
                    event.setDescription(descriptionField.getText().toString());
                    Date date = FonctionsHelper.StringToDate(dateField.getText().toString());
                    event.setDate(date);
                    Log.e(TAG, "onClick: " + categoryList.get(categoryEventSpinner.getSelectedItemPosition()).get_id());
                    Log.e(TAG, "onClick: " + categoryList.get(categoryEventSpinner.getSelectedItemPosition()).getName());
                    event.setCategory(categoryList.get(categoryEventSpinner.getSelectedItemPosition()).getName());
                    event.setCreator(userId);
                    RealmList<User> users = new RealmList<>();
                    users.add(new User(userId));
                    EventRestClient eventRestClient = new EventRestClient();
                    File file = null;
                    RequestBody requestBody = null;
                    if(photoPath != null){
                        file = new File(photoPath);
                        if(!file.exists()) {
                            ContextCompat.getDrawable(this, R.drawable.meeting);

                        }else{
                            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                        }
                    }

                    Call<Event> call = eventRestClient.updateEvent(token, eventId, requestBody, event);
                    call.enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Response<Event> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                setResult(RESULT_OK);
                                finish();
                            } else {
                                if(response.code() == 403){
                                    Snackbar.make(coordinatorLayoutEventForm, "Cette évènement existe déjà", Snackbar.LENGTH_LONG).show();
                                }else{
                                    Log.e(TAG, "no success: " + event.getCategory());
                                    Log.e(TAG, "no success: " + response.code());
                                    Log.e(TAG, "no success: " + response.message());
                                    Snackbar.make(coordinatorLayoutEventForm, "Une erreur s'est produite lors la création de l'événement", Snackbar.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Snackbar.make(coordinatorLayoutEventForm, "Problème au niveau du serveur, Veuillez réessayer plus tard", Snackbar.LENGTH_LONG).show();
                            Log.e(TAG, "onFailure: " + t);
                        }
                    });
                }else{
                    Snackbar.make(coordinatorLayoutEventForm, "Tous les champs sont obligatoires", Snackbar.LENGTH_LONG).show();
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

    private boolean isValid() {
        boolean isValid = false;
        if (!titleField.getText().toString().equals("")
                && !descriptionField.getText().toString().equals("")
                && !dateField.getText().toString().equals("")) {
            isValid = true;
        }
        return isValid;
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

    public String getImagePath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}