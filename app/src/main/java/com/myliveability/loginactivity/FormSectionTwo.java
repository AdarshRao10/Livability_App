package com.myliveability.loginactivity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormSectionTwo extends AppCompatActivity {
    //      Education
    SeekBar exiEduFacPub,expEduFacPub,exiEduFacPri,expEduFacPri;
    TextView exiEduFacPubVal,expEduFacPubVal,exiEduFacPriVal,expEduFacPriVal;
    float exiEduFacPubVar,expEduFacPubVar,exiEduFacPriVar,expEduFacPriVar;
    //    Transport
    EditText availPubTrans,expPubTrans,availPriTrans,expPriTrans;
    float availPubTransVar,expPubTransVar,availPriTransVar,expPriTransVar;
    //    Security and Salary
    EditText availPolStation,expPolStation,exiSal,expSal;
    float availPolStationVar,expPolStationVar,exiSalVar,expSalVar;

    Float rankPubEdu,rankPriEdu,rankPubTrans,rankPriTrans,rankPolice,rankSal;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    int max=10;

    Button btn_section2,btn_section2_skip;

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
        btn_section2_skip=findViewById(R.id.btn_section2_skip);



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

                if(validateform())
                {
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

                    //get data from shared preference
                    SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                    String userID = preferences.getString("userID", "");

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
//                    String s1 = sh.getString("fname", "");



                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    SectionTwoHelper sectionTwoHelper = new SectionTwoHelper( exiEduFacPubVar,  expEduFacPubVar,  exiEduFacPriVar,  expEduFacPriVar,  availPubTransVar,  expPubTransVar,  availPriTransVar,  expPriTransVar,  availPolStationVar,  expPolStationVar,  exiSalVar,  expSalVar);

                    reference.child(userID).child("section2").setValue(sectionTwoHelper);

                    // to append data
//                HashMap<String,Object> values = new HashMap<>();
//                values.put("waterAvailability",waterAvailability);
//                reference.child(s1).updateChildren(values);

                    Toast.makeText(getApplicationContext(), "Section 2 complete", Toast.LENGTH_SHORT).show();



//                Toast.makeText(getApplicationContext(), "rankPubEdu "+rankPubEdu, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPriEdu "+rankPriEdu, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPubTrans "+rankPubTrans, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPriTrans "+rankPriTrans, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankPolice "+rankPolice, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "rankSal "+rankSal, Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),FormSectionThree.class));

                }else
                {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }




            }
        });

        btn_section2_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormSectionThree.class));
            }
        });
    }

    private boolean validateform() {
        String exiEduFacPub=exiEduFacPubVal.getText().toString();
        String expEduFacPub=expEduFacPubVal.getText().toString();
        String exiEduFacPri =exiEduFacPriVal.getText().toString();
        String expEduFacPri=expEduFacPriVal.getText().toString();
        String availPub= availPubTrans.getText().toString();
        String expPub = expPubTrans.getText().toString();
        String availPri=availPriTrans.getText().toString();
        String expPri =expPriTrans.getText().toString();
        String availPol=availPolStation.getText().toString();
        String expPol=expPolStation.getText().toString();
        String exiSa=exiSal.getText().toString();
        String expSa=expSal.getText().toString();

        if(exiEduFacPub.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Public Education field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(expEduFacPub.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Public Education field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(exiEduFacPri.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Private Education field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(expEduFacPri.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Private Education field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(availPub.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Public Transport field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(expPub.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Public Transport field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(availPri.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Private Transport field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(expPri.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Private Transport field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(availPol.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Police Statn field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(expPol.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Police Statn field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(exiSa.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Salary field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(expSa.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Salary field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }



    }

}








