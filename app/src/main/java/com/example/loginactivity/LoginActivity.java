package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    EditText txtfname;
    TextInputLayout txtPwd;
    Button btnReg ,btnLogin,btnSkip;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtfname = (EditText) findViewById(R.id.txtfname);
        txtPwd= (TextInputLayout) findViewById(R.id.txtPwd);
        btnReg = findViewById(R.id.btnReg);
        btnLogin = findViewById(R.id.btnLogin);
        btnSkip = findViewById(R.id.btnSkip);

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
                    String fname = txtfname.getText().toString() ;
                    String password = txtPwd.getEditText().getText().toString() ;

                    Query checkuser = reference.orderByChild("fname").equalTo(fname);

                    checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                //if data exist remove error msg from edit text
                                txtfname.setError(null);
                                txtfname.setError(null);

                               String passFromDB = snapshot.child(fname).child("password").getValue(String.class);

                               if(passFromDB.equals(password)){
                                   Toast.makeText(getApplicationContext(),"login successfull!!",Toast.LENGTH_SHORT).show();
                                   //get user data into shared preference.
                                   String fnameFromDB = snapshot.child(fname).child("fname").getValue(String.class);
                                   String lnameFromDB = snapshot.child(fname).child("lname").getValue(String.class);
                                   String emailFromDB = snapshot.child(fname).child("email").getValue(String.class);
                                   String ageFromDB = snapshot.child(fname).child("age").getValue(String.class);
                                   String genderFromDB = snapshot.child(fname).child("gender").getValue(String.class);
                                   String id = snapshot.child(fname).child("id").getValue(String.class);

                                   //store data in shared pref
                                   // Storing data into SharedPreferences
                                   SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                                   // Creating an Editor object to edit(write to the file)
                                   SharedPreferences.Editor myEdit = sharedPreferences.edit();

                                   // Storing the key and its value as the data fetched from edittext
                                  myEdit.putString("fname", fnameFromDB);
                                  myEdit.putString("lname", lnameFromDB);
                                   myEdit.putString("email", emailFromDB);
                                   myEdit.putString("age", ageFromDB);
                                  myEdit.putString("gender", genderFromDB);
                                   myEdit.putString("id", id);

                                   // Once the changes have been made,
                                   // we need to commit to apply those changes made,
                                   // otherwise, it will throw an error
                                   myEdit.commit();



                                   Intent Terms = new Intent(getApplicationContext(),Terms.class);
                                   startActivity(Terms);
                               }
                               else{
                                   txtPwd.setError("Wrong Password");
                                   txtPwd.requestFocus();
                               }
                            }
                            else
                            {
                                txtfname.setError("User does not exist!");
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
}