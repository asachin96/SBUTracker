package com.sbu.sbutracker;

import android.util.Log;

import java.security.PublicKey;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Sachin on 02-Dec-17.
 */

public class LocationContext {
    HashMap<Map.Entry<Double,Double>,String> locationToName;

    public LocationContext(){
        locationToName = new HashMap<>();
        //hard code values here
        locationToName.put(new SimpleEntry<>(40.907379, -73.107893),"Chapin L");
        locationToName.put(new SimpleEntry<>(40.908074, -73.110342),"Chapin Commons");
        locationToName.put(new SimpleEntry<>(40.908407, -73.111608),"Chapin Bus Stand");
        locationToName.put(new SimpleEntry<>(40.909092, -73.114488),"Stonybrook Hospital");
        locationToName.put(new SimpleEntry<>(40.914378, -73.124960),"SAC Bus Stop");
    }
    public String getClosestLocation(double lat, double lon){
        Iterator it = locationToName.entrySet().iterator();
        double dist = 1000;
        String retVal = "Unknown";
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            double lt = ((Map.Entry<Double, Double>) pair.getKey()).getKey();
            double ln = ((Map.Entry<Double, Double>) pair.getKey()).getValue();
            double t = distFrom(lt, ln, lat, lon);
            Log.d("loc", "getClosestLocation: "+t);
            if(t<dist){
                dist = t;
                retVal = (String) pair.getValue();
            }
        }
        return retVal;
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
}
