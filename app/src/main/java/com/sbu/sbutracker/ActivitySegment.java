package com.sbu.sbutracker;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachin on 23-Nov-17.
 */

class ActivitySegment {
    private RecyclerView recyclerView;
    private FeedReaderDbHelper dbHelper;
    ListViewAdaptor listViewAdaptor;
    private long staticThreshold = 60 * 1000; //min no of seconds after which we tell person is static
    static int lastSize = -1;

    public ActivitySegment(RecyclerView mRecyclerView, FeedReaderDbHelper mdbHelper, ListViewAdaptor mAdapter) {
        recyclerView = mRecyclerView;
        dbHelper = mdbHelper;
        listViewAdaptor = mAdapter;
    }

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (earthRadius * c) / 10;

        return dist;
    }

    public void refresh(Boolean isPastSevenData) {
        List<DataTable> locationList;
        if(!isPastSevenData)
            locationList = dbHelper.getTodayRecord();
        else
            locationList = dbHelper.getSevenDaysRecords();
        if (lastSize == locationList.size()) {
            //no update don't refresh
//            Log.d("refresh", "no update");
            return;
        }
        if (locationList.size() == 0) {
            //no records
            //TODO display default welcome message
            Log.d("bad", "empty");
            return;
        }
        Log.d("refresh", "size" + locationList.size());
        listViewAdaptor.clear();
        lastSize = locationList.size();
        int anchor = 0;
        boolean cardShown = false;
        for (int i = 1; i < locationList.size(); i++) {
            if (locationList.get(i - 1).getTimestamp() - locationList.get(i).getTimestamp() > staticThreshold) {
                cardShown = true;
                //i-1 is the last entry in the activity
                DataTable firstEntry = locationList.get(i - 1);
                DataTable lastEntry = locationList.get(anchor+1);
                double distance = 0;
                ArrayList<Double> longitudeList = new ArrayList<>();
                ArrayList<Double> latitudeList = new ArrayList<>();
                double peakPace = 0;
                double tt =0;
                for (int j = anchor + 2; j <= i - 1; j++) {
                    double tempDist = distFrom(locationList.get(j - 1).getLattitude(), locationList.get(j - 1).getLongitude(), locationList.get(j).getLattitude(), locationList.get(j).getLongitude());
                    double tempTime = (locationList.get(j - 1).getTimestamp() - locationList.get(j).getTimestamp())/1000;
                    double tempPace = tempDist*3.6/tempTime;
                    distance += tempDist;
                    if(tempPace<80) {
                        peakPace = Math.max(peakPace, tempPace);
                        tt+=tempTime;
                        longitudeList.add(locationList.get(j - 1).getLattitude());
                        latitudeList.add(locationList.get(j - 1).getLongitude());
                    }
                }
                double time = (lastEntry.getTimestamp() - firstEntry.getTimestamp()) / 1000;
                if (distance < 100 || time < 60) {
                    anchor = i - 1;
                    continue;
                }
                double pace = distance * 3.6 / time;
                int activityType;
                if (peakPace < 10) activityType = 1; //walking
                else if ( peakPace < 25) activityType = 2; //running
                else activityType = 3; //driving

                ActivityClass activityClass = new ActivityClass();
                activityClass.setActivityType(activityType);
                activityClass.setActivityStartTime(firstEntry.getTimestamp());
                activityClass.setActivityDistance(distance / 1000);
                activityClass.setActivityPace(pace);
                activityClass.setActivityEndTime(lastEntry.getTimestamp());
                activityClass.setLatitudeList(longitudeList);
                activityClass.setLongitudeList(latitudeList);
                activityClass.setActivityPeakPace(peakPace);
                listViewAdaptor.add(activityClass);
                anchor = i;
            }
        }
        recyclerView.setAdapter(listViewAdaptor);
    }
}
