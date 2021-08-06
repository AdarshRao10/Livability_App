package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class Home extends AppCompatActivity {

    TextView fname,lname,email,age,gender,latitude,longitude;
    Button btnSubmit;
    private static final int loc_permission = 1;
    double lat,longitude1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        gender =findViewById(R.id.gender);
        latitude =findViewById(R.id.latitude);
        longitude =findViewById(R.id.longitude);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("fname", "");
        String s2 = sh.getString("lname", "");
        String s3 = sh.getString("email", "");
        String s4 = sh.getString("age", "");
        String s5 = sh.getString("gender", "");


// We can then use the data
        fname.setText(s1);
        lname.setText(s2);
        email.setText(s3);
        age.setText(s4);
        gender.setText(s5);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    ActivityCompat.requestPermissions(Home.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            loc_permission);
                } else {
                    //permission granted
                    GetCurrentLocation();
                }
            }
        });

    }

    private void GetCurrentLocation() {

        LocationRequest locationReq = new LocationRequest();
        locationReq.setInterval(10000);
        locationReq.setFastestInterval(3000);
        locationReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        LocationServices.getFusedLocationProviderClient(Home.this)
                .requestLocationUpdates(locationReq, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(Home.this).removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocatioIndex = locationResult.getLocations().size() - 1;
                            lat = locationResult.getLocations().get(latestLocatioIndex).getLatitude();
                            longitude1 = locationResult.getLocations().get(latestLocatioIndex).getLongitude();
                            latitude.setText(String.format("%s", lat));
                            longitude.setText(String.format("%s",longitude1));

                            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                            intent.putExtra("latitude",lat);
                            intent.putExtra("longitude",longitude1);
                            startActivity(intent);
                        }
                    }
                }, Looper.getMainLooper());


    }


}