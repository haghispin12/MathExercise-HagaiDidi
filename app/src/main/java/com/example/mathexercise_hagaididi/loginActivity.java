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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class loginActivity extends AppCompatActivity {
    private Button submitB;
    private Button signUpB;
    private EditText Email;
    private EditText password;
    private TextView loginT;
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
        Email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_password);
        submitB = findViewById(R.id.button_submit);
        signUpB = findViewById(R.id.button_signup);

    }

    public void activity() {
        submitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    auth.signInWithEmailAndPassword(Email.getText().toString(), password.getText().toString()).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(loginActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                                FirebaseFirestore.getInstance().collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        for(DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                                            if(documentSnapshot.exists()){
                                                String email = documentSnapshot.getString("email");
                                                String type = documentSnapshot.getString("type");

                                                if(email.equals(auth.getCurrentUser().getEmail())){
                                                    if(type.equals("student")){
                                                        Intent intent = new Intent(loginActivity.this, MainActivityStudent.class );
                                                        startActivity(intent);
                                                    }
                                                    else{
                                                        Intent intent = new Intent(loginActivity.this, MainActivityTeacher.class );
                                                        startActivity(intent);
                                                    }
                                                }

                                            }
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(loginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


            }
        });
        signUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(loginActivity.this,signupActivity.class);
            startActivity(intent);
            }
        });
    }
}