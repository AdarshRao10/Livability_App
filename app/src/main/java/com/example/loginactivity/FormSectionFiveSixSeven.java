package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class FormSectionFiveSixSeven extends AppCompatActivity {

    //    Quality of Life
    SeekBar exiQuality,expQuality;
    TextView exiQualityVal,expQualityVal;
    float exiQualityVar,expQualityVar;

    //    Governance
    TextView exiGovtRespTime,expGovtRespTime;
    float exiGovtRespTimeVar,expGovtRespTimeVar;

    //    Natural Environment
    TextView availNatEnv,expNatEnv;
    float availNatEnvVar,expNatEnvVar;

    float rankQuality,rankGovt,rankNatEnv;

    Button btn_section567;

    Button logout;

    int max=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_section_five_six_seven);

//        Quality of Life
        exiQuality=findViewById(R.id.exiQuality);
        expQuality=findViewById(R.id.expQuality);

        exiQualityVal=findViewById(R.id.exiQualityVal);
        expQualityVal=findViewById(R.id.expQualityVal);

//        Governance
        exiGovtRespTime=findViewById(R.id.exiGovtRespTime);
        expGovtRespTime=findViewById(R.id.expGovtRespTime);

//        Natural Env
        availNatEnv=findViewById(R.id.availNatEnv);
        expNatEnv=findViewById(R.id.expNatEnv);

        btn_section567=findViewById(R.id.btn_section567);
        logout=findViewById(R.id.logout);


        exiQuality.setMax(max);
        exiQuality.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                exiQualityVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        expQuality.setMax(max);
        expQuality.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                expQualityVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_section567.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Quality Of Life
                exiQualityVar= Float.parseFloat(exiQualityVal.getText().toString());
                expQualityVar= Float.parseFloat(expQualityVal.getText().toString());

//                Governance
                exiGovtRespTimeVar= Float.parseFloat(exiGovtRespTime.getText().toString());
                expGovtRespTimeVar=Float.parseFloat(expGovtRespTime.getText().toString());

//                NaturalEnv
                availNatEnvVar=Float.parseFloat(availNatEnv.getText().toString());
                expNatEnvVar=Float.parseFloat(expNatEnv.getText().toString());

                rankQuality=(exiQualityVar-expQualityVar)/100;
                rankGovt=(exiGovtRespTimeVar-expGovtRespTimeVar)/100;
                rankNatEnv=(availNatEnvVar-expNatEnvVar)/100;

                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankQuality, Toast.LENGTH_SHORT).show();
                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankGovt, Toast.LENGTH_SHORT).show();
                Toast.makeText(FormSectionFiveSixSeven.this, " "+rankNatEnv, Toast.LENGTH_SHORT).show();




            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();

                editor.remove("email");
                editor.clear();
                editor.commit();


            }
        });

    }

}