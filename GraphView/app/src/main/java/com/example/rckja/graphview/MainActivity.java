package com.example.rckja.graphview;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final ArrayList <Attendance> listItem = new ArrayList <>(5);
    EditText mDateEntryField;
    EditText mCountEntryField;
    boolean isValid = true;
    boolean countIsValid = true;
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAddData = findViewById(R.id.btnAddData);
        mDateEntryField =  findViewById(R.id.edtDate);
        //mDateEntryField.addTextChangedListener(tw);
        mCountEntryField = findViewById(R.id.edtCount);
        mCountEntryField.addTextChangedListener(tw);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean updateFlag =true;
                    InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    Date date = formatter.parse(mDateEntryField.getText().toString());
                    if (checkDateFormat(mDateEntryField.getText().toString()) && countIsValid !=false) {
                        if(listItem.size() ==0){
                            listItem.add(new Attendance(date, Integer.parseInt(mCountEntryField.getText().toString())));
                        }
                        else {
                            int index =findAndReplaceDate(mDateEntryField.getText().toString());
                            if(index <0)
                                listItem.add(new Attendance(date, Integer.parseInt(mCountEntryField.getText().toString())));
                        }
                        if(listItem.size() > 5) {
                            Collections.sort(listItem);
                            listItem.remove(0);
                        }
                        Collections.sort(listItem);
                        BarGraphView graphView = findViewById(R.id.barGraph);
                        graphView.addList(listItem);
                        graphView.invalidate();

                    } else {
                        if(!countIsValid)
                            Toast.makeText(MainActivity.this, "Please enter valid count.", Toast.LENGTH_LONG).show();
                        else
                        Toast.makeText(MainActivity.this, "Please provide Date in format MM/dd.", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
    }

    private int findAndReplaceDate(String date){
        int index =-1;
        try {
            for(int i=0;i< listItem.size();i++){
                Attendance objAttendance = listItem.get(i) ;
                if(objAttendance.getmDate().equals(formatter.parse(date))){
                    objAttendance.setmAttendanceCount(Integer.parseInt(mCountEntryField.getText().toString()));
                    index =i;
                }
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }

        return index;
    }
    //TextWatcher to check Attendance count on Leave event.
    private TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            countIsValid = true;
            if (mCountEntryField.getText().hashCode() == s.hashCode()) {
                if (!s.toString().equals("")) {
                    if (Integer.parseInt(s.toString()) > 100 || Integer.parseInt(s.toString()) < 0) {
                        mCountEntryField.setError("Please enter valid count.");
                        countIsValid = false;
                    }
                }
            }

        }

    };
    //Function to check date format
    // Require input parameter String =input date
    // Return Boolean as true if format is MM/dd
      private Boolean checkDateFormat(String strDate) {
        String dateString = strDate.toString();
        isValid =true;
        if (dateString.split("\\/")[0].length() <= 2) {
            if (Integer.parseInt(dateString.split("\\/")[0]) < 1 || Integer.parseInt(dateString.split("\\/")[0]) > 12) {
                isValid = false;
            } else {
                if(strDate.toString().split("\\/")[0].length() <2)
                    dateString = 0 + dateString;
                mDateEntryField.setText(dateString);
                mDateEntryField.setSelection(strDate.length());
            }

        }
        if (dateString.split("\\/").length > 1) {
            if (dateString.split("\\/")[1].length() <= 2) {
                String enteredDate = dateString.split("\\/")[1];
                Calendar mycal = new GregorianCalendar();
                mycal.set(Calendar.YEAR, 2017);
                mycal.set(Calendar.MONTH, Integer.parseInt(dateString.split("\\/")[0]) - 1);
                int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
                if (Integer.parseInt(enteredDate) < 0 || Integer.parseInt(enteredDate) > daysInMonth) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }
}


