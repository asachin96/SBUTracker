package com.sbu.sbutracker;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sachin on 22-Nov-17.
 */

public class NotificationAdaptor extends RecyclerView.Adapter<NotificationAdaptor.ViewHolder> {
    private List<NotificationClass> notificationList;

    public void clear() {
        notificationList.clear();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView notificationHeading, notificationText, notificationTime;
        public ViewHolder(final View v) {
            super(v);
            notificationHeading = v.findViewById(R.id.notificationHeading);
            notificationText = v.findViewById(R.id.notificationText);
            notificationTime = v.findViewById(R.id.notificationTime);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public void add(List<NotificationClass> notificationList) {
        this.notificationList = notificationList;
    }

    public NotificationAdaptor() {
        notificationList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NotificationAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        final View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_layout, parent, false);
        // set the view's size, margins, paddings and layout parameter
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NotificationClass notification = notificationList.get(position);
        Date date = new Date(notification.getNotificationTime());
        SimpleDateFormat format = new SimpleDateFormat("MMM-dd  HH:mm");
        holder.notificationTime.setText(format.format(date));
        holder.notificationHeading.setText(notification.getNotificationHeading());
        holder.notificationText.setText(notification.getNotificationText());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
