package com.example.loginactivity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormSectionThree extends AppCompatActivity {
    //     Availability of public spaces
    TextView availPubSpaces,expPubSpaces;
    float availPubSpacesVar,expPubSpacesVar;

    //    Neighbour visits
    TextView exiNeighVisits,expNeighVisits;
    float exiNeighVisitsVar,expNeighVisitsVar;

    Button btn_section3;

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

        btn_section3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                availPubSpacesVar= Float.parseFloat(availPubSpaces.getText().toString());
                expPubSpacesVar= Float.parseFloat(expPubSpaces.getText().toString());

                exiNeighVisitsVar= Float.parseFloat(exiNeighVisits.getText().toString());
                expNeighVisitsVar= Float.parseFloat(expNeighVisits.getText().toString());

                resPubSpaces=(availPubSpacesVar-expPubSpacesVar)/100;
                resNeighVisits=(exiNeighVisitsVar-expNeighVisitsVar)/100;


                //get data from shared preference
                SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
                String s1 = sh.getString("fname", "");
                String s2 = sh.getString("id", "");


                RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                reference = RootNode.getReference("users");

                SectionThreeHelper sectionThreeHelper = new SectionThreeHelper( availPubSpacesVar,  expPubSpacesVar,  exiNeighVisitsVar,  expNeighVisitsVar);

                reference.child(s1).child("section3").setValue(sectionThreeHelper);
                Toast.makeText(getApplicationContext(), "Section 3 complete", Toast.LENGTH_SHORT).show();

//                Toast.makeText(FormSectionThree.this, "resPubSpaces : "+resPubSpaces, Toast.LENGTH_SHORT).show();
//                Toast.makeText(FormSectionThree.this, "resNeighVisits : "+resNeighVisits, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),FormSectionFour.class));
            }
        });





    }
}