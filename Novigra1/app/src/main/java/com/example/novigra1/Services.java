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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import java.util.ArrayList;

public class Services extends AppCompatActivity {

    TextView documents;
    ListView myListView;
    EditText serviceInput;
    Button addValue, updateValue;

    ArrayList<String> services ;
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
        documents = (TextView) findViewById(R.id.documents);
        services = new ArrayList<>();
        myListView = (ListView) findViewById(R.id.listView);
        serviceInput = (EditText) findViewById(R.id.editServiceName);
        addValue = (Button) findViewById(R.id.addButton);
        db = new DBHelper(this);
//        updateValue = (Button) findViewById(R.id.updateButton);

        /*services.add("Permis de conduire");
        services.add("Carte santé");*/




        //myListView.setAdapter((myAdapter));

        addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean test=false;
                String serviceName;
                serviceName = serviceInput.getText().toString();

                if (serviceInput.length() != 0) {
                    Intent intent = new Intent(Services.this, Required_Documents.class);
                    startActivity(intent);
                    test=true;

                } else {
                    Toast.makeText(Services.this, "you must enter something in text field!", Toast.LENGTH_LONG).show();

                }if(test) {
                    String text = getIntent().getStringExtra("TEXT");
                    documents.setText(text);
                    String ch = documents.getText().toString();
                    AddData(serviceName, ch);
                    serviceInput.setText("");
                    services.clear();
                    viewData();
                }
            }
        });


    }



    private void AddData(String serviceName, String ch) {
        boolean insert = db.insertData_Services(serviceName,ch);
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

                myAdapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, services);
                myListView.setAdapter(myAdapter);
            }
        }

    }

       /* myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                *//*item = parent.getItemAtPosition(position).toString() + " has been selected";
                indexVal = position;*//*
                String text=myListView.getItemAtPosition(position).toString();
                Toast.makeText(Services.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });*/

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

