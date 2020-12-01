package com.example.novigra1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import java.util.ArrayList;

public class Services extends AppCompatActivity {
    Toolbar toolbar;


    ListView myListView;
    EditText serviceInput;
    Button addValue, delete, show;

    ArrayList<String> services ;
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;
    DatabaseHelper_Services db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        show = (Button) findViewById(R.id.showButton);
        services = new ArrayList<>();
        myListView = (ListView) findViewById(R.id.listView);
        serviceInput = (EditText) findViewById(R.id.editServiceName);
        addValue = (Button) findViewById(R.id.addButton);
        delete = (Button) findViewById(R.id.deleteButton);
        db = new DatabaseHelper_Services(this);
        myAdapter = new ArrayAdapter<String>(
                Services.this, android.R.layout.simple_list_item_1, services);
//        updateValue = (Button) findViewById(R.id.updateButton);

        /*services.add("Permis de conduire");
        services.add("Carte santé");*/




        //myListView.setAdapter((myAdapter));

        addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName;
                serviceName = serviceInput.getText().toString();

                if (serviceInput.length() != 0) {
                    services.add(serviceName);
                    myListView.setAdapter(myAdapter);

                   /* AddData(serviceName);
                   *//* serviceInput.setText("");
                    services.clear();*//*
                    viewData();*/


                } else {
                    Toast.makeText(Services.this, "you must enter something in text field!", Toast.LENGTH_LONG).show();

                }
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName;
                serviceName = serviceInput.getText().toString();
                boolean delete = db.deleteData(serviceName);
                if(delete==true){
                    Toast.makeText(Services.this, "Service Deleted", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Services.this,"Service not deleted",Toast.LENGTH_SHORT).show();
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

                Intent intent = new Intent(Services.this, Required_Documents.class);
                intent.putExtra("servicename",myListView.getItemAtPosition(position).toString());

                startActivity(intent);
                /*finish();
                AddService(myListView.getItemAtPosition(position).toString());
                   *//* serviceInput.setText("");
                    services.clear();*//*
                viewData();*/
            }

        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(services.size()!=0) {
                    for (int i = 0; i < services.size(); i++)
                        AddService(myListView.getChildAt(i).toString());
                }
                else
                {
                    Toast.makeText(Services.this,"Nothing to show",Toast.LENGTH_SHORT).show();
                    Toast.makeText(Services.this,"please press button add before",Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
    private void AddService(String serviceName) {
        Bundle bundle = getIntent().getExtras();
        String text=bundle.getString("documents");

        boolean insert = db.insertData(serviceName,text);
        if (insert==true){
            Toast.makeText(Services.this, "Data added", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(Services.this, "Data not added", Toast.LENGTH_LONG).show();

        }
    }

   /* private void OpenActivity(){
        Intent intent = new Intent(Services.this, Required_Documents.class);
        startActivity(intent);
        finish();
    }



    private void AddData(String serviceName) {
        OpenActivity();
        String text = getIntent().getStringExtra("text");
        documents.setText(text);

        boolean insert = db.insertData(serviceName,text);
        if (insert==true){
            Toast.makeText(Services.this, "Data added", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(Services.this, "Data not added", Toast.LENGTH_LONG).show();

        }
    }

    private void viewData() {
        Cursor cursor = db.viewData();
        if (cursor.getCount() == 0) {
            Toast.makeText(Services.this, "No data to show", Toast.LENGTH_LONG).show();

        } else {
            while (cursor.moveToNext()) {
                services.add(cursor.getString(1));

                *//*myAdapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, services);
                myListView.setAdapter(myAdapter);*//*
            }
        }

    }*/



       /* updateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = serviceInput.getText().toString();
                services.set(indexVal,serviceName);
                myAdapter.notifyDataSetChanged();

            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString() + " has been deleted";
                Toast.makeText(Services.this, item, Toast.LENGTH_SHORT).show();
                services.remove(position);
                myAdapter.notifyDataSetChanged();


                return true;
            }
        });*/


}

