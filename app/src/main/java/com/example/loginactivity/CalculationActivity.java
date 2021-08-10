package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CalculationActivity extends AppCompatActivity {
//            variables declared as per column 1 of calculation table in same sequence
//    float water,electricity,sanitation,PubHealth,PriHealth,housing,pubMassTrans,priMassTrans,pubEduSyatem,priEduSystem,safety,employment,pubSpace,communityLife,leisure,entertain,network,governance,natEnv,quality;

// Values need to be added in an array. while fetching, create above commented variables
//    Add the values from Firebase to the variables and pass them to array. Below are dummy values
    float arr[] = {0.4f,-0.02f,0.03f,-5,30,-20,-0.15f,0,-0.03f,-0.06f,-0.05f,-150,-0.2f,-0.04f,-0.15f,-0.06f,-0.04f,-0.16f,-0.2f,-0.03f};
    Button goToMaps,goToGraphs;
    int countGreen=0,countBlue=0,countRed=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        goToMaps=findViewById(R.id.goToMaps);
        goToGraphs=findViewById(R.id.goToGraphs);

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
        }else if( (countGreen +countBlue)>=13 || (countGreen +countBlue)<=16)
        {
            Toast.makeText(getApplicationContext(), "Light Green", Toast.LENGTH_SHORT).show();
        }else if(((countGreen +countBlue)>=8) || ((countGreen +countBlue)<=12))
        {
            Toast.makeText(getApplicationContext(), "yellow", Toast.LENGTH_SHORT).show();
        }else if(((countGreen +countBlue)>=4) || ((countGreen +countBlue)<=7))
        {
            Toast.makeText(getApplicationContext(), "orange", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "red", Toast.LENGTH_SHORT).show();
        }



        goToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Opening Map", Toast.LENGTH_SHORT).show();
                Intent mapIntent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(mapIntent);

            }
        });

        goToGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Opening bargraph", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),BarchartActivity.class);
                intent.putExtra("array",arr);
                startActivity(intent);
            }
        });




    }
}