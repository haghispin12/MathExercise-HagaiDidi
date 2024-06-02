package com.example.mathexercise_hagaididi;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivityTeacher extends AppCompatActivity {
private Button yourProfile;
private studentAdapter studentAdapter;
private RecyclerView rcStudents;
private MainViewModelTeacher mainViewModelTeacher;
private Button daiActiv;
private Button daiRefuse;
private TextView daiStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        mainViewModelTeacher = new ViewModelProvider(this).get(MainViewModelTeacher.class);
        initviews();
        mainViewModelTeacher.connectionsListener();
        activity();
    }
    public void initviews(){
        yourProfile = findViewById(R.id.button_yourProfile);
        rcStudents = findViewById(R.id.studentsRecycle);
    }
    public void activity(){
        mainViewModelTeacher.LiveStudents.observe(this, new Observer<ArrayList<student>>() {
            @Override
            public void onChanged(ArrayList<student> students) {
                initUsersAdapter(students);
            }
        });
        yourProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                trans.add(R.id.teacherfrag,new fragmentTeacherProfile());
                trans.addToBackStack(null);
                trans.commit();
            }
        });
    }
    public void initUsersAdapter(ArrayList<student> students){
        studentAdapter = new studentAdapter(students, new studentAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(student item) {
                Dialog dialog = new Dialog(MainActivityTeacher.this);
                dialog.setContentView(R.layout.dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                daiActiv = dialog.findViewById(R.id.dai_Activate);
                daiRefuse = dialog.findViewById(R.id.dai_refuse);
                daiStatus = dialog.findViewById(R.id.diaText);
                dialog.show();
                daiActiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainViewModelTeacher.changeStatus(item,"Active");
                        dialog.cancel();
                    }
                });
                daiRefuse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainViewModelTeacher.changeStatus(item,"refused");
                        dialog.cancel();
                    }
                });

            }


        });
        rcStudents.setLayoutManager(new LinearLayoutManager(this));
        rcStudents.setAdapter(studentAdapter);
        rcStudents.setHasFixedSize(true);

    }
}