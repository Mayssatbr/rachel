package com.example.service_novigrad;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class SignUp extends AppCompatActivity {
    EditText FirstName, LastName, mail, userName, password;

    private static final String TAG = "SignUp";

    Button Submit_customer, Submit_employee;

    ProgressBar progressBar;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirstName = (EditText) findViewById(R.id.et_firstName);
        LastName = (EditText) findViewById(R.id.et_lastName);
        mail = (EditText) findViewById(R.id.mail);
        userName = (EditText) findViewById(R.id.et_usernameS);
        password = (EditText) findViewById(R.id.et_password);

        String email = mail.getText().toString().trim();
        String passw = password.getText().toString().trim();


        Submit_customer = (Button) findViewById(R.id.bt_customer);
        Submit_employee = (Button) findViewById(R.id.bt_employee);


        Submit_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.getText().toString().isEmpty()) {
                    mail.setError("enter your mail address");
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


            }

        });


        Submit_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.getText().toString().isEmpty()) {
                    mail.setError("enter your mail");
                } else {
                    if (!(mail.getText().toString().trim().matches(emailPattern))) {
                        mail.setError("invalid mail address");
                    }
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


                if ((!(mail.getText().toString().isEmpty())) && (mail.getText().toString().trim().matches(emailPattern)) && (userName.length() != 0) && (password.length() != 0) && (FirstName.length() != 0) && (LastName.length() != 0) && (mail.length() != 0)) {

                    Intent intent = new Intent(getApplicationContext(), Login.class);

                    startActivity(intent);
                    finish();


                }


            }
        });
    }
}

