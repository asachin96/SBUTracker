package com.sbu.sbutracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sachin on 22-Nov-17.
 */

public class ListViewAdaptor extends RecyclerView.Adapter<ListViewAdaptor.ViewHolder> {
    private List<ActivityClass> activityList;

    public void clear() {
        activityList.clear();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView activityType, activityStartTime, activityDuration, activityDistance, activityPace, activityPeakPace;
        public ImageView activityThumbnail;
        public ActivityClass activityClass;
        public ViewHolder(final View v) {
            super(v);
            v.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(v.getContext(), ActivitySegmentMoreInfo.class);
                    intent.putExtra("parcel_data", activityClass);
                    v.getContext().startActivity(intent);
                }
            });
            activityThumbnail = v.findViewById(R.id.activityThumbnail);
            activityType = v.findViewById(R.id.activityType);
            activityStartTime = v.findViewById(R.id.activityStartTime);
            activityDuration = v.findViewById(R.id.activityDuration);
            activityDistance = v.findViewById(R.id.activityDistance);
            activityPace = v.findViewById(R.id.activityPace);
            activityPeakPace = v.findViewById(R.id.activityPeakPace);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public void add(ActivityClass activityList) {
        this.activityList.add(activityList);
    }

    public ListViewAdaptor() {
        activityList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        final View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        // set the view's size, margins, paddings and layout parameter
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ActivityClass activity = activityList.get(position);
        holder.activityClass = activity;
        Date date = new Date(activity.getActivityStartTime());
        SimpleDateFormat format = new SimpleDateFormat("MMM-dd  HH:mm");
        holder.activityStartTime.setText(format.format(date));
        long duration = (activity.getActivityEndTime() - activity.getActivityStartTime())/1000/60;
        holder.activityDuration.setText("Duration: "+ duration + " mins");
        double distance = (double)Math.round(activity.getActivityDistance()*100)/100;
        holder.activityDistance.setText("Distance: "+ distance + " km");
        double pace = (double)Math.round(activity.getActivityPace()*100)/100;
        holder.activityPace.setText("Pace: "+ pace + " kmph");
        double peak = (double) Math.round(activity.getActivityPeakPace()*100)/100;
        holder.activityPeakPace.setText("Peak: "+ peak + " kmph");
        int thumbnail = 0;
        String activityType = "";
        switch(activity.getActivityType()){
            case 0:
                thumbnail = R.drawable.notmoving;
                activityType= "Static";
                break;
            case 1:
                activityType = "Walking";
                thumbnail = R.drawable.walking;
                break;
            case 2:
                activityType= "Running";
                thumbnail = R.drawable.running;
                break;
            case 3:
                activityType= "Driving";
                thumbnail = R.drawable.driving;
                break;
        }
        holder.activityType.setText(activityType);
        holder.activityThumbnail.setImageResource(thumbnail);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return activityList.size();
    }
}
