package com.example.novigra1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteData extends AppCompatActivity {
    private static final String TAG = "DeleteData";
    Button delete ;
    EditText viewEditText;
    String nameSelected;
    Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_client);

        delete =(Button) findViewById(R.id.del);

        viewEditText = (EditText) findViewById(R.id.deleteEdit);
        client = new Client();

        //get the intent extra from Client class
        Intent receivedIntent = getIntent();
        nameSelected = receivedIntent.getStringExtra("username");
        viewEditText.setText(nameSelected);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();

            }
        });



    }
    // Dialog ask Admin if he wants to remove
    void confirmDialog(){
        //get the intent extra from Client class
        Intent receivedIntent = getIntent();
        nameSelected = receivedIntent.getStringExtra("username");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+nameSelected+" ?");
        builder.setMessage("Are you sure you want to delete "+ nameSelected +" ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DBHelper DB = new DBHelper(DeleteData.this);
                Boolean returnAns = DB.deleteClient(nameSelected);

                if(returnAns == true){
                    viewEditText.setText("");
                    Toast.makeText(DeleteData.this,nameSelected +" is removed ",Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(DeleteData.this,nameSelected +" not removed",Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();
    }



}

