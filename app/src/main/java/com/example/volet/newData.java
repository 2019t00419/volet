package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.volet.ui.ConnectionHelper;

public class newData extends AppCompatActivity {

    EditText des,amount;
    Button submit;
    String Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
        getSupportActionBar().hide();
        Button in =findViewById(R.id.income);
        in.setBackgroundColor(Color.rgb(156,39,176));
        Button out =findViewById(R.id.expense);
        out.setBackgroundColor(Color.rgb(211,211,211));
        des = findViewById(R.id.des);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ConnectionHelper myDB = new ConnectionHelper(newData.this);
                myDB.addData(des.getText().toString().trim(),
                        Integer.valueOf((amount.getText().toString().trim())));
                Intent sendData = new Intent(newData.this,HomePage.class);
                startActivity(sendData);
                finish();
            }
        });
    }
    /*public void submitData(View v){
        //collecting data from the query
        EditText des = findViewById(R.id.des);
        EditText amount = findViewById(R.id.amount);

        String descript=des.getText().toString();
        String amnt=amount.getText().toString();

        //sending data to home page
        Intent sendData = new Intent(newData.this,HomePage.class);
        sendData.putExtra("description",descript);
        sendData.putExtra("amount",amnt);

        startActivity(sendData);
        finish();

    }*/
    public void expensed(View v){
        Button in =findViewById(R.id.income);
        in.setBackgroundColor(Color.rgb(211,211,211));
        Button out =findViewById(R.id.expense);
        out.setBackgroundColor(Color.rgb(156,39,176));
    }
    public void incomed(View v){
        Button in =findViewById(R.id.income);
        in.setBackgroundColor(Color.rgb(156,39,176));
        Button out =findViewById(R.id.expense);
        out.setBackgroundColor(Color.rgb(211,211,211));

    }
}