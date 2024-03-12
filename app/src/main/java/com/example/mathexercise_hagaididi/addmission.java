package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class addmission extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
private Spinner spin;
private TextView createYourMission;
private LinearLayout normal;
private EditText missionName;
private EditText Hour;
private Button date;
private EditText explanation;
private LinearLayout empty;
private CalendarView calender;
private FrameLayout calenderFrame;

    String[] bankMissions={"mission type","normal","by days","continual"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmission);
        initviews();
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankMissions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        spin.setSelection(0);
        activity();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), bankMissions[position], Toast.LENGTH_LONG).show();
        if (bankMissions[position].equals("normal")){
            normal.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
        else if (bankMissions[position].equals("mission type")){
            empty.setVisibility(View.VISIBLE);
            normal.setVisibility(View.GONE);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void initviews(){
        spin =(Spinner) findViewById(R.id.spinner);
        createYourMission =findViewById(R.id.createYourMission);
        normal = findViewById(R.id.normal);
        missionName =findViewById(R.id.missionName);
        Hour = findViewById(R.id.hour);
        date = findViewById(R.id.date);
        explanation = findViewById(R.id.explanation);
        empty = findViewById(R.id.empty);
        calender = (CalendarView) findViewById(R.id.calendar);
        calenderFrame = findViewById(R.id.calendarFrame);
    }
    public void activity(){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calenderFrame.setVisibility(View.VISIBLE);
            }

        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }
}