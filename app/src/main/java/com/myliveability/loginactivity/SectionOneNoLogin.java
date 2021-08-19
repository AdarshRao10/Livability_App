package com.myliveability.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SectionOneNoLogin extends AppCompatActivity {

    EditText et_waterAvailabity2,et_waterExpected2,et_electricityAvailablity2,et_electricityExpected2,et_cost_health_public_existing2,et_cost_health_public_expected2,et_cost_health_private_existing2,et_cost_health_private_expected2,et_cost_renting_existing2,et_cost_renting_expected2;
    TextView sb_sanitationAvailableVar2,sb_sanitationExpectedVar2;
    SeekBar sb_sanitationAvailable2,sb_sanitationExpected2;
    Button btn_section1_next2;
    double waterAvailability,waterExpected,electricityAvailability,electricityExpected,cost_health_public_existing,cost_health_public_expected,cost_health_private_existing,cost_health_private_expected,cost_renting_existing,cost_renting_expected, sanitationAvailable,sanitationExpected;

    double rankWater,rankElectricity,rankSanitation,rankPublicHealth,rankPrivateHealth,rankHousing;

    double arr[]=new double[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_one_no_login);

        et_electricityExpected2=findViewById(R.id.et_electricityExpected2);
        et_waterAvailabity2=findViewById(R.id.et_waterAvailabity2);
        et_waterExpected2=findViewById(R.id.et_waterExpected2);
        et_electricityAvailablity2=findViewById(R.id.et_electricityAvailablity2);
        et_cost_health_private_existing2=findViewById(R.id.et_cost_health_private_existing2);
        et_cost_health_public_existing2=findViewById(R.id.et_cost_health_public_existing2);
        et_cost_health_public_expected2=findViewById(R.id.et_cost_health_public_expected2);
        et_cost_health_private_expected2=findViewById(R.id.et_cost_health_private_expected2);
        et_cost_renting_existing2=findViewById(R.id.et_cost_renting_existing2);
        et_cost_renting_expected2=findViewById(R.id.et_cost_renting_expected2);
        sb_sanitationAvailable2=findViewById(R.id.sb_sanitationAvailable2);
        sb_sanitationExpected2=findViewById(R.id.sb_sanitationExpected2);
        btn_section1_next2=findViewById(R.id.btn_section1_next2);
        sb_sanitationAvailableVar2=findViewById(R.id.sb_sanitationAvailableVar2);
        sb_sanitationExpectedVar2=findViewById(R.id.sb_sanitationExpectedVar2);


        sb_sanitationAvailable2.setMax(100);
        sb_sanitationExpected2.setMax(100);


        sb_sanitationAvailable2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue =0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue=progress;
                sb_sanitationAvailableVar2.setText(String.valueOf(progressChangedValue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Value set: "+progressChangedValue, Toast.LENGTH_SHORT).show();


            }
        });

        sb_sanitationExpected2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue =0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue=progress;
                sb_sanitationExpectedVar2.setText(String.valueOf(progressChangedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(), "Value set: "+progressChangedValue, Toast.LENGTH_SHORT).show();


            }
        });

        btn_section1_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Onclick", Toast.LENGTH_SHORT).show();


                if(validateform())
                {
                    sanitationAvailable= Double.parseDouble(sb_sanitationAvailableVar2.getText().toString());
                    sanitationExpected= Double.parseDouble(sb_sanitationExpectedVar2.getText().toString());
                    waterAvailability = Double.parseDouble(et_waterAvailabity2.getText().toString());
                    waterExpected = Double.parseDouble(et_waterExpected2.getText().toString());
                    electricityAvailability= Double.parseDouble(et_electricityAvailablity2.getText().toString());
                    electricityExpected= Double.parseDouble(et_electricityExpected2.getText().toString());
                    cost_health_public_existing= Double.parseDouble(et_cost_health_public_existing2.getText().toString());
                    cost_health_private_existing=Double.parseDouble(et_cost_health_private_existing2.getText().toString());
                    cost_health_private_expected= Double.parseDouble(et_cost_health_private_expected2.getText().toString());
                    cost_health_public_expected= Double.parseDouble(et_cost_health_public_expected2.getText().toString());
                    cost_renting_existing= Double.parseDouble(et_cost_renting_existing2.getText().toString());
                    cost_renting_expected= Double.parseDouble(et_cost_renting_expected2.getText().toString());




                    rankWater= (waterAvailability-waterExpected)/100;
                    rankElectricity = (electricityAvailability-electricityExpected)/100;
                    rankHousing =(cost_renting_expected-cost_renting_existing)/100;
                    rankPublicHealth =(cost_health_public_expected-cost_health_public_existing)/100;
                    rankPrivateHealth =(cost_health_private_expected-cost_health_private_existing)/100;
                    rankSanitation=(sanitationAvailable-sanitationExpected)/100;






                    arr[0] = rankWater;
                    arr[1]= rankElectricity;
                    arr[2]= rankSanitation;
                    arr[3]= rankPublicHealth;
                    arr[4]=rankPrivateHealth;
                    arr[5]= rankHousing;

                    Log.e("values"," "+arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]+" "+arr[4]+" "+arr[5]);


                    //get data from shared preference
                    // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data


//                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
//                    reference = RootNode.getReference("users");
//
//                    SectionOneHelper sectionOneHelper = new SectionOneHelper( waterAvailability,  waterExpected,  electricityAvailability,  electricityExpected, cost_health_public_existing, cost_health_public_expected,  cost_health_private_existing,  cost_health_private_expected,  cost_renting_existing, cost_renting_expected);
//
//                    reference.child(s1).child("section1").setValue(sectionOneHelper);

                    // to append data
//                HashMap<String,Object> values = new HashMap<>();
//                values.put("waterAvailability",waterAvailability);
//                reference.child(s1).updateChildren(values);

                    Toast.makeText(getApplicationContext(), "Section 1 complete", Toast.LENGTH_SHORT).show();



                Toast.makeText(getApplicationContext(), "rankWater "+arr[0], Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "rankElectricity "+arr[1], Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "rankHousing "+arr[2], Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPrivateHealth "+rankPrivateHealth, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPublicHealth "+rankPublicHealth, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankSanitation "+rankSanitation, Toast.LENGTH_SHORT).show();
//
                    Intent intent = new Intent(getApplicationContext(),CalculationNoLoginActivity.class);
                    intent.putExtra("array",arr);
                    startActivity(intent);



                }





            }
        });




    }

    private boolean validateform(){

        String sanitationAvail= sb_sanitationAvailableVar2.getText().toString();
        String sanitationExp= sb_sanitationExpectedVar2.getText().toString();
        String waterAvail = et_waterAvailabity2.getText().toString();
        String waterExp = et_waterExpected2.getText().toString();
        String electricityAvail= et_electricityExpected2.getText().toString();
        String electricityExp= et_electricityExpected2.getText().toString();
        String cost_health_public_exist= et_cost_health_public_existing2.getText().toString();
        String cost_health_public_exp= et_cost_health_public_expected2.getText().toString();
        String cost_health_private_exist= et_cost_health_private_existing2.getText().toString();
        String cost_health_private_exp= et_cost_health_private_expected2.getText().toString();
        String cost_renting_exist= et_cost_renting_existing2.getText().toString();
        String cost_renting_exp= et_cost_renting_expected2.getText().toString();

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