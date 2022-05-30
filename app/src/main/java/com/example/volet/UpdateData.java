package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public class UpdateData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    EditText des_input, amount_input;
    Button update_button, date_input,deleteRow;
    TextView cat_input,type;
    String type_in;
    String type_out;
    private Spinner spinner,spinner2;
    private Button dateButton;

    String id, des, amount, date, cat;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getSupportActionBar().hide();

        initDatePicker();
        dateButton=findViewById(R.id.datePickerButton2);
        deleteRow=findViewById(R.id.deleteRow);

        Button out =findViewById(R.id.expense2);
        Button in =findViewById(R.id.income2);

        des_input = findViewById(R.id.des2);
        amount_input = findViewById(R.id.amount2);
        date_input = findViewById(R.id.datePickerButton2);
        cat_input = findViewById(R.id.category_txt2);
        update_button=findViewById(R.id.update);
        type=findViewById(R.id.type_txt2);

        getAndSetIntentData();







        spinner=findViewById(R.id.spinner3);
        String[] categories=getResources().getStringArray(R.array.out_categories);
        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        cat_input=findViewById(R.id.category_txt2);
        spinner.setOnItemSelectedListener(this);

        //dropdown menu2

        spinner2=findViewById(R.id.spinner4);
        String[] categories2=getResources().getStringArray(R.array.in_categories);
        ArrayAdapter adapter2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        cat_input=findViewById(R.id.category_txt2);
        spinner2.setOnItemSelectedListener(this);


        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setBackgroundColor(Color.rgb(156,39,176));
                out.setBackgroundColor(Color.rgb(211,211,211));
                type.setText("income");
                spinner2.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.INVISIBLE);
                cat_input.setText(type_in);
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setBackgroundColor(Color.rgb(211,211,211));
                out.setBackgroundColor(Color.rgb(156,39,176));
                type.setText("expense");
                spinner.setVisibility(View.VISIBLE);
                spinner2.setVisibility(View.INVISIBLE);
                cat_input.setText(type_out);

            }
        });
//deleting an entry

        deleteRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });



//update data
        update_button.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
              ConnectionHelper myDB=new ConnectionHelper(UpdateData.this);
              if(type.getText()=="income") {
                  myDB.updateData(id, String.valueOf(des_input.getText()), Double.valueOf(String.valueOf(amount_input.getText())), String.valueOf(date_input.getText()), String.valueOf(cat_input.getText()));
              }else{
                  myDB.updateData(id, String.valueOf(des_input.getText()),  (-1*Double.valueOf(String.valueOf(amount_input.getText()))), String.valueOf(date_input.getText()), String.valueOf(cat_input.getText()));
              }
              Intent exit = new Intent(UpdateData.this,HomePage.class);
              startActivity(exit);
              finish();
          }
          });
        }
        void getAndSetIntentData () {
            if (getIntent().hasExtra("id") && getIntent().hasExtra("des") && getIntent().hasExtra("amount")
                    && getIntent().hasExtra("date") && getIntent().hasExtra("cat")
            ) {

                //Getting Data from intent

                id = getIntent().getStringExtra("id");
                des = getIntent().getStringExtra("des");
                amount = getIntent().getStringExtra("amount");
                cat = getIntent().getStringExtra("cat");
                date = getIntent().getStringExtra("date");

                //Setting Intent Data
                des_input.setText(des);
                cat_input.setText(cat);
                date_input.setText(date);
                //category
                Button out =findViewById(R.id.expense2);
                Button in =findViewById(R.id.income2);

                if(Double.valueOf(amount)>=0){
                    type.setText("income");
                    in.setBackgroundColor(Color.rgb(156,39,176));
                    out.setBackgroundColor(Color.rgb(211,211,211));
                    amount_input.setText(String.valueOf(df.format(Double.valueOf(amount))));

                }else{
                    out.setBackgroundColor(Color.rgb(156,39,176));
                    in.setBackgroundColor(Color.rgb(211,211,211));
                    type.setText("expense");
                    amount_input.setText(String.valueOf(df.format((-1)*Double.valueOf(amount))));
                }



            } else {
                Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
            }
        }
//delete dialog

        void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Caution");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                ConnectionHelper myDB=new ConnectionHelper((UpdateData.this));
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
        }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String valuesFromSpinners = parent.getItemAtPosition(position).toString();
        if(type.getText()=="income"){
            type_in=valuesFromSpinners;
            spinner2.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.INVISIBLE);
        }else{
            type_out=valuesFromSpinners;
            spinner2.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.VISIBLE);
        }
        cat_input.setText(valuesFromSpinners);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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



}