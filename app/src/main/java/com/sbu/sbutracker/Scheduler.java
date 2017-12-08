package com.sbu.sbutracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachin on 07-Dec-17.
 */

public class Scheduler extends Activity{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    SchedulerAdaptor schedulerAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        schedulerAdaptor = new SchedulerAdaptor();
        mRecyclerView.setLayoutManager(mLayoutManager);
        refresh();
    }

    private void refresh() {
        //setting dummy values
        List<SchedulerClass> list = new ArrayList<>();
        SchedulerClass scheduler = new SchedulerClass("08", "Fri", "Javtis Center at 12:57","","");
        list.add(scheduler);
        scheduler = new SchedulerClass("09", "Sat", "Aldi's at 11:23","Athletics Field at 17:30","");
        list.add(scheduler);
        scheduler = new SchedulerClass("10", "Sun", "Chapin Commons at 17:04","Chapin Commons at 19:13","");
        list.add(scheduler);
        scheduler = new SchedulerClass("11", "Mon", "Javtis Center at 13:03","Javtis Center at 19:06","");
        list.add(scheduler);
        scheduler = new SchedulerClass("12", "Tue", "NCS Building at 09:58","Javtis Center at 16:02","");
        list.add(scheduler);
        scheduler = new SchedulerClass("13", "Wed", "Javtis Center at 19:03","","");
        list.add(scheduler);
        scheduler = new SchedulerClass("14", "Thu", "NCS Building at 10:03","Javtis Center at 16:02","");
        list.add(scheduler);
        schedulerAdaptor.add(list);
        mRecyclerView.setAdapter(schedulerAdaptor);
    }
}
