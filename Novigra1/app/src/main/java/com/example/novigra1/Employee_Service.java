package com.example.novigra1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Employee_Service extends AppCompatActivity {
    ListView myListView;


    ArrayList<String> services ;
    ArrayAdapter myAdapter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__service);
        services = new ArrayList<>();
        myListView = (ListView) findViewById(R.id.listView);

        db = new DBHelper(this);
        myAdapter = new ArrayAdapter<String>(
                Employee_Service.this, android.R.layout.simple_list_item_1, services);
        populateListView();
    }

    public void populateListView(){

        Cursor data = db.viewData();
        while(data.moveToNext()){
            //get the value of database in column 1
            services.add(data.getString(0));
            services.add(data.getString(1));
        }
        myAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,services);
        myListView.setAdapter(myAdapter);


    }
    public void populateDocuments(){

        Cursor data = db.viewData();
        while(data.moveToNext()){
            //get the value of database in column 1
            services.add(data.getString(0));

        }
        myAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_2,services);
        myListView.setAdapter(myAdapter);


    }
}