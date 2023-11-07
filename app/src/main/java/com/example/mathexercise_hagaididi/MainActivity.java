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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelMain = new ViewModelProvider(this).get(MainViewModel.class);
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
        initView();
    }

    private void initView()
    {
        math = findViewById(R.id.mathe);
        chlng = findViewById(R.id.btnchlng);
        chlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               viewModelMain.chalenge();
               ans.setText("");
            }

        });
        x20 = findViewById(R.id.btn20x);
        x20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelMain.x20();
                ans.setText("");
            }
        });
        mulb = findViewById(R.id.btnmulb);
        mulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewModelMain.mulltable();
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

    }

}