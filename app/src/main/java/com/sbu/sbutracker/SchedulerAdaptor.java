package com.sbu.sbutracker;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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

public class SchedulerAdaptor extends RecyclerView.Adapter<SchedulerAdaptor.ViewHolder> {
    private List<SchedulerClass> schedulerClassList;

    public void clear() {
        schedulerClassList.clear();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView schedulerDate, schedulerDay,scheduleText1,scheduleText2, scheduleText3;
        public String date;
        public ViewHolder(final View v) {
            super(v);
            v.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(v.getContext(), SchedulerMoreInfo.class);
                    intent.putExtra("parcel_data", date);
                    v.getContext().startActivity(intent);
                }
            });
            schedulerDate = v.findViewById(R.id.scheduleDate);
            schedulerDay = v.findViewById(R.id.scheduleDay);
            scheduleText1 = v.findViewById(R.id.scheduleText1);
            scheduleText2 = v.findViewById(R.id.scheduleText2);
            scheduleText3 = v.findViewById(R.id.scheduleText3);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public void add(List<SchedulerClass> notificationList) {
        this.schedulerClassList = notificationList;
    }

    public SchedulerAdaptor() {
        schedulerClassList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SchedulerAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        final View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scheduler_layout, parent, false);
        // set the view's size, margins, paddings and layout parameter
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        SchedulerClass schedulerClass = schedulerClassList.get(position);
        holder.date = schedulerClass.getSchedulerDate();
        holder.schedulerDate.setText(schedulerClass.getSchedulerDate());
        holder.schedulerDay.setText(schedulerClass.getSchedulerDay());
        holder.scheduleText1.setText(schedulerClass.getScheduleText1());
        holder.scheduleText2.setText(schedulerClass.getScheduleText2());
        holder.scheduleText3.setText(schedulerClass.getScheduleText3());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return schedulerClassList.size();
    }
}
