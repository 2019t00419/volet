package com.example.volet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

        private Context context;
        private ArrayList id,description,amount;

        CustomAdapter(Context context,
                      ArrayList id,
                      ArrayList description,
                      ArrayList amount){
            this.context=context;
            this.id=id;
            this.amount=amount;
            this.description=description;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.my_row,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.id_txt.setText(String.valueOf(id.get(position)));
            holder.description_txt.setText(String.valueOf(description.get(position)));
            holder.amount_txt.setText(String.valueOf(amount.get(position)));
        }

        @Override
        public int getItemCount() {
            return id.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView id_txt,description_txt,amount_txt;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                id_txt=itemView.findViewById(R.id.id_txt);
                description_txt=itemView.findViewById(R.id.description_txt);
                amount_txt=itemView.findViewById(R.id.amount_txt);

            }
        }
}