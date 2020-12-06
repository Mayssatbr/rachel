package com.example.novigra1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Client extends AppCompatActivity {
    private static final String TAG = "ClientList";
    RecyclerView myRecyclyingView;
    ArrayList<String> nameData, emailData;
    DBHelper DB;
    CustomAdapter customAdapt;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list);

        myRecyclyingView = findViewById(R.id.clientList);
        nameData = new ArrayList<>();
        emailData = new ArrayList<>();
        DB = new DBHelper(this);

        populateListView();
        customAdapt = new CustomAdapter(Client.this,Client.this,nameData,emailData);
        myRecyclyingView.setAdapter(customAdapt);
        myRecyclyingView.setLayoutManager(new LinearLayoutManager(this));


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
                nameData.add(data.getString(0));
                emailData.add(data.getString(1));
            }

        }

    }



}
