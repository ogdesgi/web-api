package com.esgi.events.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.helpers.FonctionsHelper;
import com.esgi.events.models.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sylvainvincent on 21/01/16.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private ArrayList<Event> eventArrayList;
    private Context context;

    public EventsListAdapter(ArrayList<Event> eventArrayList, Context context){
        this.eventArrayList = eventArrayList;
        this.context = context;
    }

    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event,parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(eventArrayList.get(position),context);
    }

    public void updateData(ArrayList<Event> eventArrayList) {
        this.eventArrayList.clear();
        this.eventArrayList.addAll(eventArrayList);
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
                         date;
        private ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.row_event_title);
            date = (TextView) itemView.findViewById(R.id.row_event_date);
            thumbnail = (ImageView) itemView.findViewById(R.id.row_event_thumbnail);

        }

        public void bind(Event event, Context context){
            title.setText(event.getTitle());
            date.setText(FonctionsHelper.dateToString(event.getDate()));
            Picasso.with(context).load(event.getPhotoPath())
                    .resize(140,100)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_panorama)
                    .into(thumbnail);
        }
    }

}
