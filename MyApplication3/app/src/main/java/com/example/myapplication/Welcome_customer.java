package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome_customer extends AppCompatActivity {
    private static final String TAG = "Welcome_cutomer";
    TextView text;
    TextView logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_customer);
        logout=findViewById(R.id.logout);
        Bundle name=getIntent().getExtras();
        text=(TextView)findViewById(R.id.text);
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


    }
}