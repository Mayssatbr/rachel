package com.example.novigra1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Required_Documents extends AppCompatActivity {


    Toolbar toolbar;


    Button button,save;
    TextView textView;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.required_documents);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            toolbar.setTitle(bundle.getString("servicename"));
        }

        save = (Button) findViewById(R.id.save);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        listItems = getResources().getStringArray(R.array.documents);
        checkedItems = new boolean[listItems.length];

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Required_Documents.this);
                mBuilder.setTitle(R.string.Dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!mSelected.contains(position)) {
                                mSelected.add(position);

                            }
                        }else
                            if(mSelected.contains(position)){
                            mSelected.remove(position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        String Newligne=System.getProperty("line.separator");
                        for (int i = 0; i <mSelected.size(); i++){
                            item = item + Newligne +listItems[mSelected.get(i)];
                        }
                        textView.setText(item);
                    }
                });
                mBuilder.setNegativeButton(R.string.Dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i<checkedItems.length; i++){
                            checkedItems[i] = false;
                            mSelected.clear();
                            textView.setText("");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }

        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text= textView.getText().toString();
                String name = toolbar.getTitle().toString();
                Intent intent=new Intent(Required_Documents.this,Services.class);

                intent.putExtra("documents",text);
                intent.putExtra("serviceName",name);

                startActivity(intent);

            }
        });

    }
}
