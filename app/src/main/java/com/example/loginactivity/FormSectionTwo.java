package com.example.loginactivity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class FormSectionTwo extends AppCompatActivity {
    //      Education
    SeekBar exiEduFacPub,expEduFacPub,exiEduFacPri,expEduFacPri;
    TextView exiEduFacPubVal,expEduFacPubVal,exiEduFacPriVal,expEduFacPriVal;
    float exiEduFacPubVar,expEduFacPubVar,exiEduFacPriVar,expEduFacPriVar;
    //    Transport
    TextView availPubTrans,expPubTrans,availPriTrans,expPriTrans;
    float availPubTransVar,expPubTransVar,availPriTransVar,expPriTransVar;
    //    Security and Salary
    TextView availPolStation,expPolStation,exiSal,expSal;
    float availPolStationVar,expPolStationVar,exiSalVar,expSalVar;

    Float rankPubEdu,rankPriEdu,rankPubTrans,rankPriTrans,rankPolice,rankSal;

    int max=10;

    Button btn_section2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_section_two);
//        Education
        exiEduFacPub= findViewById(R.id.exiEduFacPub);
        expEduFacPub=findViewById(R.id.expEduFacPub);
        exiEduFacPri=findViewById(R.id.exiEduFacPri);
        expEduFacPri=findViewById(R.id.expEduFacPri);

        exiEduFacPubVal=findViewById(R.id.exiEduFacPubVal);
        expEduFacPubVal=findViewById(R.id.expEduFacPubVal);
        exiEduFacPriVal=findViewById(R.id.exiEduFacPriVal);
        expEduFacPriVal=findViewById(R.id.expEduFacPriVal);


        exiEduFacPub.setMax(max);
        expEduFacPub.setMax(max);
        exiEduFacPri.setMax(max);
        expEduFacPri.setMax(max);

//        Transport

        availPubTrans=findViewById(R.id.availPubTrans);
        expPubTrans=findViewById(R.id.expPubTrans);
        availPriTrans=findViewById(R.id.availPriTrans);
        expPriTrans=findViewById(R.id.expPriTrans);

//        Security and Salary
        availPolStation=findViewById(R.id.availPolStation);
        expPolStation=findViewById(R.id.expPolStation);
        exiSal=findViewById(R.id.exiSal);
        expSal=findViewById(R.id.expSal);

        btn_section2=findViewById(R.id.btn_section2);



        exiEduFacPub.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                exiEduFacPubVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        expEduFacPub.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                expEduFacPubVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        exiEduFacPri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                exiEduFacPriVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        expEduFacPri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int changedValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                changedValue=progress;

                expEduFacPriVal.setText(String.valueOf(changedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        btn_section2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Education
                exiEduFacPubVar= Float.parseFloat(exiEduFacPubVal.getText().toString());
                expEduFacPubVar= Float.parseFloat(expEduFacPubVal.getText().toString());
                exiEduFacPriVar= Float.parseFloat(exiEduFacPriVal.getText().toString());
                expEduFacPriVar= Float.parseFloat(expEduFacPriVal.getText().toString());

//                Transport
                availPubTransVar= Float.parseFloat(availPubTrans.getText().toString());
                expPubTransVar= Float.parseFloat(expPubTrans.getText().toString());
                availPriTransVar= Float.parseFloat(availPriTrans.getText().toString());
                expPriTransVar= Float.parseFloat(expPriTrans.getText().toString());

//                Police and Salary
                availPolStationVar= Float.parseFloat(availPolStation.getText().toString());
                expPolStationVar= Float.parseFloat(expPolStation.getText().toString());
                exiSalVar= Float.parseFloat(exiSal.getText().toString());
                expSalVar=Float.parseFloat(expSal.getText().toString());

                rankPubEdu=(exiEduFacPubVar-expEduFacPubVar)/100;
                rankPriEdu=(exiEduFacPriVar-expEduFacPriVar)/100;

                rankPubTrans=(availPubTransVar-expPubTransVar)/100;
                rankPriTrans=(availPriTransVar-expPriTransVar)/100;

                rankPolice=(availPolStationVar-expPolStationVar)/100;
                rankSal=(exiSalVar-expSalVar)/100;



                Toast.makeText(getApplicationContext(), "rankPubEdu "+rankPubEdu, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "rankPriEdu "+rankPriEdu, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "rankPubTrans "+rankPubTrans, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "rankPriTrans "+rankPriTrans, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "rankPolice "+rankPolice, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "rankSal "+rankSal, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),FormSectionThree.class));
            }
        });
    }
}