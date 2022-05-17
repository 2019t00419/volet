package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.volet.ui.ConnectionHelper;

import java.util.Calendar;

public class newData<fragmentFirstBinding> extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private TextView type_txt,category_txt;
    private Spinner spinner,spinner2;
    String type_in="Select Category";
    String type_out="Select Category";

    EditText des,amount;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
        getSupportActionBar().hide();

        initDatePicker();
        dateButton=findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        type_txt = findViewById(R.id.type_txt);
        type_txt.setText(getTodaysDateId());

        Button in =findViewById(R.id.income);
        in.setBackgroundColor(Color.rgb(211,211,211));
        Button out =findViewById(R.id.expense);
        out.setBackgroundColor(Color.rgb(156,39,176));
        type_txt.setText("expence");

        des = findViewById(R.id.des);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submit);
        out.setOnClickListener(new View.OnClickListener(){

            //type buttons expense
            @Override
            public void onClick(View v) {
                Button out =findViewById(R.id.expense);
                out.setBackgroundColor(Color.rgb(156,39,176));
                Button in =findViewById(R.id.income);
                in.setBackgroundColor(Color.rgb(211,211,211));
                type_txt.setText("expence");
                spinner2.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.VISIBLE);
                category_txt.setText(type_out);
            }
        });
        in.setOnClickListener(new View.OnClickListener(){

            //type buttons income
            @Override
            public void onClick(View v) {
                Button in =findViewById(R.id.income);
                in.setBackgroundColor(Color.rgb(156,39,176));
                Button out =findViewById(R.id.expense);
                out.setBackgroundColor(Color.rgb(211,211,211));
                type_txt.setText("income");
                spinner2.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.INVISIBLE);
                category_txt.setText(type_in);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            //send data to the database
            @Override
            public void onClick(View view) {
                String date = (String) dateButton.getText();
                ConnectionHelper myDB = new ConnectionHelper(newData.this);
                if(type_txt.getText()=="income")
                myDB.addData(des.getText().toString().trim(),
                        Double.parseDouble((amount.getText().toString().trim())),
                        ((String) dateButton.getText()).trim(),
                        type_txt.getText().toString().trim());
                else{
                    myDB.addData(des.getText().toString().trim(),
                            Double.parseDouble(("-"+amount.getText().toString().trim())),
                            ((String) dateButton.getText()).trim(),
                            type_txt.getText().toString().trim());
                }

                Intent sendData = new Intent(newData.this,HomePage.class);
                startActivity(sendData);
                finish();
            }
        });

        //dropdown menu1

        spinner=findViewById(R.id.spinner);
        String[] categories=getResources().getStringArray(R.array.out_categories);
        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        category_txt=findViewById(R.id.category_txt);
        spinner.setOnItemSelectedListener(this);

        //dropdown menu2

        spinner2=findViewById(R.id.spinner2);
        String[] categories2=getResources().getStringArray(R.array.in_categories);
        ArrayAdapter adapter2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        category_txt=findViewById(R.id.category_txt);
        spinner2.setOnItemSelectedListener(this);
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
    private String getTodaysDateId() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        if(month>9) {
            return (year + "" + month + "" + day);
        }
        else {
            return (year + "0" + month + "" + day);
        }
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date= makeDateString(day,month,year);
                String dateId=makeDate(day,month,year);
                dateButton.setText(date);
                type_txt.setText(dateId);
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
        if(month>9) {
            return (year + "" + month + "" + day);
        }
        else {
            return (year + "0" + month + "" + day);
        }
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



    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String valuesFromSpinner = parent.getItemAtPosition(position).toString();
        if(type_txt.getText()=="income"){
            type_in=valuesFromSpinner;
        }else{
            type_out=valuesFromSpinner;
        }
        category_txt.setText(valuesFromSpinner);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}