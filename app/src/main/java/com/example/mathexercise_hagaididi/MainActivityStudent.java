package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivityStudent extends AppCompatActivity {
    private RecyclerView rcTeachers;
    private teachersAdapter teachersAdapter;
    private MainViewModelStudent mainViewModelStudent;
    private lessonsAdapter lessonsAdapter;
    private TextView welcome;
    private TextView choseAteacher;
    private RecyclerView rcLessonsStudent;
    private Button scheduleNewLesson;
    private TextView yourLessonsT;
    private LinearLayout hasTeacher;
    private LinearLayout noTeacher;
    private Button check;
    private BatteryReceiver batteryReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        mainViewModelStudent = new ViewModelProvider(this).get(MainViewModelStudent.class);
        initviews();
        mainViewModelStudent.IsHasATeacher();
        mainViewModelStudent.lessonsListener();
        activity();
        activateReceiver();
    }

    public void initviews(){
        check = findViewById(R.id.check);
        rcTeachers = findViewById(R.id.teachersRecycle) ;
        rcLessonsStudent = findViewById(R.id.recycle_lessons_student);
        welcome = findViewById(R.id.welcome);
        scheduleNewLesson = findViewById(R.id.schedule_lesson);
        choseAteacher = findViewById(R.id.choose_your_teacher);
        yourLessonsT = findViewById(R.id.text_yourLessons);
        noTeacher = findViewById(R.id.noTeacher);
        hasTeacher = findViewById(R.id.hasTeacher);
    }
    public void activity(){
        mainViewModelStudent.LiveLessons.observe(this, new Observer<ArrayList<lesson>>() {
            @Override
            public void onChanged(ArrayList<lesson> lessons) {
                initLessonsAdapter(lessons);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModelStudent.IsHasATeacher();
            }
        });
        scheduleNewLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                trans.add(R.id.fragStudent,new fragment_student());
                trans.addToBackStack(null);
                trans.commit();
            }
        });
        mainViewModelStudent.LiveIsHasTeacherBool.observe(this, new Observer<ArrayList<Boolean>>() {
            @Override
            public void onChanged(ArrayList<Boolean> booleans) {
                hasAteacher();
            }
        });
        mainViewModelStudent.LiveTeachers.observe(this, new Observer<ArrayList<teacher>>() {
            @Override
            public void onChanged(ArrayList<teacher> teachers) {
                initTeachersAdapter(teachers);
            }
        });
    }
    public void initTeachersAdapter(ArrayList<teacher> teachers){
        teachersAdapter = new teachersAdapter(teachers, new teachersAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(teacher item) {
                mainViewModelStudent.MakeAConnection(item.getEmail(), mainViewModelStudent.GetCurrentEmail());

            }
        });
        rcTeachers.setLayoutManager(new LinearLayoutManager(this));
        rcTeachers.setAdapter(teachersAdapter);
        rcTeachers.setHasFixedSize(true);

    }
    public void initLessonsAdapter(ArrayList<lesson> lessons){
        lessonsAdapter = new lessonsAdapter(lessons, new lessonsAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(lesson item) {

            }
        });

        rcLessonsStudent.setLayoutManager(new LinearLayoutManager(this));
        rcLessonsStudent.setAdapter(lessonsAdapter);
        rcLessonsStudent.setHasFixedSize(true);

    }
    public void hasAteacher(){
        if(mainViewModelStudent.getIsHasATeacher()!=-1) {
            noTeacher.setVisibility(View.GONE);
            hasTeacher.setVisibility(View.VISIBLE);
        }
    }
    public void activateReceiver(){
        batteryReceiver = new BatteryReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(batteryReceiver);
    }
    }
