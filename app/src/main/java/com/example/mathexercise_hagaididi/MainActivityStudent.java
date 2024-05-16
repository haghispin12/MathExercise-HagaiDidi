package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivityStudent extends AppCompatActivity {
    private RecyclerView rcTeachers;
    private teachersAdapter teachersAdapter;
    private MainViewModelStudent mainViewModelStudent;
    private ArrayList<teacher> teachers = new ArrayList<teacher>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        mainViewModelStudent = new ViewModelProvider(this).get(MainViewModelStudent.class);
        initviews();

    }

    public void initviews(){
        rcTeachers = findViewById(R.id.teachersRecycle) ;
    }
    public void temp(){

    }
    public void initUsersAdapter(ArrayList<teacher> teachers){
        teachersAdapter = new teachersAdapter(teachers, new teachersAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(teacher item) {


            }
        });
        rcTeachers.setLayoutManager(new LinearLayoutManager(this));
        rcTeachers.setAdapter(teachersAdapter);
        rcTeachers.setHasFixedSize(true);
    }
    }
