package com.example.novigra1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteSuccursale extends AppCompatActivity {
    Button delete ;
    EditText viewEditText;
    String nameSelected;
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_succursale);

        delete =(Button) findViewById(R.id.del);

        viewEditText = (EditText) findViewById(R.id.deleteEditEmployee);
        client = new Client();

        //get the intent extra from Client class
        Intent receivedIntent = getIntent();
        nameSelected = receivedIntent.getStringExtra("employee");
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
        nameSelected = receivedIntent.getStringExtra("employee");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+nameSelected+" ?");
        builder.setMessage("Are you sure you want to delete "+ nameSelected +" ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DBHelper DB = new DBHelper(DeleteSuccursale.this);
                Boolean returnAns = DB.deleteEmployee(nameSelected);

                if(returnAns == true){
                    viewEditText.setText("");
                    Toast.makeText(DeleteSuccursale.this,nameSelected +" is removed ",Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(DeleteSuccursale.this,nameSelected +" not removed",Toast.LENGTH_SHORT).show();

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