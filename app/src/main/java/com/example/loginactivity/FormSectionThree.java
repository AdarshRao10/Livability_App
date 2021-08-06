package com.example.loginactivity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FormSectionThree extends AppCompatActivity {
    //     Availability of public spaces
    TextView availPubSpaces,expPubSpaces;
    float availPubSpacesVar,expPubSpacesVar;

    //    Neighbour visits
    TextView exiNeighVisits,expNeighVisits;
    float exiNeighVisitsVar,expNeighVisitsVar;

    Button btn_section3;

    float resPubSpaces,resNeighVisits;



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

                Toast.makeText(FormSectionThree.this, "resPubSpaces : "+resPubSpaces, Toast.LENGTH_SHORT).show();
                Toast.makeText(FormSectionThree.this, "resNeighVisits : "+resNeighVisits, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),FormSectionFour.class));
            }
        });





    }
}