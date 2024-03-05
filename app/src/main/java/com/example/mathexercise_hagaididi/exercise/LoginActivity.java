/*
package com.example.mathexercise_hagaididi.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mathexercise_hagaididi.R;

public class LoginActivity extends AppCompatActivity {
    private EditText UserName;
    private Button submit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        SharedPreferences sh = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        UserName = findViewById(R.id.username);
        submit = findViewById(R.id.submit);
        UserName.setText(sh.getString("name",""));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            myEdit.putString("name", UserName.getText().toString());
            myEdit.apply();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("UserName", UserName.getText().toString());
            startActivity(intent);

            }
        });
    }

}*/
