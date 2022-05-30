package com.example.volet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class UpdateData extends AppCompatActivity {

    EditText des_input, amount_input;
    Button update_button, date_input;
    TextView cat_input,type;

    String id, des, amount, date, cat;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        des_input = findViewById(R.id.des2);
        amount_input = findViewById(R.id.amount2);
        date_input = findViewById(R.id.datePickerButton2);
        cat_input = findViewById(R.id.category_txt2);
        update_button=findViewById(R.id.update);
        type=findViewById(R.id.type_txt2);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
              ConnectionHelper myDB=new ConnectionHelper(UpdateData.this);
              myDB.updateData(id,String.valueOf(des_input.getText()),Double.valueOf(amount),String.valueOf(date_input.getText()),String.valueOf(cat_input.getText()));

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
}