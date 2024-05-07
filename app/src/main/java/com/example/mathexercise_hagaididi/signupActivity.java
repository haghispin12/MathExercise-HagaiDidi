package com.example.mathexercise_hagaididi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupActivity extends AppCompatActivity {
    private Button submitB;
    private Button loginB;
    private EditText Email;
    private EditText password;
    private TextView signup;
    private EditText confirmPassword;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        initviews();
        activity();
    }



public void initviews() {
    signup = findViewById(R.id.textview_signup);
    Email = findViewById(R.id.edittext_email);
    password = findViewById(R.id.edittext_password);
    submitB = findViewById(R.id.button_submit);
    loginB = findViewById(R.id.button_login);
    confirmPassword = findViewById(R.id.edittext_confirmPassword);
}

public void activity() {
    submitB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(password.getText().toString().equals(confirmPassword.getText().toString())) {
                auth.createUserWithEmailAndPassword(Email.getText().toString(),password.getText().toString()).addOnCompleteListener(signupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(signupActivity.this, "sign up success.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signupActivity.this, "sign up failed.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else {
                Toast.makeText(signupActivity.this, "passwords are not the same", Toast.LENGTH_SHORT).show();
            }
        }
    });
    loginB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(signupActivity.this, loginActivity.class);
            startActivity(intent);
        }
    });
}
}