/*
package com.example.mathexercise_hagaididi.exercise;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mathexercise_hagaididi.R;

public class MainActivity extends AppCompatActivity {

    int answer;
    private TextView math;
    private Button chlng;
    private Button x20;
    private Button mulb;
    private TextView mul;
    private EditText ans;
    private Button chk;
    private Button save;
    private Button sau;
    private TextView firstnum;
    private TextView secondnum;
    private MainViewModel viewModelMain;
    private TextView user;
    private TextView score;
    private Button rate;
    private Fragment SAU;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        viewModel();
        SetUserDetails();
        activity();

    }

    private void initView() {
        math = findViewById(R.id.mathe);
        chlng = findViewById(R.id.btnchlng);
        x20 = findViewById(R.id.btn20x);
        mulb = findViewById(R.id.btnmulb);
        firstnum = findViewById(R.id.firstnum);
        mul = findViewById(R.id.mul);
        secondnum = findViewById(R.id.secondnum);
        ans = findViewById(R.id.ans);
        chk = findViewById(R.id.chk);
        save = findViewById(R.id.save);
        sau = findViewById(R.id.sau);
        score = findViewById(R.id.score);
        user = findViewById(R.id.User);
        rate = findViewById(R.id.rate);

    }
    ActivityResultLauncher <Intent> activityResultLauncher =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            viewModelMain.setuserRate(result.getData().getIntExtra("progress",-1));
        }
    });

    private void SetUserDetails() {
        String userName = getIntent().getStringExtra("UserName");
        user.setText(("welcome " + userName + "! current score: "));
        viewModelMain.SetUserDetails(userName);

    }

    public void activity() {
        sau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             */
/*   Intent intent = new Intent(MainActivity.this,FruitsActivity.class);
                startActivity(intent);*//*

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                trans.add(R.id.fragShowUsers,new fragShowUsers());
                trans.addToBackStack(null);
                trans.commit();
            }
        });
        chlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelMain.chalenge(3);
                ans.setText("");
            }
        });
        x20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelMain.x20(2);
                ans.setText("");
            }
        });
        mulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelMain.mulltable(1);
                ans.setText("");
            }
        });
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, viewModelMain.answer(ans), Toast.LENGTH_SHORT).show();
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RateActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

    public void viewModel() {
        viewModelMain = new ViewModelProvider(this).get(MainViewModel.class);
        viewModelMain.Vnum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                firstnum.setText(integer + "");
            }
        });
        viewModelMain.Vnum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                secondnum.setText(integer + "");
            }
        });
        viewModelMain.Score.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                score.setText(integer + "");
            }
        });

    }


}

*/
