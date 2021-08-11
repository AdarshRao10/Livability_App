package com.example.loginactivity;
import static java.lang.Float.parseFloat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class FormSectionOne extends AppCompatActivity {

    EditText et_waterAvailabity,et_waterExpected,et_electricityAvailablity,et_electricityExpected,et_cost_health_public_existing,et_cost_health_public_expected,et_cost_health_private_existing,et_cost_health_private_expected,et_cost_renting_existing,et_cost_renting_expected;
    TextView sb_sanitationAvailableVar,sb_sanitationExpectedVar;
    SeekBar sb_sanitationAvailable,sb_sanitationExpected;
    Button btn_section1_next;
    float waterAvailability,waterExpected,electricityAvailability,electricityExpected,cost_health_public_existing,cost_health_public_expected,cost_health_private_existing,cost_health_private_expected,cost_renting_existing,cost_renting_expected, sanitationAvailable,sanitationExpected;

    double rankWater,rankElectricity,rankSanitation,rankPublicHealth,rankPrivateHealth,rankHousing;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    private static final int loc_permission = 1;
    double lat,longitude1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_section_one);

        et_electricityExpected=findViewById(R.id.et_electricityExpected);
        et_waterAvailabity=findViewById(R.id.et_waterAvailabity);
        et_waterExpected=findViewById(R.id.et_waterExpected);
        et_electricityAvailablity=findViewById(R.id.et_electricityAvailablity);
        et_cost_health_private_existing=findViewById(R.id.et_cost_health_private_existing);
        et_cost_health_public_existing=findViewById(R.id.et_cost_health_public_existing);
        et_cost_health_public_expected=findViewById(R.id.et_cost_health_public_expected);
        et_cost_health_private_expected=findViewById(R.id.et_cost_health_private_expected);
        et_cost_renting_existing=findViewById(R.id.et_cost_renting_existing);
        et_cost_renting_expected=findViewById(R.id.et_cost_renting_expected);
        sb_sanitationAvailable=findViewById(R.id.sb_sanitationAvailable);
        sb_sanitationExpected=findViewById(R.id.sb_sanitationExpected);
        btn_section1_next=findViewById(R.id.btn_section1_next);
        sb_sanitationAvailableVar=findViewById(R.id.sb_sanitationAvailableVar);
        sb_sanitationExpectedVar=findViewById(R.id.sb_sanitationExpectedVar);


        sb_sanitationAvailable.setMax(10);
        sb_sanitationExpected.setMax(10);


        sb_sanitationAvailable.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue =0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue=progress;
                sb_sanitationAvailableVar.setText(String.valueOf(progressChangedValue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Value set: "+progressChangedValue, Toast.LENGTH_SHORT).show();


            }
        });

        sb_sanitationExpected.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue =0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue=progress;
                sb_sanitationExpectedVar.setText(String.valueOf(progressChangedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(), "Value set: "+progressChangedValue, Toast.LENGTH_SHORT).show();


            }
        });

        btn_section1_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Onclick", Toast.LENGTH_SHORT).show();


                if(validateform())
                {
                    sanitationAvailable= Float.parseFloat(sb_sanitationAvailableVar.getText().toString());
                    sanitationExpected= Float.parseFloat(sb_sanitationExpectedVar.getText().toString());
                    waterAvailability = parseFloat(et_waterAvailabity.getText().toString());
                    waterExpected = parseFloat(et_waterExpected.getText().toString());
                    electricityAvailability= parseFloat(et_electricityExpected.getText().toString());
                    electricityExpected= parseFloat(et_electricityExpected.getText().toString());
                    cost_health_public_existing= parseFloat(et_cost_health_public_existing.getText().toString());
                    cost_health_private_existing= parseFloat(et_cost_health_private_existing.getText().toString());
                    cost_health_private_expected= parseFloat(et_cost_health_private_expected.getText().toString());
                    cost_health_public_expected= parseFloat(et_cost_health_public_expected.getText().toString());
                    cost_renting_existing= parseFloat(et_cost_renting_existing.getText().toString());
                    cost_renting_expected= parseFloat(et_cost_renting_expected.getText().toString());




                    rankWater= (waterAvailability-waterExpected)/100;
                    rankElectricity = (electricityAvailability-electricityExpected)/100;
                    rankHousing =(cost_renting_expected-cost_renting_existing)/100;
                    rankPublicHealth =(cost_health_public_expected-cost_health_public_existing)/100;
                    rankPrivateHealth =(cost_health_private_expected-cost_health_public_expected)/100;
                    rankSanitation=(float)(sanitationAvailable-sanitationExpected)/100;

                    //get data from shared preference
                    // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
                    SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                    String userID = preferences.getString("userID", "");

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
                   // String s1 = sh.getString("fname", "");


                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    SectionOneHelper sectionOneHelper = new SectionOneHelper(sanitationAvailable,sanitationExpected, waterAvailability,  waterExpected,  electricityAvailability,  electricityExpected, cost_health_public_existing, cost_health_public_expected,  cost_health_private_existing,  cost_health_private_expected,  cost_renting_existing, cost_renting_expected);

                    reference.child(userID).child("section1").setValue(sectionOneHelper);

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted
                        ActivityCompat.requestPermissions(FormSectionOne.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                loc_permission);
                    } else {
                        //permission granted
                        GetCurrentLocation();
                    }

                    Intent intent = new Intent(getApplicationContext(),CalculationActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }

                    // to append data
//                HashMap<String,Object> values = new HashMap<>();
//                values.put("waterAvailability",waterAvailability);
//                reference.child(s1).updateChildren(values);

                    Toast.makeText(getApplicationContext(), "Section 1 complete", Toast.LENGTH_SHORT).show();



//                Toast.makeText(getApplicationContext(), "rankWater "+rankWater, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankElectricity "+rankElectricity, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankHousing "+rankHousing, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPrivateHealth "+rankPrivateHealth, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPublicHealth "+rankPublicHealth, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankSanitation "+rankSanitation, Toast.LENGTH_SHORT).show();
//
                    Intent intent = new Intent(getApplicationContext(),FormSectionTwo.class);
                    startActivity(intent);



                }






        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1 && grantResults.length>0 &&
                (grantResults[0]+grantResults[1] ==PackageManager.PERMISSION_GRANTED)){
//            When permission granted call getLocation
            GetCurrentLocation();
        }else{
//            If permission Denied
            Toast.makeText(this, "PermissionDenied, Please grant permission", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean validateform(){

        String sanitationAvail= sb_sanitationAvailableVar.getText().toString();
        String sanitationExp= sb_sanitationExpectedVar.getText().toString();
        String waterAvail = et_waterAvailabity.getText().toString();
        String waterExp = et_waterExpected.getText().toString();
        String electricityAvail= et_electricityExpected.getText().toString();
        String electricityExp= et_electricityExpected.getText().toString();
        String cost_health_public_exist= et_cost_health_public_existing.getText().toString();
        String cost_health_public_exp= et_cost_health_public_expected.getText().toString();
        String cost_health_private_exist= et_cost_health_private_existing.getText().toString();
        String cost_health_private_exp= et_cost_health_private_expected.getText().toString();
        String cost_renting_exist= et_cost_renting_existing.getText().toString();
        String cost_renting_exp= et_cost_renting_expected.getText().toString();

        if(sanitationAvail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Sanitation field empty", Toast.LENGTH_SHORT).show();
                return false;
        }else if( sanitationExp.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Sanitation field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(waterAvail.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Water field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(waterExp.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Water field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(electricityAvail.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Electricity field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(electricityExp.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Electricity field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(cost_health_public_exist.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Public Health cost field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(cost_health_public_exp.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Public Health cost field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(cost_health_private_exist.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Private Health cost field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(cost_health_private_exp.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Private Health cost field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(cost_renting_exist.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Rental field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(cost_renting_exp.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Rental field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }


    }
    @SuppressLint("MissingPermission")
    private void GetCurrentLocation() {

        LocationRequest locationReq = new LocationRequest();
        locationReq.setInterval(10000);
        locationReq.setFastestInterval(3000);
        locationReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        LocationServices.getFusedLocationProviderClient(FormSectionOne.this)
                .requestLocationUpdates(locationReq, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(FormSectionOne.this).removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocatioIndex = locationResult.getLocations().size() - 1;
                            lat = locationResult.getLocations().get(latestLocatioIndex).getLatitude();
                            longitude1 = locationResult.getLocations().get(latestLocatioIndex).getLongitude();
//                            latitude.setText(String.format("%s", lat));
//                            longitude.setText(String.format("%s",longitude1));

                            //Toast.makeText(getApplicationContext()," "+lat+" "+longitude1, Toast.LENGTH_SHORT).show();

                            SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                            String userID = preferences.getString("userID", "");
                            RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                            reference = RootNode.getReference("users");

                            reference.child(userID).child("latitude").setValue(lat);  //17.319401181464258, 78.40302230454013
                            reference.child(userID).child("longitude").setValue(longitude1);




                            Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_SHORT).show();

//                            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
//                            intent.putExtra("latitude",lat);
//                            intent.putExtra("longitude",longitude1);
//                            startActivity(intent);

//                            Intent intent=new Intent(getApplicationContext(),CalculationActivity.class);
//                            startActivity(intent);
                        }
                    }
                }, Looper.getMainLooper());


    }


}