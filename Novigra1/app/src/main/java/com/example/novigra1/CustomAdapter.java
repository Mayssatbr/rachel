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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList nameData, emailData;
    Activity activity;

    CustomAdapter(Activity activity,Context context, ArrayList nameData, ArrayList emailData ){
        this.activity = activity;
        this.context = context;
        this.nameData = nameData;
        this.emailData = emailData;

    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // get the string from our Array and set the text
        holder.userName_txt.setText(String.valueOf(nameData.get(position)));
        holder.userEmail_txt.setText(String.valueOf(emailData.get(position)));

        holder.clientLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DeleteData.class);
                intent.putExtra("username", String.valueOf(nameData.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return nameData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName_txt,userEmail_txt;
        LinearLayout clientLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //get id of TextView
            userName_txt= itemView.findViewById(R.id.userNamee);
            userEmail_txt = itemView.findViewById(R.id.userMail);
            clientLayout = itemView.findViewById(R.id.clientLayout);

        }
    }
}
