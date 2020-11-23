package com.example.serviceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    EditText serviceInput;
    Button addValue, updateValue;

    ArrayList<String> services = new ArrayList<String>();
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.listView);
        serviceInput = (EditText) findViewById(R.id.editServiceName);
        addValue = (Button) findViewById(R.id.addButton);
        updateValue = (Button) findViewById(R.id.updateButton);

        services.add("Permis de conduire");
        services.add("Carte santé");



        myAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,services);

        myListView.setAdapter((myAdapter));

        addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = serviceInput.getText().toString();
                services.add(serviceName);
                myAdapter.notifyDataSetChanged();

                serviceInput.setText("");
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString() + " has been selected";
                indexVal = position;
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

        updateValue.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                services.remove(position);
                myAdapter.notifyDataSetChanged();


                return true;
            }
        });


    }
}