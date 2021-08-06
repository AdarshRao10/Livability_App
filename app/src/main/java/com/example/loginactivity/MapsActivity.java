package com.example.loginactivity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.loginactivity.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude",0);
        double longitude = intent.getDoubleExtra("longitude",0);
//        double latitude = 55;
//        double longitude = 66;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude,longitude);
//        LatLng sydney2 = new LatLng(19.161161070620732, 73.2285761742803);
//        LatLng sydney3 = new LatLng(19.159792914399148, 73.2287371068203);
//        LatLng sydney4 = new LatLng(19.158404477591738, 73.22645186475235);
//        LatLng sydney5 = new LatLng(19.161181339516467, 73.23032497454828);

        mMap.addMarker(new MarkerOptions().position(sydney).title("My location."));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17));
        mMap.addCircle(new CircleOptions()
                .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88008E22")).strokeWidth(5));
//        mMap.addCircle(new CircleOptions()
//                .center(sydney2).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#8868FA73")).strokeWidth(5));
//        mMap.addCircle(new CircleOptions()
//                .center(sydney3).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88E4EE0F")).strokeWidth(5));
//        mMap.addCircle(new CircleOptions()
//                .center(sydney4).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88F49F0A")).strokeWidth(5));
//        mMap.addCircle(new CircleOptions()
//                .center(sydney5).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88EC1C1C")).strokeWidth(5));
        //dark green = good 22 is for tranperency #22179E21
        //light green = ok #2268FA73
        //yellow = moderate #22E4EE0F
        //orange = bad #22F49F0A
        //red = worst #22EC1C1C
    }
}