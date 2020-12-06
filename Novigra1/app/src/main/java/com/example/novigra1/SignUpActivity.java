package com.example.novigra1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText FirstName,Username;
    EditText mail;

    EditText password;
    TextView Sign_in;

    DBHelper DB;

    Button SignUp;





    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        FirstName = (EditText) findViewById(R.id.et_firstName);
        mail = (EditText) findViewById(R.id.mail);
        Username = (EditText) findViewById(R.id.et_usernameS);
        password = (EditText) findViewById(R.id.et_password);
        SignUp = (Button) findViewById(R.id.signUp);

        DB = new DBHelper(this);


        Sign_in = (TextView) findViewById(R.id.sign_in);




        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                String usname = Username.getText().toString();
                if(email.equals("")||pass.equals("")){
                    Toast.makeText(SignUpActivity.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkemail = DB.checkEmail(email);
                    if(checkemail==false){
                        Boolean insert = DB.insertData(email,usname,pass);
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
