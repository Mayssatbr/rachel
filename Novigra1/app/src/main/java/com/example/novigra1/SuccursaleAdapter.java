package com.example.novigra1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuccursaleAdapter extends RecyclerView.Adapter<SuccursaleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList succursale,employee;
    Activity activity;
    SuccursaleAdapter(Context context, ArrayList succursale, ArrayList employee){
        this.context = context;
        this.succursale = succursale;
        this.employee = employee;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.succursale_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // get the string from our Array and set the text
        holder.nameSuccursale.setText(String.valueOf(succursale.get(position)));
        holder.nameEmployee.setText(String.valueOf(employee.get(position)));

        holder.EmployeeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DeleteData.class);
                intent.putExtra("username", String.valueOf(succursale.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return succursale.size();
    }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView nameSuccursale, nameEmployee;
            LinearLayout EmployeeLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                //get id of TextView
                nameSuccursale = itemView.findViewById(R.id.SuccName);
                nameEmployee = itemView.findViewById(R.id.EmployeeName);
                EmployeeLayout = itemView.findViewById(R.id.EmployeeLayout);

            }
        }

}
