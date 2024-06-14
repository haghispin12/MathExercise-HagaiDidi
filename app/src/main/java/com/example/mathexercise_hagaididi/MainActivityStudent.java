package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivityStudent extends AppCompatActivity {
    private RecyclerView rcTeachers;
    private teachersAdapter teachersAdapter;
    private MainViewModelStudent mainViewModelStudent;
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
    }
    public void activity(){
        mainViewModelStudent.LiveTeachers.observe(this, new Observer<ArrayList<teacher>>() {
            @Override
            public void onChanged(ArrayList<teacher> teachers) {
                initUsersAdapter(teachers);
            }
        });
    }
    public void initUsersAdapter(ArrayList<teacher> teachers){
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
    }
