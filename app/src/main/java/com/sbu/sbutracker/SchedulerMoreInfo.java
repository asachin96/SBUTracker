package com.sbu.sbutracker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Sachin on 25-Nov-17.
 */

public class SchedulerMoreInfo extends Activity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback  {
    String type;

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
        type = intent.getStringExtra("parcel_data");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LocationContext locationContext = new LocationContext();
        CameraPosition cameraPosition;
        PolylineOptions polylineOptions = new PolylineOptions();

        if (Objects.equals(type, "08")) {
            String location = "Javtis Center";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 13:00")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue())).zoom(17).build();
        }else if(Objects.equals(type, "09")){
            String location = "Aldi's";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 11:23")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            location = "Athletics Field";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 17:30")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue())).zoom(17).build();
        }else if(Objects.equals(type, "10")){
            String location = "Chapin Commons";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 17:04")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            location = "Chapin Commons";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 19:13")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue())).zoom(17).build();
        }else if(Objects.equals(type, "11")){
            String location = "Javtis Center";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 13:03")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            location = "Javtis Center";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 19:06")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue())).zoom(17).build();
        }else if(Objects.equals(type, "12")){
            String location = "NCS Building";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 10:03")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            location = "Javtis Center";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 16:02")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue())).zoom(17).build();
        }else if(Objects.equals(type, "13")){
            String location = "Javtis Center";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 19:03")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue())).zoom(17).build();
        }else{
            String location = "NCS Building";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 10:03")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            location = "Javtis Center";
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()))
                    .title(location)
                    .snippet("At 16:02")
            );
            polylineOptions.add(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue()));
            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((double) locationContext.getCoordinates(location).getKey(), (double) locationContext.getCoordinates(location).getValue())).zoom(17).build();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        polylineOptions.add(new LatLng(latitude,longitude));
        googleMap.addPolyline(polylineOptions
                .width(10)
                .color(Color.rgb(0, 51, 204)));
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }
}
