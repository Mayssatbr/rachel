package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText FirstName;
    EditText LastName;
    EditText mail;
    EditText userName;
    EditText password;
    TextView Sign_in;

    private static final String TAG = "SignUp";

    Button Submit_customer, Submit_employee;

    private FirebaseAuth firebaseAuth;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ProgressBar progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firebaseAuth=FirebaseAuth.getInstance();
        FirstName = (EditText) findViewById(R.id.et_firstName);
        LastName = (EditText) findViewById(R.id.et_lastName);
        mail = (EditText) findViewById(R.id.mail);
        userName = (EditText) findViewById(R.id.et_usernameS);
        password = (EditText) findViewById(R.id.et_password);
        Submit_customer = (Button) findViewById(R.id.bt_customer);
        Submit_employee = (Button) findViewById(R.id.bt_employee);
        Sign_in = (TextView) findViewById(R.id.sign_in);
        progressDialog = new ProgressBar(this);

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
                if ((!(mail.getText().toString().isEmpty())) && (mail.getText().toString().trim().matches(emailPattern)) && (userName.length() != 0) && (password.length() != 0) && (FirstName.length() != 0) && (LastName.length() != 0) && (mail.length() != 0)) {
                    register();


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
                }


            }
        });
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }

    private void register() {
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
        firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(SignUpActivity.this,"Successfully registred", Toast.LENGTH_LONG);
                   Intent intent=new Intent(SignUpActivity.this,DashboardActivity.class);
                   startActivity(intent);
                   finish();
               }
               else{
                   Toast.makeText(SignUpActivity.this,"Sing up failed!", Toast.LENGTH_LONG);
               }
            }

        });

    }


}




