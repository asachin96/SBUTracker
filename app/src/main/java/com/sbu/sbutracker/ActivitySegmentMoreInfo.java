package com.sbu.sbutracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sachin on 25-Nov-17.
 */

public class ActivitySegmentMoreInfo extends Activity implements OnMapReadyCallback {
    ActivityClass activityClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_more_info);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        activityClass = intent.getParcelableExtra("parcel_data");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        PolylineOptions polylineOptions = new PolylineOptions();
        int size = activityClass.getLatitudeList().size();
        for(int i=0; i<size;i++){
            LatLng latLng= new LatLng(activityClass.getLatitudeList().get(i),activityClass.getLongitudeList().get(i));
            polylineOptions.add(latLng);
        }
        googleMap.addPolyline(polylineOptions
                .width(10)
                .color(Color.rgb(0, 51, 204)));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(activityClass.getLatitudeList().get(size-1), activityClass.getLongitudeList().get(size-1))).zoom(14).build();

        BitmapDescriptor startIcon = BitmapDescriptorFactory.fromResource(R.drawable.start);
        BitmapDescriptor endIcon = BitmapDescriptorFactory.fromResource(R.drawable.stop);
        Date date = new Date(activityClass.getActivityEndTime());
        SimpleDateFormat format = new SimpleDateFormat("MMM-dd  HH:mm");
        LocationContext locationContext = new LocationContext();
        String startLocation = locationContext.getClosestLocation(activityClass.getLatitudeList().get(size-1), activityClass.getLongitudeList().get(size-1));
        String endLocation = locationContext.getClosestLocation(activityClass.getLatitudeList().get(0), activityClass.getLongitudeList().get(0));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(activityClass.getLatitudeList().get(0), activityClass.getLongitudeList().get(0)))
                .title(endLocation)
                .icon(endIcon)
                .snippet(format.format(date))
                );
        date = new Date(activityClass.getActivityStartTime());
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(activityClass.getLatitudeList().get(size-1), activityClass.getLongitudeList().get(size-1)))
                .title(startLocation)
                .icon(startIcon)
                .snippet(format.format(date))
        );
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
