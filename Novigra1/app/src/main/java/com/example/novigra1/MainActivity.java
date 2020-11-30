package com.example.novigra1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mail,password;
    Button btnLogin_client, btnLogin_employee, btn_Admin;

    DBHelper DB;


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

        DB = new DBHelper(this);




        btnLogin_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                if(email.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemailpass = DB.checkEmilPassword(email,pass);
                    if((checkemailpass==true)&&(email!="Admin1234@gmail.com")){
                        Toast.makeText(MainActivity.this,"Sign in Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Welcome_customer.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();

                    }
                }

            }

        });


        btnLogin_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                if(email.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemailpass = DB.checkEmilPassword(email,pass);
                    if((checkemailpass==true)&&(email!="Admin1234@gmail.com")){
                        Toast.makeText(MainActivity.this,"Sign in Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Welcome_employee.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();

                    }
                }


            }



        });

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
        btn_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "Admin1234@gmail.com";
                String pass = "admin1234";
                if(email.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this,"Please Enter all fields",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemailpass = DB.checkEmilPassword(email,pass);
                    if(checkemailpass==true){
                        Toast.makeText(MainActivity.this,"Sign in Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Admin_Options.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

    }


}