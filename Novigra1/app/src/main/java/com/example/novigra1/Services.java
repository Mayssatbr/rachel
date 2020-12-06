package com.example.novigra1;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ComponentActivity;

import java.util.ArrayList;
import  androidx.appcompat.widget.AlertDialogLayout;

import static androidx.appcompat.app.AlertDialog.*;

public class Services extends AppCompatActivity {
    Toolbar toolbar;


    ListView myListView;
    EditText serviceInput;
    TextView type;
    Button addValue, delete, show;

    ArrayList<String> services ;
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;
    DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        type = (TextView) findViewById(R.id.type);
        services = new ArrayList<>();
        myListView = (ListView) findViewById(R.id.listView);
        serviceInput = (EditText) findViewById(R.id.editServiceName);
        addValue = (Button) findViewById(R.id.addButton);
        delete = (Button) findViewById(R.id.deleteButton);
        db = new DBHelper(this);
        myAdapter = new ArrayAdapter<String>(
                Services.this, android.R.layout.simple_list_item_1, services);

        populateListView();
        addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName;

                serviceName = serviceInput.getText().toString();


                if ((serviceName.length() != 0)) {
                    services.clear();
                    AddService();
                    populateListView();


                } else {
                    Toast.makeText(Services.this, "you must enter something in text field!", Toast.LENGTH_LONG).show();

                }
            }
        });


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* item = parent.getItemAtPosition(position).toString() + " has been selected";
                indexVal = position;
                String text=myListView.getItemAtPosition(position).toString();
                Toast.makeText(Services.this, ""+text, Toast.LENGTH_SHORT).show();*/

               openDialog();

                /*finish();
                AddService(myListView.getItemAtPosition(position).toString());
                   *//* serviceInput.setText("");
                    services.clear();*//*
                viewData();*/
            }

        });



        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.deleteData(services.get(position));
                services.remove(position);
                myAdapter.notifyDataSetChanged();

                myListView.setAdapter(myAdapter);

                return true;
            }

        });
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String serviceN;
//                Intent receivedIntent = getIntent();
//                serviceN = receivedIntent.getStringExtra("serviceName");
//                AddService(serviceN);
//                populateListView();
//
//            }
//        });




    }

    private void openDialog() {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Services.this);
        mBuilder.setTitle("documents");

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.documents,null);
        EditText newD;
        TextView last;
        Button ok,cancel;

        newD = view.findViewById(R.id.documenttext);
        last = view.findViewById(R.id.textView4);
        ok = view.findViewById(R.id.ok);
        cancel = view.findViewById(R.id.cancel);

        mBuilder.setView(view);

        final AlertDialog alertDialog=mBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.insertdoc(newD.getText().toString(),"1");

                Cursor data = db.viewdoc();
                while(data.moveToNext()){
                    //get the value of database in column 1
                    last.setText(data.getString(0));
                }

                type.setText(newD.getText().toString());
            }
        });
        alertDialog.show();






    }



    private void AddService() {
         String servicestrg = serviceInput.getText().toString();
         String typestrg = type.getText().toString();

        boolean insert = db.insertData_Services(servicestrg,typestrg);
        if (insert == true) {
                Toast.makeText(Services.this, "Data added", Toast.LENGTH_LONG).show();


        } else {
                Toast.makeText(Services.this, "Data not added", Toast.LENGTH_LONG).show();

        }

    }

       public void populateListView(){

           Cursor data = db.viewData();
           while(data.moveToNext()){
               //get the value of database in column 1
               services.add(data.getString(0));
           }
           myAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,services);
           myListView.setAdapter(myAdapter);


       }


}

