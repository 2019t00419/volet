package com.example.volet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.math.RoundingMode;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

        private Context context;
        private ArrayList id,description,amount,date,dateId;
        private static final DecimalFormat df = new DecimalFormat("0.00");

        CustomAdapter(Context context,
                      ArrayList id,
                      ArrayList description,
                      ArrayList amount,
                      ArrayList date,
                      ArrayList dateId){
            this.context=context;
            this.id=id;
            this.amount=amount;
            this.description=description;
            this.date=date;
            this.dateId=dateId;

        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.my_row,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int pos) {

            int position = amount.size()-1-pos;
            holder.id_txt.setText(String.valueOf(id.get(position)));
            holder.description_txt.setText(String.valueOf(description.get(position)));
            holder.date_txt.setText(String.valueOf(date.get(position)));
            holder.dateId_txt.setText(String.valueOf(dateId.get(position))+"00000"+String.valueOf(id.get(position)));
            if(Double.valueOf(String.valueOf(amount.get(position)))>=0){
               holder.amount_txt.setTextColor(Color.rgb(56,172,236));
               holder.amount_txt.setText(df.format(Double.valueOf(String.valueOf(amount.get(position)))));
            }else {
                holder.amount_txt.setTextColor(Color.rgb(237,92,92));
                holder.amount_txt.setText(df.format((-1)*Double.valueOf(String.valueOf(amount.get(position)))));
            }
        }

        @Override
        public int getItemCount() {
            return id.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView id_txt,description_txt,amount_txt,date_txt,dateId_txt;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                id_txt=itemView.findViewById(R.id.id_txt);
                description_txt=itemView.findViewById(R.id.description_txt);
                amount_txt=itemView.findViewById(R.id.amount_txt);
                date_txt=itemView.findViewById(R.id.date_txt);
                dateId_txt=itemView.findViewById(R.id.dateId_txt);

            }
        }
}
