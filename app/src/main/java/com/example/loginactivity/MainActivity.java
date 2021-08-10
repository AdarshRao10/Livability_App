package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtfname,txtlname,txtEmail,txtAge,txtGender,txtQualify,txtprof,txtPurpose,et_day,et_month,et_year;
    TextInputLayout txtPwd;
    Button btnReg ,btnLogin;
    String[] GenderType;
    Spinner spinner;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        txtfname = findViewById(R.id.txtfname);
        txtlname=findViewById(R.id.txtlname);
        txtEmail=findViewById(R.id.txtEmail);
        txtAge=findViewById(R.id.txtAge);
       // txtGender=findViewById(R.id.txtGender);
        txtQualify=findViewById(R.id.txtQualify);
        txtprof=findViewById(R.id.txtprof);
        txtPurpose=findViewById(R.id.txtPurpose);
        txtPwd=findViewById(R.id.txtPwd);
        btnReg = findViewById(R.id.btnReg);
        et_day=findViewById(R.id.et_day);
        et_month=findViewById(R.id.et_month);
        et_year=findViewById(R.id.et_year);
        btnLogin = findViewById(R.id.btnLogin);

        // Spinner element
         spinner =findViewById(R.id.spinner);

        //get array list from string.xml
        GenderType = getResources().getStringArray(R.array.gender);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.item_drop_down,GenderType);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.item_drop_down);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);




        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateform())
                {
                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    //get all values from edit Text
                    String fname = txtfname.getText().toString() ;
                    String lname = txtlname.getText().toString() ;
                    String email = txtEmail.getText().toString() ;
                    String age = txtAge.getText().toString() ;
                    String gender = spinner.getSelectedItem().toString();
                    String qualification = txtQualify.getText().toString() ;
                    String profession = txtprof.getText().toString() ;
                    String purpose = txtPurpose.getText().toString() ;
                    String day= et_day.getText().toString();
                    String month=et_month.getText().toString();
                    String year = et_year.getText().toString();
                    String password =year+month+day;
                    //String password = txtPwd.getEditText().getText().toString() ;

                    //get uniqueID
                    String postID = reference.push().getKey();

                    //save id in sharedpref
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref2",MODE_PRIVATE);

                    // Creating an Editor object to edit(write to the file)
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    // Storing the key and its value as the data fetched from edittext
                    myEdit.putString("Id", postID);


                    // Once the changes have been made,
                    // we need to commit to apply those changes made,
                    // otherwise, it will throw an error
                    myEdit.commit();

                    UserHelperClass helperClass = new UserHelperClass(postID ,fname, lname, email, age, gender,  qualification, profession,  purpose, password);

                    reference.child(postID).setValue(helperClass);
                   // reference.child(fname).setValue(helperClass);
                   // reference.push().setValue(helperClass);
                    Toast.makeText(getApplicationContext(),postID,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Registration successfull!!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(Login);
            }
        });

    }
    private boolean validateform(){
        String fname = txtfname.getText().toString() ;
        String lname = txtlname.getText().toString() ;
        String email = txtEmail.getText().toString() ;
        String age = txtAge.getText().toString() ;
        String gender = spinner.getSelectedItem().toString() ;
        String qualification = txtQualify.getText().toString() ;
        String profession = txtprof.getText().toString() ;
        String purpose = txtPurpose.getText().toString() ;
        String day= et_day.getText().toString();
        String month=et_month.getText().toString();
        String year = et_year.getText().toString();
        String password =day+month+year;
       // String password = txtPwd.getEditText().getText().toString() ;

        if(fname.isEmpty()){
            txtfname.setError("Field cannot be empty");
            return false;
        }
        if(lname.isEmpty()){
            txtlname.setError("Field cannot be empty");
            return false;
        }
        if(email.isEmpty()){
            txtEmail.setError("Field cannot be empty");
            return false;
        }

         if (!email.trim().matches(emailPattern)) {
            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
             txtEmail.setError("Invalid Email Address");
        }

        if(age.isEmpty()){
            txtAge.setError("Field cannot be empty");
            return false;
        }
        if(gender.isEmpty()){
            txtGender.setError("Field cannot be empty");
            return false;
        }
        if(qualification.isEmpty()){
            txtQualify.setError("Field cannot be empty");
            return false;
        }
        if(profession.isEmpty()){
            txtprof.setError("Field cannot be empty");
            return false;
        }
        if(purpose.isEmpty()){
            txtPurpose.setError("Field cannot be empty");
            return false;
        }

        if(year.isEmpty()){
            et_year.setError("Year cannot be empty");
        }else if(year.length()!=4){
            et_year.setError("Enter correct year");
        }

        if(month.isEmpty()){
            et_year.setError("Year cannot be empty");
        }else if(Integer.parseInt(month)>12 && Integer.parseInt(month)<0){
            et_year.setError("Enter correct month");
        }

        if(day.isEmpty()){
            et_year.setError("day cannot be empty");
        }else if(Integer.parseInt(day)>31 && Integer.parseInt(day)<0){
            et_year.setError("Enter correct day");
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