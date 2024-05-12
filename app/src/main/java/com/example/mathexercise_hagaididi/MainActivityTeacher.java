package com.example.mathexercise_hagaididi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivityTeacher extends AppCompatActivity {
private Button yourProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        initviews();
        activity();
    }
    public void initviews(){
        yourProfile = findViewById(R.id.button_yourProfile);
    }
    public void activity(){
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
}