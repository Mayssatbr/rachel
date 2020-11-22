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



public class MainActivity extends AppCompatActivity {
    EditText mail,password;
    Button btnLogin_client;
    Button btnLogin_employee;
    Button btn_Admin;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private TextView Sign_up;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.et_password);

        btnLogin_client=(Button) findViewById(R.id.customerbt);
        btnLogin_employee=(Button) findViewById(R.id.employeebt);
        btn_Admin=(Button) findViewById(R.id.bt_Administrator);

        Sign_up = (TextView) findViewById(R.id.sign_up);



        btnLogin_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.getText().toString().isEmpty()) {
                    mail.setError("enter your mail");
                }
                if (!(mail.getText().toString().trim().matches(emailPattern))) {
                    mail.setError("invalid mail address");
                }

                if (password.length() == 0) {
                    password.setError("Enter password");
                }
                if(((mail.getText().toString().isEmpty())&&(mail.getText().toString().trim().matches(emailPattern))&&(password.length() == 0))) {
                    Intent intent = new Intent(MainActivity.this, Welcome_customer.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        btnLogin_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.getText().toString().isEmpty()) {
                    mail.setError("enter your mail");
                }
                if (!(mail.getText().toString().trim().matches(emailPattern))) {
                    mail.setError("invalid mail address");
                }

                if (password.length() == 0) {
                    password.setError("Enter password");
                }
                if(((mail.getText().toString().isEmpty())&&(mail.getText().toString().trim().matches(emailPattern))&&(password.length() == 0))) {
                    Intent intent = new Intent(MainActivity.this, Welcome_employee.class);
                    startActivity(intent);
                    finish();
                }
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
                Intent intent=new Intent(MainActivity.this, Admin_Options.class);
                startActivity(intent);
                finish();

            }
        });

    }


}