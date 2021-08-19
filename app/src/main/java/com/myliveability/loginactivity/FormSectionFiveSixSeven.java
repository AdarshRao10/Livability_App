package com.myliveability.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormSectionFiveSixSeven extends AppCompatActivity {

    //    Quality of Life
    SeekBar exiQuality,expQuality;
    TextView exiQualityVal,expQualityVal;
    float exiQualityVar,expQualityVar;

    //    Governance
    EditText exiGovtRespTime,expGovtRespTime;
    float exiGovtRespTimeVar,expGovtRespTimeVar;

    //    Natural Environment
    EditText availNatEnv,expNatEnv;
    float availNatEnvVar,expNatEnvVar;

    float rankQuality,rankGovt,rankNatEnv;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    Button btn_section567,btn_section567_skip;



    int max=100;

//    private static final int loc_permission = 1;
//    double lat,longitude1;

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
        btn_section567_skip=findViewById(R.id.btn_section567_skip);
        //logout=findViewById(R.id.logout);


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

                if(validateform())
                {


//                Quality Of Life
                    exiQualityVar= Float.parseFloat(exiQualityVal.getText().toString());
                    expQualityVar= Float.parseFloat(expQualityVal.getText().toString());

//                Governance
                    exiGovtRespTimeVar= Float.parseFloat(exiGovtRespTime.getText().toString());
                    expGovtRespTimeVar=Float.parseFloat(expGovtRespTime.getText().toString());

//                NaturalEnv
                    availNatEnvVar=Float.parseFloat(availNatEnv.getText().toString());
                    expNatEnvVar=Float.parseFloat(expNatEnv.getText().toString());



                    //get data from shared preference
                    SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                    String userID = preferences.getString("userID", "");

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show





                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    SectionFiveSixSevenHelper sectionFiveSixSevenHelper = new SectionFiveSixSevenHelper(exiQualityVar,expQualityVar,exiGovtRespTimeVar,expGovtRespTimeVar,availNatEnvVar,expNatEnvVar);

                    reference.child(userID).child("section5").setValue(sectionFiveSixSevenHelper);
                    Toast.makeText(getApplicationContext(), "Section 5 complete", Toast.LENGTH_SHORT).show();

//                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                            != PackageManager.PERMISSION_GRANTED) {
//                        // Permission is not granted
//                        ActivityCompat.requestPermissions(FormSectionFiveSixSeven.this,
//                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                loc_permission);
//                    } else {
//                        //permission granted
//                        GetCurrentLocation();
//                    }

                    Intent intent = new Intent(getApplicationContext(),CalculationActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }


//                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankQuality, Toast.LENGTH_SHORT).show();
//                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankGovt, Toast.LENGTH_SHORT).show();
//                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankNatEnv, Toast.LENGTH_SHORT).show();




            }
        });

        btn_section567_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CalculationActivity.class));
            }
        });



//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    // Permission is not granted
//                    ActivityCompat.requestPermissions(FormSectionFiveSixSeven.this,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                            loc_permission);
//                } else {
//                    //permission granted
//                    GetCurrentLocation();
//                }
//
//
//
////                SharedPreferences preferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
////                SharedPreferences.Editor editor=preferences.edit();
////
////                editor.remove("email");
////                editor.clear();
////                editor.commit();
//
//
//            }
//        });

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode==1 && grantResults.length>0 &&
//                (grantResults[0]+grantResults[1] ==PackageManager.PERMISSION_GRANTED)){
////            When permission granted call getLocation
//            GetCurrentLocation();
//        }else{
////            If permission Denied
//            Toast.makeText(this, "PermissionDenied, Please grant permission", Toast.LENGTH_SHORT).show();
//        }
//    }


    private boolean validateform() {
        String exiGovtResp=exiGovtRespTime.getText().toString();
        String expGovtResp= expGovtRespTime.getText().toString();
        String availNat =availNatEnv.getText().toString();
        String expNat= expNatEnv.getText().toString();
        String exiQuality=exiQualityVal.getText().toString();
        String expQuality=expQualityVal.getText().toString();

        if(exiGovtResp.isEmpty()){
            Toast.makeText(getApplicationContext(), "Govt Resp field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(expGovtResp.isEmpty()){
            Toast.makeText(getApplicationContext(), "Govt Resp field empty", Toast.LENGTH_SHORT).show();
            return false;


        }else if(availNat.isEmpty()){
            Toast.makeText(getApplicationContext(), "Natural env field empty", Toast.LENGTH_SHORT).show();
            return false;


        }else if(expNat.isEmpty()){
            Toast.makeText(getApplicationContext(), "Natural env field empty", Toast.LENGTH_SHORT).show();
            return false;


        }else if(exiQuality.isEmpty()){
            Toast.makeText(getApplicationContext(), "Quality field empty", Toast.LENGTH_SHORT).show();
            return false;


        }else if(expQuality.isEmpty()){

            Toast.makeText(getApplicationContext(), "Quality field empty", Toast.LENGTH_SHORT).show();
            return false;


        }else{

            return true;
        }

    }

//    @SuppressLint("MissingPermission")
//    private void GetCurrentLocation() {
//
//        LocationRequest locationReq = new LocationRequest();
//        locationReq.setInterval(10000);
//        locationReq.setFastestInterval(3000);
//        locationReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//
//        LocationServices.getFusedLocationProviderClient(FormSectionFiveSixSeven.this)
//                .requestLocationUpdates(locationReq, new LocationCallback() {
//                    @Override
//                    public void onLocationResult(@NonNull LocationResult locationResult) {
//                        super.onLocationResult(locationResult);
//                        LocationServices.getFusedLocationProviderClient(FormSectionFiveSixSeven.this).removeLocationUpdates(this);
//                        if(locationResult != null && locationResult.getLocations().size() > 0) {
//                            int latestLocatioIndex = locationResult.getLocations().size() - 1;
//                            lat = locationResult.getLocations().get(latestLocatioIndex).getLatitude();
//                            longitude1 = locationResult.getLocations().get(latestLocatioIndex).getLongitude();
////                            latitude.setText(String.format("%s", lat));
////                            longitude.setText(String.format("%s",longitude1));
//
//                            //Toast.makeText(getApplicationContext()," "+lat+" "+longitude1, Toast.LENGTH_SHORT).show();
//
//                            SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
//                            String userID = preferences.getString("userID", "");
//                            RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
//                            reference = RootNode.getReference("users");
//
//                            reference.child(userID).child("latitude").setValue(lat);  //17.319401181464258, 78.40302230454013
//                           reference.child(userID).child("longitude").setValue(longitude1);
//
//
//
//
//                            Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_SHORT).show();
//
////                            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
////                            intent.putExtra("latitude",lat);
////                            intent.putExtra("longitude",longitude1);
////                            startActivity(intent);
//
//                            Intent intent=new Intent(getApplicationContext(),CalculationActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//                }, Looper.getMainLooper());
//
//
//    }

}



