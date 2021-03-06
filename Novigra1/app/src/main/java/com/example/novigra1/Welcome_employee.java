package com.example.novigra1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome_employee extends AppCompatActivity {
    TextView text;
    TextView logout;
    Button services;
    private static final String TAG = "Welcome_employee";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_employee);
        logout=findViewById(R.id.logout);
        Bundle name=getIntent().getExtras();
        text=(TextView)findViewById(R.id.text);
        services=(Button)findViewById(R.id.services);
        if (name!=null) {
            text.setText(name.getString("userName"));
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome_employee.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome_employee.this,Employee_Service.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
