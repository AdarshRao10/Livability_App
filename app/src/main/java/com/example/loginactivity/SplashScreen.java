package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity

                SharedPreferences preferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
                if(preferences.contains("email"))
                {
                    startActivity(new Intent(SplashScreen.this,Terms.class));
                }else
                {
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                }


                // close this activity
                finish();
            }
        }, 3000);
    }
}