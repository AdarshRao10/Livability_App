package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    float latitude,longitude,latitude2,longitude2;

    float sanitationAvailable,
            sanitationExpected,
            cost_health_private_existing,
            cost_health_private_expected,
            cost_health_public_existing,
            cost_health_public_expected,
            cost_renting_existing,
            cost_renting_expected,
            electricityAvailability,
            electricityExpected,
            waterAvailability,
            waterExpected,
            water,
            electricity,
            sanitation,
            public_health_sys,
            private_health_sys,
            housing;

    float availPolStationVar,
            availPriTransVar,
            availPubTransVar,
            exiEduFacPriVar,
            exiEduFacPubVar,
            exiSalVar,
            expEduFacPriVar,
            expEduFacPubVar,
            expPolStationVar,
            expPriTransVar,
            expPubTransVar,
            expSalVar,
            public_mass_transport,private_mass_transport,public_edu_sys,private_edu_sys,safety_security,emp_opportunity;

    float availPubSpacesVar,
            expPubSpacesVar,
            exiNeighVisitsVar,
            expNeighVisitsVar,public_space,community;


    float   publicFacilitiesAvailability,
            publicFacilitiesExpected,
            publicEntertainmentUtilitiesAvailability,
            publicEntertaimnentUtilitiesExpected,
            networkSpeedAvailable,
            networkSpeedExpected,leisure_recreation,network_connectivity,entertainment;

    float  exiQualityVar,
            expQualityVar,
            exiGovtRespTimeVar,
            expGovtRespTimeVar,
            availNatEnvVar,
            expNatEnvVar,governance,natural_env,quality_of_life;

    int countGreen=0,countBlue=0,countRed=0;

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




        FirebaseDatabase.getInstance().getReference().child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            latitude = snapshot.child("latitude").getValue(Float.class);
                            longitude = snapshot.child("longitude").getValue(Float.class);
                            result = snapshot.child("result").getValue(String.class);
                            LatLng sydney = new LatLng(latitude,longitude);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17));

                            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();

                            if(result.equals("dark"))
        {
           // Toast.makeText(getApplicationContext(), "Dark Green", Toast.LENGTH_SHORT).show();

            mMap.addCircle(new CircleOptions()
                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88008E22")).strokeWidth(5));

        }else if(result.equals("green"))
        {
           // Toast.makeText(getApplicationContext(), "Light Green", Toast.LENGTH_SHORT).show();

            mMap.addCircle(new CircleOptions()
                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#8868FA73")).strokeWidth(5));

        }else if(result.equals("yellow"))
        {
          //  Toast.makeText(getApplicationContext(), "yellow", Toast.LENGTH_SHORT).show();

            mMap.addCircle(new CircleOptions()
                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88E4EE0F")).strokeWidth(5));

        }else if(result.equals("orange"))
        {
           // Toast.makeText(getApplicationContext(), "orange", Toast.LENGTH_SHORT).show();

            mMap.addCircle(new CircleOptions()
                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88F49F0A")).strokeWidth(5));

        }else
        {
          //  Toast.makeText(getApplicationContext(), "red", Toast.LENGTH_SHORT).show();

            mMap.addCircle(new CircleOptions()
                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88EC1C1C")).strokeWidth(5));

        }
                            SharedPreferences sh = getSharedPreferences("MySharedPref2",MODE_PRIVATE);
                            String id = sh.getString("Id", "");

                            RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                            reference = RootNode.getReference("users");
                            Query checkuser = reference.orderByChild("id").equalTo(id);

                            checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        waterAvailability = snapshot.child(id).child("section1").child("waterAvailability").getValue(Float.class);
                                        latitude2 = snapshot.child(id).child("latitude").getValue(Float.class);
                                        longitude2 = snapshot.child(id).child("longitude").getValue(Float.class);
                                        LatLng sydney2 = new LatLng(latitude,longitude);
                                        mMap.addMarker(new MarkerOptions().position(sydney2).title("My location."));



                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });





//                            waterAvailability = snapshot.child("section1").child("waterAvailability").getValue(Float.class);
//                            waterExpected = snapshot.child("section1").child("waterExpected").getValue(Float.class);
//                            electricityAvailability = snapshot.child("section1").child("electricityAvailability").getValue(Float.class);
//                            electricityExpected = snapshot.child("section1").child("electricityExpected").getValue(Float.class);
//                            sanitationAvailable = snapshot.child("section1").child("sanitationAvailable").getValue(Float.class);
//                            sanitationExpected = snapshot.child("section1").child("sanitationExpected").getValue(Float.class);
//                            cost_health_public_existing = snapshot.child("section1").child("cost_health_public_existing").getValue(Float.class);
//                            cost_health_public_expected = snapshot.child("section1").child("cost_health_public_expected").getValue(Float.class);
//                            cost_health_private_existing = snapshot.child("section1").child("waterAvailability").getValue(Float.class);
//                            cost_health_private_expected = snapshot.child("section1").child("cost_health_private_expected").getValue(Float.class);
//                            cost_renting_existing = snapshot.child("section1").child("cost_renting_existing").getValue(Float.class);
//                            cost_renting_expected = snapshot.child("section1").child("cost_renting_expected").getValue(Float.class);
//                            water = (waterAvailability - waterExpected)/100;
//                            electricity= (electricityAvailability-electricityExpected)/100;
//                            sanitation = (sanitationAvailable-sanitationExpected)/100;
//                            public_health_sys = (cost_health_public_expected-cost_health_public_existing)/100;
//                            private_health_sys = (cost_health_private_expected-cost_health_private_existing)/100;
//                            housing = (cost_renting_expected-cost_health_private_existing)/100;
//
//                            availPubTransVar = snapshot.child("section2").child("availPubTransVar").getValue(Float.class);
//                            expPubTransVar = snapshot.child("section2").child("expPubTransVar").getValue(Float.class);
//                            availPriTransVar = snapshot.child("section2").child("availPriTransVar").getValue(Float.class);
//                            expPriTransVar = snapshot.child("section2").child("expPriTransVar").getValue(Float.class);
//                            exiEduFacPubVar = snapshot.child("section2").child("exiEduFacPubVar").getValue(Float.class);
//                            expEduFacPubVar = snapshot.child("section2").child("expEduFacPubVar").getValue(Float.class);
//                            exiEduFacPriVar = snapshot.child("section2").child("exiEduFacPriVar").getValue(Float.class);
//                            expEduFacPriVar = snapshot.child("section2").child("expEduFacPriVar").getValue(Float.class);
//                            availPolStationVar = snapshot.child("section2").child("availPolStationVar").getValue(Float.class);
//                            expPolStationVar = snapshot.child("section2").child("expPolStationVar").getValue(Float.class);
//                            exiSalVar = snapshot.child("section2").child("exiSalVar").getValue(Float.class);
//                            expSalVar = snapshot.child("section2").child("expSalVar").getValue(Float.class);
//                            public_mass_transport=(availPubTransVar-expPubTransVar)/100;
//                            private_mass_transport=(availPriTransVar-expPriTransVar)/100;
//                            public_edu_sys=(exiEduFacPriVar-expEduFacPriVar)/100;
//                            private_edu_sys=(exiEduFacPriVar-exiEduFacPubVar)/100;
//                            safety_security=(availPolStationVar-expPolStationVar)/100;
//                            emp_opportunity=(exiSalVar-expSalVar)/100;
//
//                            availPubSpacesVar = snapshot.child("section3").child("availPubSpacesVar").getValue(Float.class);
//                            expPubSpacesVar = snapshot.child("section3").child("expPubSpacesVar").getValue(Float.class);
//                            exiNeighVisitsVar = snapshot.child("section3").child("exiNeighVisitsVar").getValue(Float.class);
//                            expNeighVisitsVar = snapshot.child("section3").child("expNeighVisitsVar").getValue(Float.class);
//                            public_space=(availPubSpacesVar-expPubSpacesVar)/100;
//                            community=(exiNeighVisitsVar-expNeighVisitsVar)/100;
//
//                            publicFacilitiesAvailability = snapshot.child("section4").child("publicFacilitiesAvailability").getValue(Float.class);
//                            publicFacilitiesExpected = snapshot.child("section4").child("publicFacilitiesExpected").getValue(Float.class);
//                            publicEntertainmentUtilitiesAvailability = snapshot.child("section4").child("publicEntertainmentUtilitiesAvailability").getValue(Float.class);
//                            publicEntertaimnentUtilitiesExpected = snapshot.child("section4").child("publicEntertaimnentUtilitiesExpected").getValue(Float.class);
//                            networkSpeedAvailable = snapshot.child("section4").child("networkSpeedAvailable").getValue(Float.class);
//                            networkSpeedExpected = snapshot.child("section4").child("networkSpeedExpected").getValue(Float.class);
//                            leisure_recreation =( publicFacilitiesAvailability-publicFacilitiesExpected)/100;
//                            entertainment=(publicEntertainmentUtilitiesAvailability-publicEntertaimnentUtilitiesExpected)/100;
//                            network_connectivity=(networkSpeedAvailable-networkSpeedExpected)/100;
//
//                            exiGovtRespTimeVar = snapshot.child("section5").child("exiGovtRespTimeVar").getValue(Float.class);
//                            expGovtRespTimeVar = snapshot.child("section5").child("expGovtRespTimeVar").getValue(Float.class);
//                            availNatEnvVar = snapshot.child("section5").child("availNatEnvVar").getValue(Float.class);
//                            expNatEnvVar = snapshot.child("section5").child("expNatEnvVar").getValue(Float.class);
//                            exiQualityVar = snapshot.child("section5").child("exiQualityVar").getValue(Float.class);
//                            expQualityVar = snapshot.child("section5").child("expQualityVar").getValue(Float.class);
//                            governance = (expGovtRespTimeVar-exiGovtRespTimeVar)/100;
//                            natural_env =(availNatEnvVar-expNatEnvVar)/100;
//                            quality_of_life = (exiQualityVar-expQualityVar)/100;

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

//        float arr[] = {water,electricity,sanitation,public_health_sys,private_health_sys,housing,
//                public_mass_transport,private_mass_transport,public_edu_sys,private_edu_sys,safety_security,emp_opportunity,
//                public_space,community,leisure_recreation,entertainment,network_connectivity,governance,natural_env,quality_of_life};

//        for(float i : arr)
//        {
//            if(i>=0.26)
//            {
//                countGreen++;
//            }else if(i>=-0.25 && i<=0.25)
//            {
//                countBlue++;
//            }else
//            {
//                countRed++;
//            }
//
//        }

//        LatLng sydney = new LatLng(latitude,longitude);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17));
//
//        if(countGreen>16)
//        {
//           // Toast.makeText(getApplicationContext(), "Dark Green", Toast.LENGTH_SHORT).show();
//
//            mMap.addCircle(new CircleOptions()
//                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88008E22")).strokeWidth(5));
//
//        }else if( (countGreen +countBlue)>=13 || (countGreen +countBlue)<=16)
//        {
//           // Toast.makeText(getApplicationContext(), "Light Green", Toast.LENGTH_SHORT).show();
//
//            mMap.addCircle(new CircleOptions()
//                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#8868FA73")).strokeWidth(5));
//
//        }else if(((countGreen +countBlue)>=8) || ((countGreen +countBlue)<=12))
//        {
//          //  Toast.makeText(getApplicationContext(), "yellow", Toast.LENGTH_SHORT).show();
//
//            mMap.addCircle(new CircleOptions()
//                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88E4EE0F")).strokeWidth(5));
//
//        }else if(((countGreen +countBlue)>=4) || ((countGreen +countBlue)<=7))
//        {
//           // Toast.makeText(getApplicationContext(), "orange", Toast.LENGTH_SHORT).show();
//
//            mMap.addCircle(new CircleOptions()
//                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88F49F0A")).strokeWidth(5));
//
//        }else
//        {
//          //  Toast.makeText(getApplicationContext(), "red", Toast.LENGTH_SHORT).show();
//
//            mMap.addCircle(new CircleOptions()
//                    .center(sydney).radius(100).strokeColor(Color.RED).fillColor(Color.parseColor("#88EC1C1C")).strokeWidth(5));
//
//        }


    }
}