package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CalculationActivity extends AppCompatActivity {
//            variables declared as per column 1 of calculation table in same sequence
//    float water,electricity,sanitation,PubHealth,PriHealth,housing,pubMassTrans,priMassTrans,pubEduSyatem,priEduSystem,safety,employment,pubSpace,communityLife,leisure,entertain,network,governance,natEnv,quality;

// Values need to be added in an array. while fetching, create above commented variables
//    Add the values from Firebase to the variables and pass them to array. Below are dummy values
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

  String Result;


    FirebaseDatabase RootNode;
    DatabaseReference reference;



    Button goToMaps,goToGraphs;
    int countGreen=0,countBlue=0,countRed=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        goToMaps=findViewById(R.id.goToMaps);
        goToGraphs=findViewById(R.id.goToGraphs);

         //1st get values from all section
        RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
        reference = RootNode.getReference("users");


        SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
        String userID = preferences.getString("userID", "");
        Query checkuser = reference.orderByChild("id").equalTo(userID);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                     waterAvailability = snapshot.child(userID).child("section1").child("waterAvailability").getValue(Float.class);
                     waterExpected = snapshot.child(userID).child("section1").child("waterExpected").getValue(Float.class);
                     electricityAvailability = snapshot.child(userID).child("section1").child("electricityAvailability").getValue(Float.class);
                     electricityExpected = snapshot.child(userID).child("section1").child("electricityExpected").getValue(Float.class);
                     sanitationAvailable = snapshot.child(userID).child("section1").child("sanitationAvailable").getValue(Float.class);
                     sanitationExpected = snapshot.child(userID).child("section1").child("sanitationExpected").getValue(Float.class);
                     cost_health_public_existing = snapshot.child(userID).child("section1").child("cost_health_public_existing").getValue(Float.class);
                     cost_health_public_expected = snapshot.child(userID).child("section1").child("cost_health_public_expected").getValue(Float.class);
                     cost_health_private_existing = snapshot.child(userID).child("section1").child("waterAvailability").getValue(Float.class);
                     cost_health_private_expected = snapshot.child(userID).child("section1").child("cost_health_private_expected").getValue(Float.class);
                     cost_renting_existing = snapshot.child(userID).child("section1").child("cost_renting_existing").getValue(Float.class);
                     cost_renting_expected = snapshot.child(userID).child("section1").child("cost_renting_expected").getValue(Float.class);
                     water = (waterAvailability - waterExpected)/100;
                     electricity= (electricityAvailability-electricityExpected)/100;
                     sanitation = (sanitationAvailable-sanitationExpected)/100;
                     public_health_sys = (cost_health_public_expected-cost_health_public_existing)/100;
                     private_health_sys = (cost_health_private_expected-cost_health_private_existing)/100;
                     housing = (cost_renting_expected-cost_health_private_existing)/100;

                    availPubTransVar = snapshot.child(userID).child("section2").child("availPubTransVar").getValue(Float.class);
                    expPubTransVar = snapshot.child(userID).child("section2").child("expPubTransVar").getValue(Float.class);
                    availPriTransVar = snapshot.child(userID).child("section2").child("availPriTransVar").getValue(Float.class);
                    expPriTransVar = snapshot.child(userID).child("section2").child("expPriTransVar").getValue(Float.class);
                    exiEduFacPubVar = snapshot.child(userID).child("section2").child("exiEduFacPubVar").getValue(Float.class);
                    expEduFacPubVar = snapshot.child(userID).child("section2").child("expEduFacPubVar").getValue(Float.class);
                    exiEduFacPriVar = snapshot.child(userID).child("section2").child("exiEduFacPriVar").getValue(Float.class);
                    expEduFacPriVar = snapshot.child(userID).child("section2").child("expEduFacPriVar").getValue(Float.class);
                    availPolStationVar = snapshot.child(userID).child("section2").child("availPolStationVar").getValue(Float.class);
                    expPolStationVar = snapshot.child(userID).child("section2").child("expPolStationVar").getValue(Float.class);
                    exiSalVar = snapshot.child(userID).child("section2").child("exiSalVar").getValue(Float.class);
                    expSalVar = snapshot.child(userID).child("section2").child("expSalVar").getValue(Float.class);
                    public_mass_transport=(availPubTransVar-expPubTransVar)/100;
                    private_mass_transport=(availPriTransVar-expPriTransVar)/100;
                    public_edu_sys=(exiEduFacPriVar-expEduFacPriVar)/100;
                    private_edu_sys=(exiEduFacPriVar-exiEduFacPubVar)/100;
                    safety_security=(availPolStationVar-expPolStationVar)/100;
                    emp_opportunity=(exiSalVar-expSalVar)/100;

                    availPubSpacesVar = snapshot.child(userID).child("section3").child("availPubSpacesVar").getValue(Float.class);
                    expPubSpacesVar = snapshot.child(userID).child("section3").child("expPubSpacesVar").getValue(Float.class);
                    exiNeighVisitsVar = snapshot.child(userID).child("section3").child("exiNeighVisitsVar").getValue(Float.class);
                    expNeighVisitsVar = snapshot.child(userID).child("section3").child("expNeighVisitsVar").getValue(Float.class);
                    public_space=(availPubSpacesVar-expPubSpacesVar)/100;
                    community=(exiNeighVisitsVar-expNeighVisitsVar)/100;

                    publicFacilitiesAvailability = snapshot.child(userID).child("section4").child("publicFacilitiesAvailability").getValue(Float.class);
                    publicFacilitiesExpected = snapshot.child(userID).child("section4").child("publicFacilitiesExpected").getValue(Float.class);
                    publicEntertainmentUtilitiesAvailability = snapshot.child(userID).child("section4").child("publicEntertainmentUtilitiesAvailability").getValue(Float.class);
                    publicEntertaimnentUtilitiesExpected = snapshot.child(userID).child("section4").child("publicEntertaimnentUtilitiesExpected").getValue(Float.class);
                    networkSpeedAvailable = snapshot.child(userID).child("section4").child("networkSpeedAvailable").getValue(Float.class);
                    networkSpeedExpected = snapshot.child(userID).child("section4").child("networkSpeedExpected").getValue(Float.class);
                    leisure_recreation =( publicFacilitiesAvailability-publicFacilitiesExpected)/100;
                    entertainment=(publicEntertainmentUtilitiesAvailability-publicEntertaimnentUtilitiesExpected)/100;
                    network_connectivity=(networkSpeedAvailable-networkSpeedExpected)/100;

                    exiGovtRespTimeVar = snapshot.child(userID).child("section5").child("exiGovtRespTimeVar").getValue(Float.class);
                    expGovtRespTimeVar = snapshot.child(userID).child("section5").child("expGovtRespTimeVar").getValue(Float.class);
                    availNatEnvVar = snapshot.child(userID).child("section5").child("availNatEnvVar").getValue(Float.class);
                    expNatEnvVar = snapshot.child(userID).child("section5").child("expNatEnvVar").getValue(Float.class);
                    exiQualityVar = snapshot.child(userID).child("section5").child("exiQualityVar").getValue(Float.class);
                    expQualityVar = snapshot.child(userID).child("section5").child("expQualityVar").getValue(Float.class);
                    governance = (expGovtRespTimeVar-exiGovtRespTimeVar)/100;
                    natural_env =(availNatEnvVar-expNatEnvVar)/100;
                    quality_of_life = (exiQualityVar-expQualityVar)/100;



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        float arr[] = {water,electricity,sanitation,public_health_sys,private_health_sys,housing,
                public_mass_transport,private_mass_transport,public_edu_sys,private_edu_sys,safety_security,emp_opportunity,
                public_space,community,leisure_recreation,entertainment,network_connectivity,governance,natural_env,quality_of_life};

        float arr2[] = {0.4f,-0.02f,0.03f,-5,30,-20,-0.15f,0,-0.03f,-0.06f,-0.05f,-150,-0.2f,-0.04f,-0.15f,-0.06f,-0.04f,-0.16f,-0.2f,-0.03f};

        for(float i : arr)
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


        if(countGreen>16)
        {
            Toast.makeText(getApplicationContext(), "Dark Green", Toast.LENGTH_SHORT).show();
            Result = "dark";
        }else if( (countGreen +countBlue)>=13 || (countGreen +countBlue)<=16)
        {
            Toast.makeText(getApplicationContext(), "Light Green", Toast.LENGTH_SHORT).show();
            Result = "green";
        }else if(((countGreen +countBlue)>=8) || ((countGreen +countBlue)<=12))
        {
            Toast.makeText(getApplicationContext(), "yellow", Toast.LENGTH_SHORT).show();
            Result = "yellow";
        }else if(((countGreen +countBlue)>=4) || ((countGreen +countBlue)<=7))
        {
            Toast.makeText(getApplicationContext(), "orange", Toast.LENGTH_SHORT).show();
            Result = "orange";
        }else
        {
            Toast.makeText(getApplicationContext(), "red", Toast.LENGTH_SHORT).show();
            Result = "Red";
        }



        goToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = getSharedPreferences("MySharedPref2",MODE_PRIVATE);
                String id = sh.getString("Id", "");
                RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                reference = RootNode.getReference("users");
                reference.child(id).child("result").setValue(Result);


                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        goToGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BarchartActivity.class);
                intent.putExtra("array",arr2);
                startActivity(intent);
            }
        });




    }
}