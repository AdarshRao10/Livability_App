package com.example.loginactivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.loginactivity.databinding.ActivityMapsNoLoginBinding;

public class MapsNoLoginActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsNoLoginBinding binding;

double lat,lng;
String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsNoLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lat=getIntent().getDoubleExtra("latitude",0);
        lng=getIntent().getDoubleExtra("longitude",0);
        result=getIntent().getStringExtra("result");








    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng latlng = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(latlng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));


        if(result.equals("dark"))
        {
            mMap.addCircle(new CircleOptions()
                    .center(latlng).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88008E22")).strokeWidth(5));

        }else if(result.equals("green"))
        {
            mMap.addCircle(new CircleOptions()
                    .center(latlng).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#8868FA73")).strokeWidth(5));

        }else if(result.equals("yellow"))
        {

            mMap.addCircle(new CircleOptions()
                    .center(latlng).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88E4EE0F")).strokeWidth(5));

        }else if(result.equals("orange"))
        {


            mMap.addCircle(new CircleOptions()
                    .center(latlng).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88F49F0A")).strokeWidth(5));

        }else
        {

            mMap.addCircle(new CircleOptions()
                    .center(latlng).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88EC1C1C")).strokeWidth(5));

        }
    }
}