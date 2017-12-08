package com.sbu.sbutracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachin on 05-Dec-17.
 */

public class Notification extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    NotificationAdaptor notificationAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.notification_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        notificationAdaptor = new NotificationAdaptor();
        mRecyclerView.setLayoutManager(mLayoutManager);
        refresh();
    }

    public void refresh(){
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        notificationAdaptor.add(dbHelper.getAllUnseenNotification());
        mRecyclerView.setAdapter(notificationAdaptor);
    }
}
