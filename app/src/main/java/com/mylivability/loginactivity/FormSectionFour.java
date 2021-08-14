package com.mylivability.loginactivity;



import static java.lang.Float.parseFloat;

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

public class FormSectionFour extends AppCompatActivity {

    EditText et_publicFacilitiesAvailable,et_publicFacilitiesExpected,et_publicentertainmentUtilitiesAvailable,et_publicentertainmentUtilitiesExpected;
    SeekBar sb_networkSpeedAvailable,sb_networkSpeedExpected;
    Button btn_section4_next, btn_section4_skip;
    float publicFacilitiesAvailability,publicFacilitiesExpected,publicEntertainmentUtilitiesAvailability,publicEntertaimnentUtilitiesExpected;
    float networkSpeedAvailable,networkSpeedExpected;
    TextView sb_networkSpeedAvailableValue,sb_networkSpeedExpectedValue;
    FirebaseDatabase RootNode;
    DatabaseReference reference;

    Float rankPublicFacilities,rankpublicEntertainment,rankNetworkSpeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_section_four);

        et_publicFacilitiesAvailable=findViewById(R.id.et_publicFacilitiesAvailable);
        et_publicFacilitiesExpected=findViewById(R.id.et_publicFacilitiesExpected);
        et_publicentertainmentUtilitiesAvailable=findViewById(R.id.et_publicentertainmentUtilitiesAvailable);
        et_publicentertainmentUtilitiesExpected=findViewById(R.id.et_publicentertainmentUtilitiesExpected);

        sb_networkSpeedAvailable=findViewById(R.id.sb_networkSpeedAvailable);
        sb_networkSpeedExpected=findViewById(R.id.sb_networkSpeedExpected);
        btn_section4_next=findViewById(R.id.btn_section4_next);
        btn_section4_skip=findViewById(R.id.btn_section4_skip);
        sb_networkSpeedAvailableValue=findViewById(R.id.sb_networkSpeedAvailableValue);
        sb_networkSpeedExpectedValue=findViewById(R.id.sb_networkSpeedExpectedValue);

        sb_networkSpeedAvailable.setMax(10);
        sb_networkSpeedExpected.setMax(10);


        sb_networkSpeedAvailable.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue =0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue=progress;
                sb_networkSpeedAvailableValue.setText(String.valueOf(progressChangedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Toast.makeText(getApplicationContext(), "Value set: "+progressChangedValue, Toast.LENGTH_SHORT).show();


            }
        });

        sb_networkSpeedExpected.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue =0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue=progress;
                sb_networkSpeedExpectedValue.setText(String.valueOf(progressChangedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Toast.makeText(getApplicationContext(), "Value set: "+progressChangedValue, Toast.LENGTH_SHORT).show();


            }
        });

        btn_section4_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateform())
                {
                    Toast.makeText(getApplicationContext(), "Onclick", Toast.LENGTH_SHORT).show();

                    publicFacilitiesAvailability = parseFloat(et_publicFacilitiesAvailable.getText().toString());
                    publicFacilitiesExpected = parseFloat(et_publicFacilitiesExpected.getText().toString());
                    publicEntertainmentUtilitiesAvailability= parseFloat(et_publicentertainmentUtilitiesAvailable.getText().toString());
                    publicEntertaimnentUtilitiesExpected= parseFloat(et_publicentertainmentUtilitiesExpected.getText().toString());
                    networkSpeedAvailable=sb_networkSpeedAvailable.getProgress();
                    networkSpeedExpected=sb_networkSpeedExpected.getProgress();


                    rankPublicFacilities= (publicFacilitiesAvailability-publicFacilitiesExpected)/100;
                    rankpublicEntertainment = (publicEntertainmentUtilitiesAvailability-publicEntertaimnentUtilitiesExpected)/100;
                    rankNetworkSpeed =(networkSpeedAvailable-networkSpeedExpected)/100;

                    //get data from shared preference
                    SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                    String userID = preferences.getString("userID", "");
// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show





                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    SectionFourHelper sectionFourHelper = new SectionFourHelper(publicFacilitiesAvailability,publicFacilitiesExpected,publicEntertainmentUtilitiesAvailability,publicEntertaimnentUtilitiesExpected, networkSpeedAvailable,networkSpeedExpected);

                    reference.child(userID).child("section4").setValue(sectionFourHelper);
                    Toast.makeText(getApplicationContext(), "Section 4 complete", Toast.LENGTH_SHORT).show();

//                Toast.makeText(getApplicationContext(), "rankPublicFacilities "+rankPublicFacilities, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankEntertainnment "+rankpublicEntertainment, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankNetworkSpeed "+rankNetworkSpeed, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),FormSectionFiveSixSeven.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_section4_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormSectionFiveSixSeven.class));
            }
        });



    }

    private boolean validateform() {
        String sb_networkSpeedAvail=sb_networkSpeedAvailableValue.getText().toString();
        String sb_networkSpeedExp=sb_networkSpeedExpectedValue.getText().toString();
        String et_publicFacilitiesAvail=et_publicFacilitiesAvailable.getText().toString();
        String et_publicFacilitiesExp=et_publicFacilitiesExpected.getText().toString();
        String et_publicentertainmentUtilitiesAvail=et_publicentertainmentUtilitiesAvailable.getText().toString();
        String et_publicentertainmentUtilitiesExp=et_publicentertainmentUtilitiesExpected.getText().toString();

        if(sb_networkSpeedAvail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Network field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(sb_networkSpeedExp.isEmpty()){
            Toast.makeText(getApplicationContext(), "Network field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(et_publicFacilitiesAvail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Public Leisure facilities field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(et_publicFacilitiesExp.isEmpty()){
            Toast.makeText(getApplicationContext(), "Public Leisure facilities field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(et_publicentertainmentUtilitiesAvail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Public Entertainment facilities field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(et_publicentertainmentUtilitiesExp.isEmpty()){

            Toast.makeText(getApplicationContext(), "Public Entertainment facilities field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else{
            return true;
        }


    }
}


