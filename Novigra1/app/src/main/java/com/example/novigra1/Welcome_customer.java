package com.example.novigra1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome_customer extends AppCompatActivity {
    private static final String TAG = "Welcome_cutomer";
    TextView text;
    TextView logout;
    Button services;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_customer);
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
                Intent intent=new Intent(Welcome_customer.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome_customer.this,Services.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
