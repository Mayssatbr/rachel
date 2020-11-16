package com.example.service_novigrad;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    EditText mail,password;
    Button btnLogin_client;
    Button btnLogin_employee;
    Button btn_Admin;
    Button  btnSign_up;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.et_password);
        btnSign_up=(Button) findViewById(R.id.bt_signUp);

        btnLogin_client=(Button) findViewById(R.id.customerbt);
        btnLogin_employee=(Button) findViewById(R.id.employeebt);
        btn_Admin=(Button) findViewById(R.id.bt_Administrator);






        btnLogin_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.getText().toString().isEmpty()) {
                    mail.setError("enter your mail address");
                }
                if (!(mail.getText().toString().trim().matches(emailPattern))) {
                    mail.setError("invalid mail address");
                }

                if(password.length()==0){
                    password.setError("Enter password");
                }
                if(!(mail.getText().toString().isEmpty())&&(mail.getText().toString().trim().matches(emailPattern))&&(password.length()!=0)) {
                    Intent intent = new Intent(getApplicationContext(), Welcome_cutomer.class);
                    String customer_name = mail.getText().toString();
                    intent.putExtra("userName", customer_name);
                    startActivity(intent);
                }

            }
        });


        btnLogin_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.getText().toString().isEmpty()) {
                    mail.setError("enter your mail address");
                }
                if (!(mail.getText().toString().trim().matches(emailPattern))) {
                    mail.setError("invalid mail address");
                }

                if(password.length()==0){
                    password.setError("Enter password");
                }
                if(!(mail.getText().toString().isEmpty())&&(mail.getText().toString().trim().matches(emailPattern))&&(password.length()!=0)) {
                    Intent intent = new Intent(getApplicationContext(), Welcome_cutomer.class);
                    String customer_name = mail.getText().toString();
                    intent.putExtra("userName", customer_name);
                    startActivity(intent);
                }

            }

        });

        btnSign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });







    }



}