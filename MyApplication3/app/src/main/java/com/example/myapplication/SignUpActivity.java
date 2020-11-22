package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;




public class SignUpActivity extends AppCompatActivity {
    EditText FirstName;
    EditText LastName;
    EditText mail;
    EditText userName;
    EditText password;
    TextView Sign_in;



    Button Submit_customer, Submit_employee;





    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        FirstName = (EditText) findViewById(R.id.et_firstName);
        LastName = (EditText) findViewById(R.id.et_lastName);
        mail = (EditText) findViewById(R.id.mail);
        userName = (EditText) findViewById(R.id.et_usernameS);
        password = (EditText) findViewById(R.id.et_password);
        Submit_customer = (Button) findViewById(R.id.bt_customer);
        Submit_employee = (Button) findViewById(R.id.bt_employee);


        Sign_in = (TextView) findViewById(R.id.sign_in);


        Submit_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }

        });


        Submit_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

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




    private void register() {
        if (mail.getText().toString().isEmpty()) {
            mail.setError("enter your mail");
        }
        if (!(mail.getText().toString().trim().matches(emailPattern))) {
            mail.setError("invalid mail address");
        }



        if (userName.length() == 0) {
            userName.setError("Enter Username");
        }
        if (password.length() == 0) {
            password.setError("Enter password");
        }
        if (FirstName.length() == 0) {
            FirstName.setError("Enter First name");
        }
        if (LastName.length() == 0) {
            LastName.setError("Enter Last name");
        }
        if(((mail.getText().toString().isEmpty())&&(mail.getText().toString().trim().matches(emailPattern))&&(userName.length() == 0)&&(password.length() == 0)&&(LastName.length() == 0))){
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }



    }


}



