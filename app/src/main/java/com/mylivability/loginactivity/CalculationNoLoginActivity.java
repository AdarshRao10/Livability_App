package com.mylivability.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CalculationNoLoginActivity extends AppCompatActivity implements LocationListener {



    Button goToMaps,goToGraphs,backToLogin;
    int countGreen=0,countBlue=0,countRed=0;

    String res;

    LocationManager locationManager;

    double arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_no_login);

        Bundle extras = getIntent().getExtras();
        arr = extras.getDoubleArray("array");

        goToMaps=findViewById(R.id.goToMaps);
        goToGraphs=findViewById(R.id.goToGraphs);
        backToLogin=findViewById(R.id.backToLogin);



        for(double i : arr)
        {
            if(i>=0.26)
            {
                countGreen++;
            }else if(i>=-0.25 && i<=0.25)
            {
                countBlue++;
            }else
            {
                countRed++;
            }

        }


        if(countGreen==6)
        {
            Toast.makeText(getApplicationContext(), "Dark Green", Toast.LENGTH_SHORT).show();
            res="dark";
        }else if( (countGreen +countBlue)==5)
        {
            Toast.makeText(getApplicationContext(), "Light Green", Toast.LENGTH_SHORT).show();
            res="green";
        }else if(((countGreen +countBlue)==3) || ((countGreen +countBlue)==4))
        {
            Toast.makeText(getApplicationContext(), "yellow", Toast.LENGTH_SHORT).show();
            res="yellow";
        }else if(((countGreen +countBlue)==2))
        {
            Toast.makeText(getApplicationContext(), "orange", Toast.LENGTH_SHORT).show();
            res="orange";
        }else
        {
            Toast.makeText(getApplicationContext(), "red", Toast.LENGTH_SHORT).show();
            res="red";
        }



        goToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(ContextCompat.checkSelfPermission(CalculationNoLoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(CalculationNoLoginActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(CalculationNoLoginActivity.this,new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
                    },100);
                }else{
                    getLocation();
                }


            }
        });

        goToGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BarChartNoLogin.class);
                intent.putExtra("array",arr);
                startActivity(intent);
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {


        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,5000,CalculationNoLoginActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        double lat = location.getLatitude();
        double lng= location.getLongitude();

        Intent intent=new Intent(getApplicationContext(),MapsNoLoginActivity.class);
        intent.putExtra("result",res);
        intent.putExtra("latitude",lat);
        intent.putExtra("longitude",lng);
        startActivity(intent);
    }
}