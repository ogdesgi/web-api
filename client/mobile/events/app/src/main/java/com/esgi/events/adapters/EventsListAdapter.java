package com.esgi.events.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.activities.EventDetailActivity;
import com.esgi.events.activities.LoginActivity;
import com.esgi.events.models.Event;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sylvainvincent on 21/01/16.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private static final String TAG = "EventsListAdapter";
    private ArrayList<Event> eventArrayList;
    private Context context;
    public static final String PUT_EVENT_ID = "eventId";
    public static String userId;


    public EventsListAdapter(ArrayList<Event> eventArrayList, Context context, String userId){
        this.eventArrayList = eventArrayList;
        this.context = context;
        this.userId = userId;
    }

    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event,parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(eventArrayList.get(position),context);
    }

    public void updateData(ArrayList<Event> arrayList) {
        if (arrayList != null) {
            eventArrayList.clear();
            eventArrayList.addAll(arrayList);
        }
        else {
            Log.e(TAG, "updateData: arraylist null" );
            eventArrayList = arrayList;
        }
        notifyDataSetChanged();
    }

    public void addItem(int position, Event event) {
        this.eventArrayList.add(position, event);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        this.eventArrayList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,
                         date,
                         author;
        private ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.row_event_title);
            date = (TextView) itemView.findViewById(R.id.row_event_date);
            thumbnail = (ImageView) itemView.findViewById(R.id.row_event_thumbnail);
            author = (TextView) itemView.findViewById(R.id.row_event_author);

        }

        public void bind(final Event event, final Context context){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick: " + event.get_id());
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(PUT_EVENT_ID, event.get_id());
                    intent.putExtra("userId", userId);
                    ((Activity) context).startActivityForResult(intent, 1);
                }
            });
            title.setText(event.getTitle());
            Calendar cal = Calendar.getInstance();
            cal.setTime(event.getDate());
            cal.add(Calendar.DATE, 1);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            date.setText(df.format(cal.getTime()));
            author.setText("Organisé par " + event.getCreatorName());
            //date.setText(FonctionsHelper.dateToString(event.getDate()));
            Picasso.with(context).load(event.getLogo())
                    .resize(140,100)
                    .centerCrop()
                    .placeholder(R.drawable.background)
                    .into(thumbnail);
        }
    }

}
