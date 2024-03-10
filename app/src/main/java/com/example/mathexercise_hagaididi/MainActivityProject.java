package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivityProject extends AppCompatActivity {
    private TextView toDoList;
    private Button addMission;
    private RecyclerView rc;
    private missionAdapter Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_project);
        initviews();
        ArrayList<mission> a1 =new ArrayList<mission>();
        mission m1 = new mission("math",32);
        mission m2 = new mission("buy a car",62);
        a1.add(m1);
        a1.add(m2);
        initmissionAdapter(a1);
        Activity();
    }
    public void initviews(){
        rc =findViewById(R.id.rcView);
        addMission =findViewById(R.id.addMission);
        toDoList =findViewById(R.id.toDoList);
    }
    public void initmissionAdapter(ArrayList<mission> missions){
        Adapter=new missionAdapter(missions, new missionAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(mission item) {

            }

        });
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(Adapter);
        rc.setHasFixedSize(true);
    }
    public void Activity(){
        addMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityProject.this,addmission.class);
                startActivity(intent);
            }
        });
    }
}