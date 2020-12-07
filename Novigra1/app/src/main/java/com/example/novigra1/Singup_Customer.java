package com.example.novigra1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Singup_Customer extends AppCompatActivity {
    EditText Username;
    EditText mail;

    EditText password;
    TextView Sign_in;
    EditText horaire, adresse, type, nom;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Intent intent;


    DBHelper DB;

    Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup__customer);
        mail = (EditText) findViewById(R.id.email);
        Username = (EditText) findViewById(R.id.usernameE);
        password = (EditText) findViewById(R.id.password);
        SignUp = (Button) findViewById(R.id.signUpE);

        DB = new DBHelper(this);


        Sign_in = (TextView) findViewById(R.id.sign_inE);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailE = mail.getText().toString();
                String passE = password.getText().toString();
                String usnameE = Username.getText().toString();

                if (emailE.equals("") || passE.equals("")) {
                    Toast.makeText(Singup_Customer.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkemail = DB.checkEmail(emailE);
                    if (checkemail == false) {
                        Boolean insert = DB.insertData(emailE, usnameE, passE);
                        if (insert == true) {
                            Toast.makeText(Singup_Customer.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            startActivity(intent);

                        } else {
                            Toast.makeText(Singup_Customer.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Singup_Customer.this, "email already exist! Sign in :)", Toast.LENGTH_SHORT).show();
                    }


                }

            }

        });


        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Singup_Customer.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}




















