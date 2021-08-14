package com.mylivability.loginactivity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormSectionThree extends AppCompatActivity {
    //     Availability of public spaces
    EditText availPubSpaces,expPubSpaces;
    float availPubSpacesVar,expPubSpacesVar;

    //    Neighbour visits
    EditText exiNeighVisits,expNeighVisits;
    float exiNeighVisitsVar,expNeighVisitsVar;

    Button btn_section3,btn_section3_skip;

    float resPubSpaces,resNeighVisits;

    FirebaseDatabase RootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_section_three);

        availPubSpaces=findViewById(R.id.availPubSpaces);
        expPubSpaces=findViewById(R.id.expPubSpaces);

        exiNeighVisits=findViewById(R.id.exiNeighVisits);
        expNeighVisits=findViewById(R.id.expNeighVisits);

        btn_section3=findViewById(R.id.btn_section3);

        btn_section3_skip=findViewById(R.id.btn_section3_skip);

        btn_section3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateform()){

                    availPubSpacesVar= Float.parseFloat(availPubSpaces.getText().toString());
                    expPubSpacesVar= Float.parseFloat(expPubSpaces.getText().toString());

                    exiNeighVisitsVar= Float.parseFloat(exiNeighVisits.getText().toString());
                    expNeighVisitsVar= Float.parseFloat(expNeighVisits.getText().toString());

                    resPubSpaces=(availPubSpacesVar-expPubSpacesVar)/100;
                    resNeighVisits=(exiNeighVisitsVar-expNeighVisitsVar)/100;


                    //get data from shared preference
                    SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                    String userID = preferences.getString("userID", "");
// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show



                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    SectionThreeHelper sectionThreeHelper = new SectionThreeHelper( availPubSpacesVar,  expPubSpacesVar,  exiNeighVisitsVar,  expNeighVisitsVar);

                    reference.child(userID).child("section3").setValue(sectionThreeHelper);
                    Toast.makeText(getApplicationContext(), "Section 3 complete", Toast.LENGTH_SHORT).show();

//                Toast.makeText(FormSectionThree.this, "resPubSpaces : "+resPubSpaces, Toast.LENGTH_SHORT).show();
//                Toast.makeText(FormSectionThree.this, "resNeighVisits : "+resNeighVisits, Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),FormSectionFour.class));

                }else {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_section3_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormSectionFour.class));
            }
        });




    }

    private boolean validateform() {
        String availPubSpac = availPubSpaces.getText().toString();
        String expPubSpac = expPubSpaces.getText().toString();
        String exiNeighVis = exiNeighVisits.getText().toString();
        String expNeighVis =expNeighVisits.getText().toString();

        if(availPubSpac.isEmpty()){
            Toast.makeText(getApplicationContext(), "Public Spaces field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(expPubSpac.isEmpty()){
            Toast.makeText(getApplicationContext(), "Public Spaces field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(exiNeighVis.isEmpty()){
            Toast.makeText(getApplicationContext(), "Neighbour Visit field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else if(expNeighVis.isEmpty()){
            Toast.makeText(getApplicationContext(), "Neighbour Visit field empty", Toast.LENGTH_SHORT).show();
            return false;

        }else{
            return true;
        }
    }
}

