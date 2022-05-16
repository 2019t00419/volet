package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.volet.ui.ConnectionHelper;

import java.util.Calendar;

public class newData extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    EditText des,amount;
    Button submit;
    String Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
        getSupportActionBar().hide();

        initDatePicker();
        dateButton=findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        Button in =findViewById(R.id.income);
        in.setBackgroundColor(Color.rgb(156,39,176));
        Button out =findViewById(R.id.expense);
        out.setBackgroundColor(Color.rgb(211,211,211));


        des = findViewById(R.id.des);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            //send data to the database
            @Override
            public void onClick(View view) {
                String date = (String) dateButton.getText();
                TextView textView = findViewById(R.id.textView);
                textView.setText(date);
                ConnectionHelper myDB = new ConnectionHelper(newData.this);
                myDB.addData(des.getText().toString().trim(),
                        Double.parseDouble((amount.getText().toString().trim())),((String) dateButton.getText()).trim());
                Intent sendData = new Intent(newData.this,HomePage.class);
                startActivity(sendData);
                finish();
            }
        });
    }
//date picker
    private String getTodaysDate() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date= makeDateString(day,month,year);
                dateButton.setText(date);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+ " "+ day+ " "+year;
    }
    private String makeDate(int day, int month, int year) {
        return year+ " "+ month+ " "+day;
    }

    private String getMonthFormat(int month) {
        if(month==1) {
            return "JAN";
        }
        if(month==2) {
            return "FEB";
        }
        if(month==3) {
            return "MAR";
        }
        if(month==4) {
            return "APR";
        }
        if(month==5) {
            return "MAY";
        }
        if(month==6) {
            return "JUN";
        }
        if(month==7) {
            return "JUL";
        }
        if(month==8) {
            return "AUG";
        }
        if(month==9) {
            return "SEP";
        }
        if(month==10) {
            return "OCT";
        }
        if(month==11) {
            return "NOV";
        }
        if(month==12) {
            return "DEC";
        }
        return "JAN";
    }

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

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}