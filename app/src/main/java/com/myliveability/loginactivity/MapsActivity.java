package com.myliveability.loginactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.myliveability.loginactivity.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    FirebaseDatabase RootNode2;
    DatabaseReference reference2;

    double latitude,longitude,latitude2,longitude2;
    LatLng sydney;

    String result;

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

//         latitude = 55;
//         longitude = 66;
//
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(latitude,longitude);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("My location."));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17));
//        mMap.addCircle(new CircleOptions()
//                .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88008E22")).strokeWidth(5));

        FirebaseDatabase.getInstance().getReference().child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            latitude = snapshot.child("latitude").getValue(Float.class);
                            longitude = snapshot.child("longitude").getValue(Float.class);
                            result = snapshot.child("result").getValue(String.class);
                            LatLng sydney = new LatLng(latitude,longitude);

                            if(result.equals("dark"))
                            {
                                mMap.addCircle(new CircleOptions()
                                        .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88008E22")).strokeWidth(5));

                            }else if(result.equals("green"))
                            {
                                mMap.addCircle(new CircleOptions()
                                        .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#8868FA73")).strokeWidth(5));

                            }else if(result.equals("yellow"))
                            {

                                mMap.addCircle(new CircleOptions()
                                        .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88E4EE0F")).strokeWidth(5));

                            }else if(result.equals("orange"))
                            {


                                mMap.addCircle(new CircleOptions()
                                        .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88F49F0A")).strokeWidth(5));

                            }else
                            {

                                mMap.addCircle(new CircleOptions()
                                        .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88EC1C1C")).strokeWidth(5));

                            }




                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                //to fetch current user marker.
        SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
        String userid = preferences.getString("userID","");


         FirebaseDatabase.getInstance().getReference().child("users").orderByChild("userid").equalTo(userid)
       .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    latitude2 = snapshot.child(userid).child("latitude").getValue(Double.class);
                    longitude2 = snapshot.child(userid).child("longitude").getValue(Double.class);
                    LatLng sydney2 = new LatLng(latitude2,longitude2);
                    mMap.addMarker(new MarkerOptions().position(sydney2).title("My location."));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney2,17));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}