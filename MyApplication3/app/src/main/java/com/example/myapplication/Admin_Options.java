package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Options extends AppCompatActivity {
    Button bt_services, bt_succursales, bt_clients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_options);
        bt_services = (Button) findViewById(R.id.service);
        bt_succursales = (Button) findViewById(R.id.succursale);
        bt_clients = (Button) findViewById(R.id.client);


        bt_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Services.class);
                startActivity(intent);
            }
        });

        bt_succursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Services.class);
                startActivity(intent);
            }
        });

        bt_clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Services.class);
                startActivity(intent);
            }
        });


    }
}
