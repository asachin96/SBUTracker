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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by Sachin on 25-Nov-17.
 */

public class ActivitySegmentMoreInfo extends Activity implements OnMapReadyCallback {
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
        ActivityClass activity = intent.getParcelableExtra("parcel_data");
        Log.d("inside", "onCreate: "+activity.getActivityDistance());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Polyline line = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(40.912039, -73.121992), new LatLng(40.907725, -73.109890))
                .width(5)
                .color(Color.RED));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(40.912039, -73.121992)).zoom(10).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
