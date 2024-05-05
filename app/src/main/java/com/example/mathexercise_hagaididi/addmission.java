package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;

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
private Spinner spinMissionType;
private TextView createYourMission;
private Spinner spinPriority;
private LinearLayout normal;
private EditText missionName;
private EditText Hour;
private Button date;
private EditText explanation;
private LinearLayout empty;
private CalendarView calender;
private FrameLayout calenderFrame;
private String selectedDate;
private String priority;


    String[] bankMissions={"mission type","normal","by days","continual"};
    String[] bankPriority ={"priority","urgent","important","casual"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmission);
        initviews();
        spiners();
        activity();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), bankMissions[position], Toast.LENGTH_LONG).show();
        if (bankMissions[position].equals("normal")){
            empty.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        }
        else if (bankMissions[position].equals("mission type")){
            normal.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void initviews(){
        spinMissionType = findViewById(R.id.spinnerMissionType);
        createYourMission =findViewById(R.id.createYourMission);
        normal = findViewById(R.id.normal);
        missionName =findViewById(R.id.missionName);
        Hour = findViewById(R.id.hour);
        date = findViewById(R.id.date);
        explanation = findViewById(R.id.explanation);
        empty = findViewById(R.id.empty);
        calender = findViewById(R.id.calendar);
        calenderFrame = findViewById(R.id.calendarFrame);
        spinPriority =findViewById(R.id.spinnerPriority);

    }
    public void activity(){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.getText().equals("confirm")){
                    calenderFrame.setVisibility(View.GONE);
                    date.setText(selectedDate+"");
                }
                else {
                    calenderFrame.setVisibility(View.VISIBLE);
                    date.setText("confirm");
                }
            }

        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                selectedDate = dayOfMonth + "/" + month + "/" + year;
            }
        });
    }
    public void spiners(){
        spinMissionType.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankMissions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMissionType.setAdapter(aa);
        spinMissionType.setSelection(0);
        spinPriority.setOnItemSelectedListener(this);
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankPriority);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPriority.setAdapter(bb);
        spinPriority.setSelection(0);
    }
}