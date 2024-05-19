package com.example.mathexercise_hagaididi;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainViewModelStudent extends ViewModel {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<ArrayList<teacher>> LiveTeachers = new MutableLiveData<ArrayList<teacher>>();


    public String GetCurrentEmail() {
        return auth.getCurrentUser().getEmail();
    }

    public void getTeachers() {
        FirebaseFirestore.getInstance().collection("teachers profiles").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                ArrayList<teacher> tempTeachers = new ArrayList<teacher>();
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (documentSnapshot.exists()) {
                        String location = documentSnapshot.getString("location");
                        String phoneNumber = documentSnapshot.getString("phoneNumber");
                        String name = documentSnapshot.getString("name");
                        int price = Integer.valueOf(Math.toIntExact(documentSnapshot.getLong("price")));
                        String Email = documentSnapshot.getString("email");
                        teacher teacher = new teacher(phoneNumber, price, location, name, Email);
                        tempTeachers.add(teacher);
                    }
                }
                LiveTeachers.setValue(tempTeachers);
            }
        });
    }

    public void IsHasATeacher() {
        db.collection("connections").whereEqualTo("email", GetCurrentEmail()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getTeachers();
            }
        });
    }
    public void MakeAConnection(String EmailTeacher,String EmailStudent){
        connection connection = new connection(EmailTeacher,EmailStudent,"inActivate");
        db.collection("connections").document().set(connection).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });

    }
}
