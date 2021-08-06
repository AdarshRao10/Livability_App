package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormSectionFiveSixSeven extends AppCompatActivity {

    //    Quality of Life
    SeekBar exiQuality,expQuality;
    TextView exiQualityVal,expQualityVal;
    float exiQualityVar,expQualityVar;

    //    Governance
    TextView exiGovtRespTime,expGovtRespTime;
    float exiGovtRespTimeVar,expGovtRespTimeVar;

    //    Natural Environment
    TextView availNatEnv,expNatEnv;
    float availNatEnvVar,expNatEnvVar;

    float rankQuality,rankGovt,rankNatEnv;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    Button btn_section567;

    Button logout;

    int max=10;

    private static final int loc_permission = 1;
    double lat,longitude1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_section_five_six_seven);

//        Quality of Life
        exiQuality=findViewById(R.id.exiQuality);
        expQuality=findViewById(R.id.expQuality);

        exiQualityVal=findViewById(R.id.exiQualityVal);
        expQualityVal=findViewById(R.id.expQualityVal);

//        Governance
        exiGovtRespTime=findViewById(R.id.exiGovtRespTime);
        expGovtRespTime=findViewById(R.id.expGovtRespTime);

//        Natural Env
        availNatEnv=findViewById(R.id.availNatEnv);
        expNatEnv=findViewById(R.id.expNatEnv);

        btn_section567=findViewById(R.id.btn_section567);
        logout=findViewById(R.id.logout);


        exiQuality.setMax(max);
        exiQuality.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                exiQualityVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        expQuality.setMax(max);
        expQuality.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                expQualityVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_section567.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Quality Of Life
                exiQualityVar= Float.parseFloat(exiQualityVal.getText().toString());
                expQualityVar= Float.parseFloat(expQualityVal.getText().toString());

//                Governance
                exiGovtRespTimeVar= Float.parseFloat(exiGovtRespTime.getText().toString());
                expGovtRespTimeVar=Float.parseFloat(expGovtRespTime.getText().toString());

//                NaturalEnv
                availNatEnvVar=Float.parseFloat(availNatEnv.getText().toString());
                expNatEnvVar=Float.parseFloat(expNatEnv.getText().toString());

                rankQuality=(exiQualityVar-expQualityVar)/100;
                rankGovt=(exiGovtRespTimeVar-expGovtRespTimeVar)/100;
                rankNatEnv=(availNatEnvVar-expNatEnvVar)/100;

                //get data from shared preference
                SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
                String s1 = sh.getString("fname", "");
                String s2 = sh.getString("id", "");


                RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                reference = RootNode.getReference("users");

                SectionFiveSixSevenHelper sectionFiveSixSevenHelper = new SectionFiveSixSevenHelper(exiQualityVar,expQualityVar,exiGovtRespTimeVar,expGovtRespTimeVar,availNatEnvVar,expNatEnvVar);

                reference.child(s1).child("section5").setValue(sectionFiveSixSevenHelper);
                Toast.makeText(getApplicationContext(), "Section 5 complete", Toast.LENGTH_SHORT).show();

//                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankQuality, Toast.LENGTH_SHORT).show();
//                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankGovt, Toast.LENGTH_SHORT).show();
//                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankNatEnv, Toast.LENGTH_SHORT).show();




            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    ActivityCompat.requestPermissions(FormSectionFiveSixSeven.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            loc_permission);
                } else {
                    //permission granted
                    GetCurrentLocation();
                }



//                SharedPreferences preferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
//                SharedPreferences.Editor editor=preferences.edit();
//
//                editor.remove("email");
//                editor.clear();
//                editor.commit();


            }
        });

    }

    private void GetCurrentLocation() {

        LocationRequest locationReq = new LocationRequest();
        locationReq.setInterval(10000);
        locationReq.setFastestInterval(3000);
        locationReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        LocationServices.getFusedLocationProviderClient(FormSectionFiveSixSeven.this)
                .requestLocationUpdates(locationReq, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(FormSectionFiveSixSeven.this).removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocatioIndex = locationResult.getLocations().size() - 1;
                            lat = locationResult.getLocations().get(latestLocatioIndex).getLatitude();
                            longitude1 = locationResult.getLocations().get(latestLocatioIndex).getLongitude();
//                            latitude.setText(String.format("%s", lat));
//                            longitude.setText(String.format("%s",longitude1));

                            Toast.makeText(getApplicationContext()," "+lat+" "+longitude1, Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                            intent.putExtra("latitude",lat);
                            intent.putExtra("longitude",longitude1);
                            startActivity(intent);
                        }
                    }
                }, Looper.getMainLooper());


    }

}