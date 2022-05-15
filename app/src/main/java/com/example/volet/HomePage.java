package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
//import android.os.Handler;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volet.ui.ConnectionHelper;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {


    ConnectionHelper myDB ;
    ArrayList<String>id,description,amount,date;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        recyclerView=findViewById(R.id.recyclerView);
        /*collecting data from newData.class
        String descript="";
        String amount="";
        String type="";

        Bundle getData= getIntent().getExtras();
        if(getData !=null){
            descript= getData.getString("description");
            amount= getData.getString("amount");
            type= getData.getString("type");
        }
        //setting texts to relevant data*/
        myDB=new ConnectionHelper(HomePage.this);
        id=new ArrayList<>();
        description=new ArrayList<>();
        amount=new ArrayList<>();
        date=new ArrayList<>();
        displayData();
        customAdapter= new CustomAdapter(HomePage.this,id,description,amount,date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
    }

    public void addData(View v){
        Intent i = new Intent(HomePage.this, newData.class);
        startActivity(i);
    }
    void displayData() {
        Cursor cursor=myDB.readAllData();
         if(cursor.getCount()==0){
             Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
         }else{
             while (cursor.moveToNext()){
                 id.add(cursor.getString(0));
                 description.add(cursor.getString(1));
                 amount.add(cursor.getString(2));
                 date.add(cursor.getString(3));

             }
         }

    }
}