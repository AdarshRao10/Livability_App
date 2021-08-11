package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Terms extends AppCompatActivity {
    CheckBox checkBox;
    Button btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        checkBox = findViewById(R.id.checkbox);
        btnAgree =findViewById(R.id.btnAgree);


        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked())
                {


//                    Intent Home = new Intent(getApplicationContext(),Home.class);
//                    startActivity(Home);
                    Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_SHORT).show();
                    Intent Home = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(Home);


                }
                else{
                    Toast.makeText(getApplicationContext(),"click on the check box to continue!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}