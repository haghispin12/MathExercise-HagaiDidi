package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int answer;
    private TextView math;
    private Button chlng;
    private Button x20 ;
    private Button mulb ;
    private TextView mul ;
    private EditText ans;
    private Button chk;
    private Button save;
    private Button sau;
    private TextView firstnum;
    private TextView secondnum;
    private MainViewModel viewModelMain;
    private TextView user;
    private TextView score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelMain = new ViewModelProvider(this).get(MainViewModel.class);
        initView();
        SetUserDetails();
        viewModelMain.Vnum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                firstnum.setText(integer+"");
            }
        });
        viewModelMain.Vnum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                secondnum.setText(integer+"");
            }
        });
        viewModelMain.Score.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                score.setText(integer+"");
            }
        });


    }

    private void initView()
    {
        math = findViewById(R.id.mathe);
        chlng = findViewById(R.id.btnchlng);
        chlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               viewModelMain.chalenge(3);
               ans.setText("");
            }

        });
        x20 = findViewById(R.id.btn20x);
        x20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelMain.x20(2);
                ans.setText("");
            }
        });
        mulb = findViewById(R.id.btnmulb);
        mulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewModelMain.mulltable(1);
                ans.setText("");
            }
        });
        firstnum = findViewById(R.id.firstnum);
        mul = findViewById(R.id.mul);
        secondnum = findViewById(R.id.secondnum);
        ans = findViewById(R.id.ans);
        chk = findViewById(R.id.chk);
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(MainActivity.this, viewModelMain.answer(ans), Toast.LENGTH_SHORT).show();
            }
        });
        save = findViewById(R.id.save);
        sau = findViewById(R.id.sau);
        score =findViewById(R.id.score);
        user = findViewById(R.id.User);

    }
    public void SetUserDetails(){
    String userName = getIntent().getStringExtra("UserName");
    user.setText(("welcome "+userName+"! current score: "));
    viewModelMain.SetUserDetails(userName);
    }

}