package com.example.service_novigrad;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class SignUp extends AppCompatActivity {
    EditText FirstName, LastName, mail, userName, password;

    private static final String TAG = "SignUp";

    Button Submit_customer, Submit_employee;

    private FirebaseAuth firebaseAuth;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth=FirebaseAuth.getInstance();
        FirstName = (EditText) findViewById(R.id.et_firstName);
        LastName = (EditText) findViewById(R.id.et_lastName);
        mail = (EditText) findViewById(R.id.mail);
        userName = (EditText) findViewById(R.id.et_usernameS);
        password = (EditText) findViewById(R.id.et_password);



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
                if ((!(mail.getText().toString().isEmpty())) && (mail.getText().toString().trim().matches(emailPattern)) && (userName.length() != 0) && (password.length() != 0) && (FirstName.length() != 0) && (LastName.length() != 0) && (mail.length() != 0)) {

                    Intent intent = new Intent(getApplicationContext(), Login.class);

                    startActivity(intent);
                    finish();


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
                    firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).adOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUp.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            Intent intent = new Intent(getApplicationContext(), Login.class);

                    startActivity(intent);
                    finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUp.this, "failed to create Account", Toast.LENGTH_SHORT).show();
                        }
                    });



                }



            }
        });



    }




                private void updateUI(FirebaseUser user) {
                    if (user != null) {
                        Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }




    }
}

