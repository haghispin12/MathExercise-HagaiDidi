package com.example.mathexercise_hagaididi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signupActivity extends AppCompatActivity {
    private Button submitB;
    private Button loginB;
    private EditText Email;
    private EditText password;
    private TextView signup;
    private EditText confirmPassword;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Spinner TorS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initviews();
        activateSpinner();
        activity();
    }



public void initviews() {
    TorS = findViewById(R.id.spinner_TorS);
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
                        Users user = new Users(Email.getText().toString(),TorS.getSelectedItem().toString());
                        FirebaseFirestore.getInstance().collection("Users").document().set(user) .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
                        if(TorS.getSelectedItem().toString().equals("student")) {
                            Intent intent = new Intent(signupActivity.this, MainActivityStudent.class );
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(signupActivity.this, MainActivityTeacher.class );
                            startActivity(intent);
                        }
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
    public void activateSpinner(){
        String[] optionsTorS = {"teacher","student"};
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, optionsTorS);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TorS.setAdapter(ad);
    }
}