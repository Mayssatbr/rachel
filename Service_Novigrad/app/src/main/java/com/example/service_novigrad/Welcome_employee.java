package com.example.service_novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome_employee extends AppCompatActivity {
    TextView text;
    private static final String TAG = "Welcome_employee";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_employee);

        Bundle name=getIntent().getExtras();
        text=(TextView)findViewById(R.id.text);
        if (name!=null) {
            text.setText(name.getString("userName"));
        }
    }
}