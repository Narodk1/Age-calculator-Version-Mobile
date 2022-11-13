package com.example.calculatice_dage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
   FloatingActionButton fabdata;
    TextView tvAge;
   TextView tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabdata=findViewById(R.id.fabdatapicker);
        tvAge=findViewById(R.id.tvage);
        tvDate=findViewById(R.id.tvdate);
        fabdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                int  myear=c.get(Calendar.YEAR);
                int  myonth=c.get(Calendar.MONTH);
                int  mday=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datedialog = new DatePickerDialog(v.getContext(),datePickerListener,myear,myonth,mday);
                datedialog.getDatePicker().setMaxDate(new Date().getTime());
                datedialog.show();
            }
        });

    }
 private DatePickerDialog.OnDateSetListener  datePickerListener =new DatePickerDialog.OnDateSetListener() {
     @Override
     public void onDateSet(DatePicker view, int year, int month, int day) {
         Calendar c =Calendar.getInstance();
         c.set(Calendar.YEAR, year);
         c.set(Calendar.MONTH, month);
         c.set(Calendar.DAY_OF_MONTH, day);
         String format= new SimpleDateFormat("dd-mm-yyyy").format(c.getTime());
         tvDate.setText(format);
         tvAge.setText(Integer.toString(calculateage(c.getTimeInMillis())));

     }
 };
    int calculateage(long date){
        Calendar da= Calendar.getInstance();
        da.setTimeInMillis(date);
        Calendar today= Calendar.getInstance();
        int age = today.get(Calendar.YEAR)-da.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_MONTH)<da.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }
}