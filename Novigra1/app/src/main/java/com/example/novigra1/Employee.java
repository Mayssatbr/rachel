package com.example.novigra1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Employee extends AppCompatActivity {
    private static final String TAG = "ClientList";
    RecyclerView myRecyclyingView;
    ArrayList<String> employeeName, succursaleName;
    DBHelper DB;
    CustomAdapter customAdapt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        myRecyclyingView = findViewById(R.id.clientList);
        employeeName = new ArrayList<>();
        succursaleName = new ArrayList<>();
        DB = new DBHelper(this);

        populateListView();
        customAdapt = new CustomAdapter(Employee.this,Employee.this,succursaleName,employeeName);
        myRecyclyingView.setAdapter(customAdapt);
        myRecyclyingView.setLayoutManager(new LinearLayoutManager(Employee.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            //will refresh
            recreate();
        }
    }

    public void populateListView(){

        Cursor data = DB.getListContent();
        if(data.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            while (data.moveToNext()) {
                //get the value of database in column 1
                succursaleName.add(data.getString(1));
               employeeName.add(data.getString(4));
            }

        }

    }


}


