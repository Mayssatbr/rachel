package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome_customer extends AppCompatActivity {
    private static final String TAG = "Welcome_cutomer";
    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_customer);

        Bundle name=getIntent().getExtras();
        text=(TextView)findViewById(R.id.text);
        if (name!=null) {
            text.setText(name.getString("userName"));
        }


    }
}