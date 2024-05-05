package com.example.mathexercise_hagaididi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private Button logInB;
    private Button signUpB;
    private EditText Email;
    private EditText password;
    private TextView loginT;
    private TextView signupT;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_project);
        initviews();
        activity();
    }

    public void initviews() {
        loginT = findViewById(R.id.textview_login);
        signupT = findViewById(R.id.textview_signup);
        Email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_password);
        logInB = findViewById(R.id.button_login);
        signUpB = findViewById(R.id.button_signup);

    }

    public void activity() {
        logInB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(Email.getText().toString(),password.getText().toString()).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(loginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        });
    }
}