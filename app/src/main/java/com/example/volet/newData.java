package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class newData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
        getSupportActionBar().hide();
    }
    public void submitData(View v){
        Intent i = new Intent(newData.this, HomePage.class);
        startActivity(i);
    }
}