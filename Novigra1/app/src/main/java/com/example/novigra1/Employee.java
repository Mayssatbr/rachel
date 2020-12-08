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
    ArrayList<String> employeeName, succursaleName,succursaleTime;
    DBHelper DB;
    SuccursaleAdapter succursaleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        myRecyclyingView = findViewById(R.id.EmployeeList);
        employeeName = new ArrayList<>();
        succursaleName = new ArrayList<>();
        succursaleTime = new ArrayList<>();

        DB = new DBHelper(this);

        populateListView();
        succursaleAdapter = new SuccursaleAdapter(Employee.this,this,succursaleName,employeeName,succursaleTime);
        myRecyclyingView.setAdapter(succursaleAdapter);
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

        Cursor data = DB.getListContentEmployee();
        if(data.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            while (data.moveToNext()) {
                //get the value of database in column 1
                succursaleName.add(data.getString(3));
               employeeName.add(data.getString(0));
               succursaleTime.add(data.getString(4));
            }

        }

    }


}


