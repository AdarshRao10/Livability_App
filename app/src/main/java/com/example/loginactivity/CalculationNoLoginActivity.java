package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CalculationNoLoginActivity extends AppCompatActivity {

    float arr[] = {-0.4f,-0.02f,-0.03f,-5,-30,-20};

    Button goToMaps,goToGraphs;
    int countGreen=0,countBlue=0,countRed=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_no_login);

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


        if(countGreen==6)
        {
            Toast.makeText(getApplicationContext(), "Dark Green", Toast.LENGTH_SHORT).show();
        }else if( (countGreen +countBlue)==5)
        {
            Toast.makeText(getApplicationContext(), "Light Green", Toast.LENGTH_SHORT).show();
        }else if(((countGreen +countBlue)==3) || ((countGreen +countBlue)==4))
        {
            Toast.makeText(getApplicationContext(), "yellow", Toast.LENGTH_SHORT).show();
        }else if(((countGreen +countBlue)==2))
        {
            Toast.makeText(getApplicationContext(), "orange", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "red", Toast.LENGTH_SHORT).show();
        }



        goToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        goToGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BarchartActivity.class);
                intent.putExtra("array",arr);
            }
        });

    }
}