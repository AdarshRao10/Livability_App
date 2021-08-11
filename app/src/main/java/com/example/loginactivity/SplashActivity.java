package com.example.loginactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.text.Normalizer;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity

                SharedPreferences preferences=getSharedPreferences("userID",MODE_PRIVATE);
                if(preferences.contains("userID"))
                {
                    startActivity(new Intent(SplashActivity.this, CalculationActivity.class));
                }else
                {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }


                // close this activity
                finish();
            }
        }, 3000);
    }
}