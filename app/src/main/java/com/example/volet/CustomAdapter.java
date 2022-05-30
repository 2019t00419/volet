package com.example.volet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

        private Context context;
        Activity activity;
        private ArrayList id,description,amount,date,CatId;
        private static final DecimalFormat df = new DecimalFormat("0.00");

        CustomAdapter(Activity activity,
                      Context context,
                      ArrayList id,
                      ArrayList description,
                      ArrayList amount,
                      ArrayList date,
                      ArrayList catId){
            this.activity=activity;
            this.context=context;
            this.id=id;
            this.amount=amount;
            this.description=description;
            this.date=date;
            this.CatId=catId;

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
            holder.catId_txt.setText(String.valueOf(CatId.get(position)));

            //edit entries
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, UpdateData.class);
                    intent.putExtra("id",String.valueOf(id.get(position)));
                    intent.putExtra("des",String.valueOf(description.get(position)));
                    intent.putExtra("date",String.valueOf(date.get(position)));
                    intent.putExtra("cat",String.valueOf(CatId.get(position)));
                    intent.putExtra("amount",String.valueOf(amount.get(position)));
                    activity.startActivityForResult(intent,1);

                }
            });
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
            TextView id_txt,description_txt,amount_txt,date_txt,catId_txt;
            LinearLayout mainLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                id_txt=itemView.findViewById(R.id.id_txt);
                description_txt=itemView.findViewById(R.id.description_txt);
                amount_txt=itemView.findViewById(R.id.amount_txt);
                date_txt=itemView.findViewById(R.id.date_txt);
                catId_txt=itemView.findViewById(R.id.catId_txt);
                mainLayout = itemView.findViewById(R.id.mainLayout);

            }
        }
}
