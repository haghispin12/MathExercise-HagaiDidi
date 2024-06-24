package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        mainViewModelStudent = new ViewModelProvider(this).get(MainViewModelStudent.class);
        initviews();
        mainViewModelStudent.IsHasATeacher();
        mainViewModelStudent.lessonsListener();

        activity();
    }

    public void initviews(){
        rcTeachers = findViewById(R.id.teachersRecycle) ;
        rcLessonsStudent = findViewById(R.id.lessons_students_recycle);
        welcome = findViewById(R.id.welcome);
        scheduleNewLesson = findViewById(R.id.schedule_lesson);
        choseAteacher = findViewById(R.id.choose_your_teacher);
        yourLessonsT = findViewById(R.id.text_yourLessons);
        noTeacher = findViewById(R.id.noTeacher);
        hasTeacher = findViewById(R.id.hasTeacher);
    }
    public void activity(){

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
    }
