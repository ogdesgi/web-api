package com.esgi.events.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.helpers.FonctionsHelper;
import com.esgi.events.models.Event;

import java.util.ArrayList;

/**
 * Created by sylvainvincent on 21/01/16.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private ArrayList<Event> eventArrayList;

    public EventsListAdapter(ArrayList<Event> eventArrayList){
        this.eventArrayList = eventArrayList;
    }

    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event,parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(eventArrayList.get(position));
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

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.row_event_title);
            date = (TextView) itemView.findViewById(R.id.row_event_date);

        }

        public void bind(Event event){
            title.setText(event.getTitle());
            date.setText(FonctionsHelper.dateToString(event.getDate()));
        }
    }

}
