package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    EditText mail,password;
    Button btnLogin_client;
    Button btnLogin_employee;
    Button btn_Admin;
    ProgressBar mProgress_bar;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth firebaseAuth;
    private TextView Sign_up;
    boolean test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.et_password);

        btnLogin_client=(Button) findViewById(R.id.customerbt);
        btnLogin_employee=(Button) findViewById(R.id.employeebt);
        btn_Admin=(Button) findViewById(R.id.bt_Administrator);
        firebaseAuth= FirebaseAuth.getInstance();
        Sign_up = (TextView) findViewById(R.id.sign_up);
        mProgress_bar=(ProgressBar) findViewById(R.id.loading_progressBar);


        btnLogin_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_customer();

            }
        });


        btnLogin_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_employee();
            }
            

        });

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btn_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_adm();

            }
        });

    }
    private void inProgress(boolean test){
        if(test){
            mProgress_bar.setVisibility(View.VISIBLE);
            btn_Admin.setEnabled(false);
            Sign_up.setEnabled(false);
            btnLogin_client.setEnabled(false);
            btnLogin_employee.setEnabled(false);
        }else{
            mProgress_bar.setVisibility(View.GONE);
            btn_Admin.setEnabled(true);
            Sign_up.setEnabled(true);
            btnLogin_client.setEnabled(true);
            btnLogin_employee.setEnabled(true);
        }
    }
    private void login_adm() {
            inProgress(true);
            firebaseAuth.signInWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Successfully connected", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,Admin_Options.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, "Error!can't connect!!", Toast.LENGTH_LONG).show();
                    }
                }

            });


    }

    private void login_employee() {

        if(!(mail.getText().toString().isEmpty())&&(mail.getText().toString().trim().matches(emailPattern))&&(password.length()!=0)) {
            inProgress(true);
            firebaseAuth.signInWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "Successfully connected", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Welcome_employee.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, "Error!can't connect!!", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }


    }
    private void login_customer() {

        if(!(mail.getText().toString().isEmpty())&&(mail.getText().toString().trim().matches(emailPattern))&&(password.length()!=0)) {
            inProgress(true);
            firebaseAuth.signInWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "Successfully connected", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Welcome_customer.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, "Error!can't connect!!", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }


    }


}