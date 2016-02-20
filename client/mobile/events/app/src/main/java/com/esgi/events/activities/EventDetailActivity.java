package com.esgi.events.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.adapters.EventsListAdapter;
import com.esgi.events.models.Category;
import com.esgi.events.models.Event;
import com.esgi.events.models.Events;
import com.esgi.events.models.User;
import com.esgi.events.webservice.CategoryRestClient;
import com.esgi.events.webservice.EventRestClient;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 27/01/16.
 */
public class EventDetailActivity extends AppCompatActivity {

    private static final String TAG = "EventDetailActivity";
    @Bind(R.id.event_picture)
    ImageView eventPicture;
    @Bind(R.id.event_title)
    TextView eventTitle;
    @Bind(R.id.event_description)
    TextView eventDescription;
    @Bind(R.id.event_date)
    TextView eventDate;
    @Bind(R.id.event_creator)
    TextView eventCreator;
    @Bind(R.id.scroll)
    NestedScrollView scroll;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.delete_event_button)
    FloatingActionButton deleteEventButton;
    @Bind(R.id.modify_event_button)
    FloatingActionButton modifyEventButton;
    @Bind(R.id.join_event_button)
    FloatingActionButton joinEventButton;
    @Bind(R.id.multiple_actions_event)
    FloatingActionsMenu multipleActionsEvent;
    @Bind(R.id.event_participant_button)
    Button eventParticipantButton;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.event_categorie)
    TextView eventCategorie;
    @Bind(R.id.coordinator_event_detail)
    CoordinatorLayout coordinatorEventDetail;

    private String eventId;
    private String userId;
    private List<String> participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);

        eventId = getIntent().getStringExtra(EventsListAdapter.PUT_EVENT_ID);
        userId = getIntent().getStringExtra("userId");


        EventRestClient eventRestClient = new EventRestClient();
        Log.e(TAG, "onCreate: " + eventId);
        Call<Events> call = eventRestClient.getEvent(eventId);
        call.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Response<Events> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.e(TAG, "success: " + response.message());
                    Event event = response.body().getProfile();
                    Log.e(TAG, "success: " + event.getDescription());

                    Picasso.with(EventDetailActivity.this).load(event.getLogo()).placeholder(R.drawable.background).into(eventPicture);
                    eventTitle.setText(event.getTitle());
                    eventDescription.setText(event.getDescription());
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    eventCategorie.setText(event.getCategoryName());
                    eventDate.setText(df.format(event.getDate()));
                    participants = event.getParticipantsNames();
                    if (event.getCreator().equals(userId)) {
                        modifyEventButton.setVisibility(View.VISIBLE);
                        deleteEventButton.setVisibility(View.VISIBLE);
                        eventCreator.setText("Moi");
                    }else{
                        eventCreator.setText(event.getCreatorName());
                        joinEventButton.setVisibility(View.VISIBLE);
                        for(String participantId : event.getParticipants()){
                            if(participantId.equals(userId)){
                                joinEventButton.setTitle("Se retirer");
                                break;
                            }
                        }
                    }


                } else {
                    Log.e(TAG, "no success: " + response.code());
                    Log.e(TAG, "no success: " + response.message());

                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });
        /*Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        Event event = realm.where(Event.class).equalTo("id", eventId).findFirst();
        Picasso.with(this).load(event.getLogo()).into(eventCover);
        eventTitle.setText(event.getTitle());*/

        /*title.setText(event.getTitle().toString());
        date.setText(event.getDate().toString());
        description.setText(event.getDescription());*/

    }

    public void onClick(View view) {
        EventRestClient eventRestClient;
        switch (view.getId()) {
            case R.id.delete_event_button:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                alertDialogBuilder
                        .setMessage("Êtes vous sûr de vouloir supprimer cette évènement ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EventRestClient eventRestClient = new EventRestClient();
                                Call<Events> call = eventRestClient.deleteEvent(LoginActivity.token, eventId);
                                call.enqueue(new Callback<Events>() {
                                    @Override
                                    public void onResponse(Response<Events> response, Retrofit retrofit) {
                                        if (response.isSuccess()) {
                                            setResult(RESULT_OK);
                                            finish();

                                        } else {
                                            if (response.code() == 403) {
                                                //Snackbar.make(coordinatorEventDetail,"Cette catégorie ne peut pas être supprimer car elle est lié à au moins un évenement",Snackbar.LENGTH_LONG).show();
                                            }
                                            Log.e(TAG, "onResponse: " + response.message());
                                            Log.e(TAG, "onResponse: " + response.code());
                                        }

                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        Log.e(TAG, "onFailure: " + t.getMessage());
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Non", null)
                        .create();

                alertDialogBuilder.show();
                break;
            case R.id.modify_event_button:

                break;
            case R.id.join_event_button:
                if(joinEventButton.getTitle().equals("Rejoindre")){
                    Log.e(TAG, "onClick: " + eventId );
                    User user = new User();
                    user.set_id(userId);
                    eventRestClient = new EventRestClient();
                    Call<Event> eventCall = eventRestClient.joinEvent(LoginActivity.token, eventId,user);
                    eventCall.enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Response<Event> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                Snackbar.make(coordinatorEventDetail,"Vous avez rejoint l'évènement",Snackbar.LENGTH_LONG).show();
                                joinEventButton.setTitle("Se retirer");
                            }else{
                                Log.e(TAG, "onResponse: " + response.message());
                                Log.e(TAG, "onResponse: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.e(TAG, "onFailure: " + t);
                            Log.e(TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }else{
                    eventRestClient = new EventRestClient();
                    User user = new User();
                    user.set_id(userId);
                    Call<Event> eventCall = eventRestClient.leaveEvent(LoginActivity.token, eventId, user);
                    eventCall.enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Response<Event> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                Snackbar.make(coordinatorEventDetail,"Vous avez quitté l'évènement",Snackbar.LENGTH_LONG).show();
                                joinEventButton.setTitle("Rejoindre");
                            }else{
                                Log.e(TAG, "onResponse: " + response.message());
                                Log.e(TAG, "onResponse: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.e(TAG, "onFailure: " + t );
                        }
                    });
                }

        }
    }

    @OnClick(R.id.event_participant_button)
    public void onClick() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Participants :");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(participants);
        /*arrayAdapter.add("Hardik");
        arrayAdapter.add("Archit");
        arrayAdapter.add("Jignesh");
        arrayAdapter.add("Umang");
        arrayAdapter.add("Gatti");*/

        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(EventDetailActivity.this);
                        builderInner.setMessage(strName);
                        builderInner.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builderInner.show();
                    }
                });
        builderSingle.show();
    }
}
