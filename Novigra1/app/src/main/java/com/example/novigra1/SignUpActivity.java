package com.example.novigra1;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    TextView Sign_in;
    EditText Username, mail, star,end,password, adresse, type ,nom;

    TimePickerDialog timePickerDialog;


    DBHelper DB;
    Button SignUp;

    String ampm;





    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        mail = (EditText) findViewById(R.id.mail);
        Username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.et_password);
        SignUp = (Button) findViewById(R.id.signUp);
        star = (EditText) findViewById(R.id.startTime);
        end = (EditText) findViewById(R.id.endTime);
        adresse = (EditText) findViewById(R.id.adresse);
        type = (EditText) findViewById(R.id.typeService);
        nom = (EditText) findViewById(R.id.nomsuccursale);
        DB = new DBHelper(this);


        Sign_in = (TextView) findViewById(R.id.sign_in);

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(SignUpActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay>=12){
                            ampm = "PM";
                        }else{
                            ampm = "AM";
                        }
                        star.setText(String.format("%02d:%02d", hourOfDay, minute) + ampm);
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(SignUpActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay>=12){
                            ampm = "PM";
                        }else{
                            ampm = "AM";
                        }
                        end.setText(String.format("%02d:%02d", hourOfDay, minute) + ampm);
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });




        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                String usname = Username.getText().toString();
                String time = "Monday to Saturday from "+star.getText().toString()+" to "+end.getText().toString();
                String address= adresse.getText().toString();
                String typeSucc = type.getText().toString();
                String name = nom.getText().toString();
                if (!email.matches(emailPattern)){
                    mail.setError("Invalid Email");
                }
                if(email.equals("")||pass.equals("")){
                    Toast.makeText(SignUpActivity.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkemail = DB.checkEmailEmployee(email);
                    if(checkemail==false){
                        Boolean insert = DB.insertData_Employee(usname,email,pass,name,time,address,typeSucc);
                        if(insert==true){
                            Toast.makeText(SignUpActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(SignUpActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this,"email already exist! Sign in :)",Toast.LENGTH_SHORT).show();
                    }


                }

            }

        });



        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }







}
