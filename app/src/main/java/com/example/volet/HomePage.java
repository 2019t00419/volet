package com.example.volet;

import androidx.annotation.Nullable;
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

import java.text.DecimalFormat;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {


    ConnectionHelper myDB ;
    ArrayList<String>id,description,amount,date,dateId;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    TextView total_txt,income_txt,expense_txt;
    //formatting output to two decimals
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        recyclerView=findViewById(R.id.recyclerView);

        //setting texts to relevant data*/
        myDB=new ConnectionHelper(HomePage.this);
        id=new ArrayList<>();
        description=new ArrayList<>();
        amount=new ArrayList<>();
        date=new ArrayList<>();
        dateId=new ArrayList<>();
        displayData();
        customAdapter= new CustomAdapter(HomePage.this,HomePage.this,id,description,amount,date,dateId);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));


        //total generate
        double total=0;
        double income=0;
        double expense=0;

        for(int i = 0; i<amount.size(); i++){
            total= total+Double.parseDouble(amount.get(i));
            if(Double.parseDouble(amount.get(i))>=0){
                income=income+Double.parseDouble(amount.get(i));
            }else{
                expense=expense+((-1)*Double.parseDouble(amount.get(i)));
            }
        }
        total_txt = findViewById(R.id.total_txt);
        income_txt = findViewById(R.id.income_txt);
        expense_txt = findViewById(R.id.expense_txt);

        total_txt.setText("Total: "+df.format(total));
        if(total>=0){
            total_txt.setTextColor(Color.rgb(56,172,236));
        }else {
            total_txt.setTextColor(Color.rgb(237,92,92));
        }
        income_txt.setText("Income: "+df.format(income));
        expense_txt.setText("Expense: "+df.format(expense));
        expense_txt.setTextColor(Color.rgb(237,92,92));
        income_txt.setTextColor(Color.rgb(56,172,236));
    }

    public void addData(View v){
        Intent i = new Intent(HomePage.this, newData.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
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
                 dateId.add(cursor.getString(4));

             }
         }

    }
}