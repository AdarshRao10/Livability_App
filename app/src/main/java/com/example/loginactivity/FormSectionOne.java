package com.example.loginactivity;
import static java.lang.Float.parseFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
                    SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
                    String s1 = sh.getString("fname", "");
                    String s2 = sh.getString("id", "");


                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    SectionOneHelper sectionOneHelper = new SectionOneHelper( waterAvailability,  waterExpected,  electricityAvailability,  electricityExpected, cost_health_public_existing, cost_health_public_expected,  cost_health_private_existing,  cost_health_private_expected,  cost_renting_existing, cost_renting_expected);

                    reference.child(s1).child("section1").setValue(sectionOneHelper);

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





            }
        });

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
}