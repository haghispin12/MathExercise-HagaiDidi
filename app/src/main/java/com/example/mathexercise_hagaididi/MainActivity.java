package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int num1;
    int num2;
    int answer;
    private TextView math;
    private Button chlng;
    private Button x20 ;
    private Button mulb ;
    private TextView firstnum;
    private TextView mul ;
    private TextView secondnum;
    private EditText ans;
    private Button chk;
    private Button save;
    private Button sau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView()
    {
        math = findViewById(R.id.mathe);
        chlng = findViewById(R.id.btnchlng);
        chlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                num1=r.nextInt(9)+1;
                num2=r.nextInt(90)+10;
                firstnum.setText(num1+"");
                secondnum.setText(num2+"");

            }
        });
        x20 = findViewById(R.id.btn20x);
        x20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                num1=r.nextInt(9)+1;
                num2=r.nextInt(10)+10;
                firstnum.setText(num1+"");
                secondnum.setText(num2+"");
            }
        });
        mulb = findViewById(R.id.btnmulb);
        mulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                num1=r.nextInt(10)+1;
                num2=r.nextInt(10)+1;
                firstnum.setText(num1+"");
                secondnum.setText(num2+"");
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
                String s = ans.getText().toString();
                answer = Integer.valueOf(s);
                if(num2*num1==answer){
                    Toast.makeText(MainActivity.this, "well done!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "maybe next time...:(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        save = findViewById(R.id.save);
        sau = findViewById(R.id.sau);

    }

}