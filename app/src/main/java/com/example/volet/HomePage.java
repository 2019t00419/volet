package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
//import android.os.Handler;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        //collecting data from newData.class
        String descript="";
        String amount="";
        String type="";

        Bundle getData= getIntent().getExtras();
        if(getData !=null){
            descript= getData.getString("description");
            amount= getData.getString("amount");
            type= getData.getString("type");
        }
        //setting texts to relevant data
        TextView textView32 =findViewById(R.id.textView32);
        TextView textView33 =findViewById(R.id.textView33);
        TextView textView34 =findViewById(R.id.textView34);
        textView32.setText(descript);
        textView33.setText(amount);
        textView34.setText(type);
    }

    public void addData(View v){
        Intent i = new Intent(HomePage.this, newData.class);
        startActivity(i);
    }
}