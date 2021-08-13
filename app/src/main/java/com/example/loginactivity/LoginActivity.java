package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements LocationListener {

    EditText txtfname;
    TextInputLayout txtPwd;
    Button btnReg ,btnLogin,btnSkip;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtfname = (EditText) findViewById(R.id.txtfname);
        txtPwd= (TextInputLayout) findViewById(R.id.txtPwd);
        btnReg = findViewById(R.id.btnReg);
        btnLogin = findViewById(R.id.btnLogin);
        btnSkip = findViewById(R.id.btnSkip);



        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SectionOneNoLogin.class));
            }
        });



        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(Register);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateform())
                {

                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");




                    //get all values from edit Text
                    String fname = txtfname.getText().toString().trim() ;
                    String password = txtPwd.getEditText().getText().toString().trim();


                    Query checkuser = reference.orderByChild("userid").equalTo(fname);

                 //   Query checkuser = reference.orderByChild("fname").equalTo(fname);
                  //  Query checkuser = reference.orderByChild("id").equalTo(id);
                  //  Query pass = reference.orderByChild("password").equalTo(password);

                               checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists())
                                        {
                                            //if data exist remove error msg from edit text
                                            txtfname.setError(null);
                                            txtfname.setError(null);

                                            String passFromDB =snapshot.child(fname).child("password").getValue(String.class);
                                            if(passFromDB.equals(password)){
                                                Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();

                                                SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                                                SharedPreferences.Editor editor=preferences.edit();

                                                editor.putString("userID",fname);
                                                editor.commit();


                                                if(ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                                                        && ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
                                                {
                                                    ActivityCompat.requestPermissions(LoginActivity.this,new String[]{
                                                            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
                                                    },100);
                                                }else{
                                                    getLocation();
                                                }


//                                                finish();

                                            }
                                            else{
                                                txtPwd.setError("Wrong Password");
                                                txtPwd.requestFocus();
                                            }
                                        }
                                        else
                                        {
                                            txtfname.setError("Invalid user!");
                                            txtfname.requestFocus();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });








                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,5000,LoginActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean validateform(){
        String fname = txtfname.getText().toString() ;
        String password = txtPwd.getEditText().getText().toString() ;

        if(fname.isEmpty()){
            txtfname.setError("Field cannot be empty");
            return false;
        }

        if(password.isEmpty()){
            txtPwd.setError("Field cannot be empty");
            return false;
        }
        else if (password.length() < 8) {
            txtPwd.setError("Password must be minimum 8 characters");
            return false;
        }

        // after all validation return true.
        return true;

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        double lat = location.getLatitude();
        double lng= location.getLongitude();
//                            latitude.setText(String.format("%s", lat));
//                            longitude.setText(String.format("%s",longitude1));

        //Toast.makeText(getApplicationContext()," "+lat+" "+longitude1, Toast.LENGTH_SHORT).show();

        SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
        String userID = preferences.getString("userID", "");
        RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
        reference = RootNode.getReference("users");

        reference.child(userID).child("latitude").setValue(lat);  //17.319401181464258, 78.40302230454013
        reference.child(userID).child("longitude").setValue(lng);

        Toast.makeText(getApplicationContext(),"Login successfull!!",Toast.LENGTH_SHORT).show();
        Intent Terms = new Intent(getApplicationContext(),FormSectionOne.class);
        startActivity(Terms);
        finish();


    }
}

