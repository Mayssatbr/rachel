package com.example.service_novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome_cutomer extends AppCompatActivity {
    private static final String TAG = "Welcome_cutomer";
    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_cutomer);

        Bundle name=getIntent().getExtras();
        text=(TextView)findViewById(R.id.text);
        if (name!=null) {
            text.setText(name.getString("userName"));
        }


    }
}